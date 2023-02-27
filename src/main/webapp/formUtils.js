        //
        // Example use of querySelector for html input tag:
        //
        // <input name="searchTxt" type="text" maxlength="512" id="searchTxt" class="searchField"/>
        // Use the powerful document.querySelector('selector').value
        // which uses a CSS selector to select the element
        //
        // For example, document.querySelector('#searchTxt').value; selected by id (note the # sign)
        // document.querySelector('.searchField').value; selected by class (note the dot)
        // document.querySelector('input').value; selected by tagname (in the '  ')
        // document.querySelector('[name="searchTxt"]').value; selected by name (note the '[ ]' )
        //

//----------------------------

let restMethod = 'POST';

let nextPage = null;


let username = 'user';
let password = 'userpass';

// ------------------------------------

function setUserAuth(name, pass){
 	username = name;
 	password = pass;
}


/**
 * Function for setting redirect info after database update
 *
 * @param {string} listPage - relative URL which is on the same level as the current page
 */
function prepUpdate(listPage){
 	// note: restMethod will be reset after submit
 	restMethod = 'PUT';
 	// note: nextPage will be reset after submit
 	// redirects to nextPage, which is on the same level as the current page
 	nextPage = listPage;
 //	console.log({'nextPage': nextPage});
}

/**
 * Function for POSTing data as JSON with fetch.
 *
 * @param {string} url - URL to POST data to
 * @param {FormData} formData - `FormData` instance
 * @return {Object} - Response body from URL that was POSTed
 */
async function postFormDataAsJson( { url, formData }) {
	 let resp = null;
	 try{
	//variable url passed to function must match outer variable name
	let plainFormData = Object.fromEntries(formData.entries());
	let formDataJsonString = JSON.stringify(plainFormData);
	console.log({'rest url': url});
	console.log({'rest formDataJson': formDataJsonString});
	// btoa() takes a string and encodes it to Base64.
	//
	const fetchOptions = {
		method: restMethod,
		mode: 'cors',
		headers: {
			'Accept': 'application/json',
		  //  'Authorization': 'Basic ' + btoa(username + ":" + password),
			'Content-Type': 'application/json; charset=UTF-8',
		},
		body: formDataJsonString
	};
	//
	console.log({'fetchOptions': fetchOptions});
	//
	//
	resp = await fetch(url, fetchOptions);
 } catch (error) {
  console.log(error);
}
	return resp;
}

/**
 * Handler for a form submit response.
 *
 * @param {Response from submit} resp
 */
async function handleFormResponse(resp) {
	//
	try {
		//
		console.log({resp});
		// jsonResp - javascript object with {key : value}
		const jsonResp = await resp.json();
		console.log({'form jsonResp': jsonResp});
		let msg = jsonResp.msg;
		console.log({'form msg': msg});
		//
		// uncomment alert to compare with notify()
		// alert('form msg: ' + msg);
		//
		let titleResp = 'Add Response';
		// for restMethod = POST, stay on Add page, as user may want to add more,
		// or let user go back to Home
		if(restMethod === 'PUT'){
			titleResp = 'Update Response';
		}
		console.log({'form titleResp': titleResp});
		setNotifierTitle(titleResp);
		setNotifierMessage(msg);
		//
		let noteResult = await notify();
		//
		console.log({'form noteResult': noteResult});
		//
		// reset restMethod
		restMethod = 'POST';
		//
		if(nextPage && resp.ok){
			// go back to page list
			// redirects to nextPage, which is on the same level as the current page
		    window.location.href = nextPage;
		} else{
			console.log({'nextPage': nextPage});
			console.log({'resp.ok': resp.ok});
		}
	} catch (ex) {
		console.error(ex);
	}
}
 /**
  * Event handler for a form submit event.
  *
  * @param {SubmitEvent} event
  */
 async function handleFormSubmit(event) {
	 /**
	  * This prevents the default behaviour of the browser submitting
	  * the form so that we can handle things instead.
	  */
	 event.preventDefault();
	 const form = event.currentTarget;
	 //
	 //REST service url, restBaseUrl must be set externally
	 const url = restBaseUrl;
	//
	 try {
		console.log({'rest url': url});
		 const formData = new FormData(form);
		 console.log({'rest formData': formData});
		 //
		 //variable name url passed to function must match inner variable,
		 // must not rename passed variabel different than inner variable
		 //
		 const resp = await postFormDataAsJson({url, formData});
		 //
		 console.log({resp});
		 //
		 await handleFormResponse(resp);
	 } catch (ex) {
		 console.error(ex);
	 }
 }
