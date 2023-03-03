
const headerTemplate = document.createElement('template');

headerTemplate.innerHTML = `
	<link href="navDrop.css" rel="stylesheet" />
     <header>
     
        <nav  >
        
            <ul>
                <li><a href="index.html">Home</a></li>
				<li onclick="toggle('submenuA')">Employees <span class="arrow"></span>
                    <ul id="submenuA" class="submenu">
		                <li class="top"><a href="employeeForm.html">Create Employee</a></li>
		                <li class="top"><a href="employeeList.html">Employee LIst</a></li>
		            </ul>
		        </li>
                <li onclick="toggle('submenuB')">Customers <span class="arrow"></span>
                    <ul id="submenuB" class="submenu">
		                <li><a href="customerForm.html">Create Customer</a></li>
		                <li><a href="customerList.html">Customer LIst</a></li>
		            </ul>
		        </li>
                <li onclick="toggle('joinOptions')">Joins <span class="arrow"></span>
                    <ul id="joinOptions" class="submenu">
		                <li id="InnerJoin" onclick="join(this);">Employees {Inner Join} Customers</li>
		                <li id="LeftJoin" onclick="join(this);">Employees {Left Join} Customers</li>
		                <li id="RightJoin" onclick="join(this);">Employees {Right Join} Customers</li>
		                <li id="FullJoin" onclick="join(this);">Employees {Full Join} Customers</li>
		                <li id="LeftAntiJoin" onclick="join(this);">Employees {Left Anti Join} Customers</li>
		                <li id="RightAntiJoin" onclick="join(this);">Employees {Right Anti Join} Customers</li>
		                <li id="FullAntiJoin" onclick="join(this);">Employees {Full Anti Join} Customers</li>
	                    <li id="SelfJoin" onclick="join(this);">Employees {Self Join} Employees</li>
		                <li id="CrossJoin" onclick="join(this);">Employees {Cross Join} Customers</li>
		            </ul>
		        </li>

            </ul>
            
        </nav>
      
      </header>
      
    `;


class Header extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
	// Note: mode must be open for querySelector to work  
    const shadowRoot = this.attachShadow({ mode: 'open' });
    
    shadowRoot.appendChild(headerTemplate.content);
  }
}

customElements.define('header-component', Header);

// -----------------------------------

let menuItem = null;

function toggle(id) {
  const myWebComponent = document.querySelector("header-component");
  // Note: mode must be open for querySelector to work 
  let elt = myWebComponent.shadowRoot.querySelector('#'+id);
  
  	// close open menu if there is one
	if ( menuItem && elt !== menuItem ) {
	  menuItem.style.display = 'none';
	  menuItem.style.opacity = 0;
	}

  menuItem = elt;
  
 // console.log({'toggle id': id, 'elt': elt});
 // console.log({'toggle id': id, 'elt.style.display': elt.style.display});
  if(elt.style.display=='block'){
	  elt.style.display = 'none';
	  elt.style.opacity = 0;
  } else {
	  elt.style.display = 'block';
	  elt.style.opacity = 1;
  }
}

function join(el){
  let joinType = el.id;
  let title = el.innerText;
	console.log({'joinType': joinType, 'join title': title});
	//
	let join = {
		'joinType' : joinType,
		'joinTitle' : title
	}
	let js = JSON.stringify(join);
	localStorage.setItem('joinData', js);
 	window.location.href = "joinList.html";
	
	
}