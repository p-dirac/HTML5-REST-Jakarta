

function setNotifierTitle(title){
	console.log( {'setNotifierTitle': title} );
	const myNotifier = document.querySelector("notifier-component");
    // Note: mode must be open for querySelector to work
	const mytitle = myNotifier.shadowRoot.querySelector("#title");
	mytitle.innerHTML = title;
}

function setNotifierMessage(msg){
	console.log( {'setMessage': msg} );
	const myNotifier = document.querySelector("notifier-component");
    // Note: mode must be open for querySelector to work
	const mymsg = myNotifier.shadowRoot.querySelector("#msg");
	mymsg.innerHTML = msg;
}



const notifierTemplate = document.createElement('template');

notifierTemplate.innerHTML = `
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
<dialog id="notifyDialog">
  <form method="dialog">
	<header class="center">
	     <h3 id="title"></h3>
	 </header>

	 <div class="center">
	 	<label id="msg"></label>
	 </div>

	 <section  class="center">
	   <button id="okbtn" value="yes ok">
	     Ok
	   </button>

	 </section>

   </form>
</dialog>
</section>
`;


class DialogNotifier extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
	  //
	  // mode must be open to use shadowRoot.querySelector("#notifyDialog");
	  //
    const shadowRoot = this.attachShadow({ mode: 'open' });
	//
    shadowRoot.appendChild(notifierTemplate.content);
  }
}  // end class

customElements.define('notifier-component', DialogNotifier);


//  ---------------------------------



/*
   notify is called from html file
*/
async function notify(){
	// must get confirmer-component inside function, outside returns null
	const myNotifier = document.querySelector("notifier-component");
	//
	// != checks for null and undefined at the same time
	//
	if(myNotifier != null){
		const mydialog = myNotifier.shadowRoot.querySelector("#notifyDialog");
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



/*
function notify(){
	// must get confirmer-component inside function, outside returns null
	const myNotifier = document.querySelector("notifier-component");
	//
	// != checks for null and undefined at the same time
	//
	if(myNotifier != null){
		const mydialog = myNotifier.shadowRoot.querySelector("#notifyDialog");
		//
		if(!mydialog.open){
			//
			mydialog.showModal();
			//
		}
		//		
		const myokBtn = myNotifier.shadowRoot.querySelector("#okbtn");
		//
		myokBtn.addEventListener("click", () => {
		  mydialog.close("closing");
		});
	}
}
*/

