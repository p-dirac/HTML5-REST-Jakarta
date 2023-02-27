/*
pager.js provides a reusable web component for paging services.
When the user changes the page info, a custom event is dispatched named 'custom:updatePage'.
See the usage below on how to add this component to HTML, and how to listen for this event.

Usage: 
In the HTML body add this tag:
	<pager-component></pager-component>
	
In the HTML script section, add javascript functions like this:

function pageHandler(event){
  pageInfo = event.detail;
  loadPage(pageInfo);
}

function loadPage(pageInfo){
   console.log( {'loadPage' : pageInfo}  );
   // use fetch to send page request to server and receive
   // response with one page of data, plus updated page info
   refreshPage();
   
}

document.addEventListener('custom:updatePage', pageHandler);	
*/

// page info global
let pageNum = 1;
let pageSize = 10;
let totalPages = 1;
 
// object for page info 
let pageInfo = {
   pageNum : pageNum,
   pageSize : pageSize,
   totalPages : totalPages	
}

/**
 * Get current page info as object
 *
 * @return pageInfo
 */
function getPageInfo(){
	pageInfo.pageNum = pageNum;
	pageInfo.pageSize = pageSize;
	pageInfo.totalPages = totalPages;
	return pageInfo;
}

function updatePager(){
  	const myWebComponent = document.querySelector("pager-component");
    // Note: mode must be open for querySelector to work
	const mytotal = myWebComponent.shadowRoot.querySelector("#pageT");
	// here we need string concat   +totalPages+   rather than  ${totalPages}
	mytotal.innerHTML = ' of '+totalPages+' pages';
	const mynode = myWebComponent.shadowRoot.querySelector("#pageN");
	mynode.max = totalPages;
	mynode.value = pageNum;
	const myselector = myWebComponent.shadowRoot.querySelector("#pageSizing");
	myselector.value = pageSize;
}


  

function previousPage(selectObject) {
  if(pageNum > 1) pageNum--;
    updatePageN(pageNum);
  sendEvent(selectObject);
}

function nextPage(selectObject) {
  if(pageNum < totalPages) pageNum++;
  updatePageN(pageNum);
  sendEvent(selectObject)
}

function setPageSize(selectObject) {
  pageSize = Number(selectObject.value);  
  // reset to page 1 when page size changes
  pageNum = 1;
  // totalPages is unknown when page size changes
  totalPages = 1
  console.log({'pageSize': pageSize});
  sendEvent(selectObject)
}

function setPageNum(selectObject) {
  pageNum = Number(selectObject.value);  
  updatePageN(pageNum);
  sendEvent(selectObject)
}

function updatePageN(pageNum){
	pageInfo.pageNum = pageNum;
	console.log( {'pageNum': pageNum} );
  const myWebComponent = document.querySelector("pager-component");
	myWebComponent.shadowRoot.querySelector("#pageN").value = pageNum;
}

function sendEvent(selectObject){
	pageInfo = getPageInfo();
	selectObject.dispatchEvent(new CustomEvent('custom:updatePage', 
		{ 
		bubbles: true,
  		composed: true,
		detail: pageInfo 
		}
	));
	console.log({'event dispatched': pageInfo});
}



const pagerTemplate = document.createElement('template');

pagerTemplate.innerHTML = `

      <style>
      .center {
                display: flex;
                justify-content: center;
                align-items: center;
            }
            
		section {
			/* top | right | bottom | left */
			margin: 5px 0 5px 0;
          list-style: none;
          display: inline;
        }    
        .spc{
			margin-left: 10px;
			padding-left: 5px;
    		padding-right: 5px;
		}        
        input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}

      </style>
      
      
      <section  class="center">

        <button id="prevButton" class="spc" onclick="previousPage(this)">&lt; Prev</button>

		<input type="number" id="pageN" name="page" class="spc" min="1" max="${totalPages}" value="${pageNum}" onChange="setPageNum(this)"> 
		<label id="pageT"> of ${totalPages} pages</label>
        
        <button id="nextButton" class="spc" onclick="nextPage(this)">Next &gt;</button>
        
		<label	for="pageSizing" class="spc">Page size</label> 
		<select name="size"	id="pageSizing"  class="spc" onchange="setPageSize(this)">
		//
		</select> 
			
      </section>
    `;



class Pager extends HTMLElement {
  constructor() {
    super();
    //
    // Note: mode must be open for querySelector to work
    //
    const shadowRoot = this.attachShadow({ mode: 'open' });

	pagerTemplate.cloneNode(true);
    shadowRoot.appendChild(pagerTemplate.content);
    
    
      this.createPageSizeList();
  }


	createPageSizeList() {
	    console.log({'createPageSizeList': pageSize});
    	const sizeList = [5, 10, 20, 50, 100];
    	 const selector = this.shadowRoot.querySelector('#pageSizing');
    	 console.log({'selector': selector});
    	for (const size of sizeList) {
    		const option = document.createElement( 'option' );
    	//	let s = size.toString();
            option.value = size;
            option.text = size;
            selector.appendChild( option );    	  
    	}
    	// set default selection
    	selector.value = pageSize;  
    	//
    	//  let i = sizeList.indexOf(pageSize);
    	//  console.log({'i': i});
    	//  selector.selectedIndex = i;
	}

  
}  // end class

customElements.define('pager-component', Pager);

