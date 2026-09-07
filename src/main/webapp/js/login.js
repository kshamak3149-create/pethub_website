const loginForm = document.querySelector("#login-form");
if (loginForm) {
  loginForm.addEventListener("submit", (event) => {
    const email = loginForm.querySelector("input[name='email']").value.trim();
    const password = loginForm
      .querySelector("input[name='password']")
      .value.trim();
    const message = document.querySelector("#form-message");

    if (!email || !password) {
      event.preventDefault();
      if (message) {
        message.textContent = "Please enter both email and password.";
      }
    }
  });
}
