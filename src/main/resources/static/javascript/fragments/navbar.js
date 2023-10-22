function wrap(elementToWrap, wrapperElement) {
    elementToWrap.parentNode.insertBefore(wrapperElement, elementToWrap);
    wrapperElement.appendChild(elementToWrap);
}

function unwrap(wrapperElement) {
    const parent = wrapperElement.parentNode;
    while (wrapperElement.firstChild) {
        parent.insertBefore(wrapperElement.firstChild, wrapperElement);
    }
    parent.removeChild(wrapperElement);
}

document.addEventListener('DOMContentLoaded', function () {
        function toggleMenu() {
            const navigationBarRow = document.querySelector('.navigation-bar-row');
            let mobileMenuToggle = document.querySelector('.mobile-menu');

            if (window.innerWidth <= 600) {
                let mobileMenuToggle = document.querySelector('.mobile-menu');

                //if doesnt exists, CREATE elements for mobile browser
                if (!mobileMenuToggle) {
                    mobileMenuToggle = document.createElement('div');
                    mobileMenuToggle.classList.add('mobile-menu');

                    // <label htmlFor="menu-toggle-checkbox" id="menu-toggle">☰</label>
                    let menuToggle = document.createElement('label');
                    menuToggle.id = 'menu-toggle';
                    menuToggle.htmlFor = 'menu-toggle-checkbox';
                    menuToggle.textContent = '☰';
                    menuToggle.style.float = 'right';
                    menuToggle.style.fontSize = '32px'
                    menuToggle.style.width = '100%';
                    menuToggle.style.textAlign = 'right';
                    menuToggle.style.display = 'block';
                    mobileMenuToggle.appendChild(menuToggle);

                    // <input type="checkbox" id="menu-toggle-checkbox">
                    let checkBox = document.createElement('input');
                    checkBox.type = 'checkbox';
                    checkBox.id = 'menu-toggle-checkbox';
                    checkBox.style.visibility = 'hidden';
                    mobileMenuToggle.appendChild(checkBox);


                    navigationBarRow.style.display = 'block';
                    wrap(navigationBarRow, mobileMenuToggle);

                    function toggleNavigationBar() {
                        if (checkBox.checked) {
                            navigationBarRow.style.display = 'none';
                        } else {
                            navigationBarRow.style.display = 'block';
                        }
                    }
                    checkBox.addEventListener('change', toggleNavigationBar);
                }
            } else {
                //if exists, REMOVE elements for mobile browser
                if (mobileMenuToggle) {
                    const checkBox = document.querySelector('input#menu-toggle-checkbox');
                    const label = document.querySelector('label#menu-toggle');

                    mobileMenuToggle.removeChild(checkBox);
                    mobileMenuToggle.removeChild(label);

                    navigationBarRow.style.display = 'flex';

                    unwrap(mobileMenuToggle);
                }
            }
        }

        toggleMenu();
        window.addEventListener('resize', toggleMenu);
    }
);