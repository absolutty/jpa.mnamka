let scrollToTopButton = document.getElementById("scroll-to-top");

//SHOW/HIDE scroll button
window.onscroll = function () {
    if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
        scrollToTopButton.style.display = "block";
    } else {
        scrollToTopButton.style.display = "none";
    }
};

//SCROLL to top
scrollToTopButton.addEventListener("click", function () {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
});
