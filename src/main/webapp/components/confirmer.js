

function setConfirmerTitle(title){
	console.log( {'setConfirmerTitle': title} );
	const myConfirmer = document.querySelector("confirmer-component");
    // Note: mode must be open for querySelector to work
	const mytitle = myConfirmer.shadowRoot.querySelector("#title");
	mytitle.innerHTML = title;
}

function setConfirmerMessage(msg){
	console.log( {'setMessage': msg} );
	const myConfirmer = document.querySelector("confirmer-component");
    // Note: mode must be open for querySelector to work
	const mymsg = myConfirmer.shadowRoot.querySelector("#msg");
	mymsg.innerHTML = msg;
}


const confirmerTemplate = document.createElement('template');

confirmerTemplate.innerHTML = `
<style>
.center {
    display: flex;
    justify-content: center;
    align-items: center;
}
dialog::backdrop {
  background: black;
  opacity: 0.5;
}

button {
  border-radius: 2px;
  background-color: white;
  border: 1px solid black;
  margin: 5px;
  box-shadow: 1px 1px 2px grey;
}

dialog {
  max-width: 90vw;
  border: 1px solid black;
}

</style>
<section>
<dialog id="delDialog">
  <form method="dialog">
	<header class="center">
	     <h3 id="title"></h3>
	 </header>

	 <div class="center">
	     <label id="msg"></label>
	 </div>
	 
	 <section  class="center">
	   <button id="yesbtn" value="yes ok">
	     Yes
	   </button>
	
	   <button id="nobtn" value="no cancel">
	     No
	   </button>
	 </section>
	
   </form>
</dialog>
</section>
`;


class DialogConfirmer extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
	  //
	  // mode must be open to use shadowRoot.querySelector("#delDialog");
	  //
    const shadowRoot = this.attachShadow({ mode: 'open' });
	//
    shadowRoot.appendChild(confirmerTemplate.content);
  }
}  // end class

customElements.define('confirmer-component', DialogConfirmer);


//  ---------------------------------



/*
   confirmAction is called from html file
*/
async function confirmAction(){
	// must get confirmer-component inside function, outside returns null
	const myConfirmer = document.querySelector("confirmer-component");
	//
	// != checks for null and undefined at the same time
	//
	if(myConfirmer != null){
		const mydialog = myConfirmer.shadowRoot.querySelector("#delDialog");
		//
		if(!mydialog.open){
			//
			mydialog.showModal();
			//
			return new Promise((resolve) => {
				// clicking a button in the form will create close event
				mydialog.addEventListener("close", function(event){
				  // returnValue set from value in the form button
				  resolve(mydialog.returnValue);
				});
			});
		}
	}
}




