const menu = document.querySelector(".menu-toggle");
const nav = document.querySelector(".site-nav");
if (menu) menu.addEventListener("click", () => nav.classList.toggle("open"));
