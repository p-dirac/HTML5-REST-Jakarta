
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

/**
 * Display table top title and column titles
 * 
 * Note: topTitle must be defined externally
 *
 */
function renderTitles() {
	  try {
		// Select the table tbody
		let topTitlePart = document.querySelector('#topTitle');
		topTitlePart.innerHTML = topTitle;
		console.log({'renderTitles, topTitle': topTitle});
		// Select the table tbody
	    let theadPart = document.querySelector('#pageTable thead');
	    let result = '';  
		result = createColTitles();
		theadPart.innerHTML = result;
	  } catch (ex) {
	      console.error(ex);
	  }
}

/**
 * Create table column titles
 * 
 * Note: colTitles array must be defined externally
 *
 * @return row of html column titles
 */
function createColTitles() {
	// create html
	let titles = '';
	colTitles.forEach(t => {
		//
		// th must use style - white-space: pre-wrap;
		// replace first occurrence of space with newline
		let tWrap = t.replace(' ', '\n');
		// titles - must use backtick 
		titles += `<th>${tWrap}</th>\n`;
	});
	// result - must use backtick 
	let result = `<tr>
	${titles}
	</tr>`;
  return result;
}


/**
 * Fetch list of data ans display it
 * 
 * Note: restBaseUrl must be defined externally
 *
 * @return response from fetching list 
 */
async function refreshPage() {
	let resp = null;
  try {
	//REST service url
	  const  url = restBaseUrl + 'page';
  	console.log({'rest url': url});
  	//
  	pageInfo = getPageInfo();
  	console.log({'refreshPage pageInfo': pageInfo});
  	//
 	let fetchOptions = postDataAsJson(pageInfo)
 	//
	// get the data with POST request
	//
 	let resp = await fetch(url, fetchOptions);
 	//
 	if (!resp.ok) {
 	    const text = await resp.text();
 	    throw new Error(text);
 	}
	  //
	  // Note: no parse required here
	  let data = await resp.json();
	  let pageList = data.content;
	  let page = data.pageInfo;
	  // pageNum, pageSize, totalPages defined in pager.js
	  pageNum = page.pageNum;
	  pageSize = page.pageSize;
	  totalPages = page.totalPages;
	  //
	  renderTitles();
	  //
	  renderTableData(pageList);
	  //
	  // call function in pager.js
	  updatePager();
	} catch (error) {
		  console.log(error);
	}
	return resp;
}



/**
 * Create POST fetch options
 *
 * @param pageInfo instance
 * @return fetchOptions
 */
function postDataAsJson(pageInfo) {
    let dataJsonString = JSON.stringify(pageInfo);
    console.log({'rest dataJsonString': dataJsonString});
    //
    const fetchOptions = {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
          //  'Authorization': 'Basic ' + btoa(username + ":" + password),
            'Content-Type': 'application/json; charset=UTF-8',
        },
        body: dataJsonString
    };
    console.log({'fetchOptions': fetchOptions});
    //
    return fetchOptions;
 }

/**
 * Function to delete data and then refresh page
 *
 * @param {string} id - data id
*/
async function deleteRefresh(id){
	//
	const resp = await deleteById(id);
	//
    // jsonResp - javascript object with {key : value}
    const jsonResp = await resp.json();
    console.log({'delete jsonResp': jsonResp});
    let msg = jsonResp.msg;
    console.log({'delete msg': msg});
	//
	const titleResp = 'Delete Response';
	setNotifierTitle(titleResp);
	setNotifierMessage(msg);
	let noteResult = await notify();
	console.log({'notify noteResult': noteResult});
	//
	if(noteResult == 'yes ok' && resp.ok){
		// refresh list
		pageNum = 1;
		const refreshResponse = await refreshPage();
		console.log({'refreshResponse': refreshResponse});
	} else{
		console.log('notify with other');
	}
}
 
 
/**
 * Function to delete data as JSON with fetch.
 *
 * @param {string} url - URL to DELETE data
 * @param {string} id - data id
 * @return {Object} - Response body from URL that was deleted
*/
async function deleteById(id) {
	 let resp = null;
	 try{
		 
    let fullUrl = restBaseUrl + id;
    console.log({'rest fullUrl': fullUrl});
    //
    const fetchOptions = {
        method: 'DELETE',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
          //  'Authorization': 'Basic ' + btoa(username + ":" + password),
            'Content-Type': 'application/json; charset=UTF-8',
        },
      //  body: dataJsonString
    };
    //
    console.log({'fetchOptions': fetchOptions});
    //
    //
    resp = await fetch(fullUrl, fetchOptions);
    //
	 } catch (error) {
	  console.log(error);
	}            
    return resp;
}

/**
 * Wrapper function around async function
 *
 * @param event 
*/
function pageHandler(event){
  pageInfo = event.detail;
  loadPage(pageInfo);
}

/**
 * Async function for calling await function
 *
 * @param pageInfo 
*/
async function loadPage(pageInfo){
   console.log( {'loadPage' : pageInfo}  );
   // use fetch to send page request to server and receive
   // response with one page of data, plus updated page info
	const refreshResponse = await refreshPage();
	console.log({'refreshResponse': refreshResponse});
}