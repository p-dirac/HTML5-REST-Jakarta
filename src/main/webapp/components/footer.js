const footerTemplate = document.createElement('template');

footerTemplate.innerHTML = `


      <style>
      .center {
                display: flex;
                justify-content: center;
                align-items: center;
            }
        footer {
			font-size: 1.2rem;
			/* top right bottom left */
          padding: 4px 4px 4px 4px;
          list-style: none;
          display: flex;
          justify-content: space-between;
          align-items: center;
          background-color: Turquoise;
        }
        ul {
			margin: 0;
          list-style: none;
          display: inline;
        }
        
        ul li {
			/* top right bottom left */
          padding: 4px 30px 4px 30px;

          list-style: none;
          display: inline;
        }
        
        a {
          margin: 0 0px;
          color: inherit;
          text-decoration: none;
        }
        
        a:hover {
          padding-bottom: 4px;
          box-shadow: inset 0 -2px 0 0 #333;
        }
        
        
      </style>
      <footer class="center">
        <ul>
          <li><a href="about.html">About</a></li>
          <li><a href="docs.html">Docs</a></li>
          <li><a href="help.html">Help</a></li>
        </ul>
      </footer>
    `;

class Footer extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const shadowRoot = this.attachShadow({ mode: 'closed' });

    shadowRoot.appendChild(footerTemplate.content);
  }
}

customElements.define('footer-component', Footer);
