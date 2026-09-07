const adminForm = document.querySelector("#admin-login-form");
if (adminForm) {
  adminForm.addEventListener("submit", (event) => {
    const email = adminForm.querySelector("input[name='email']").value.trim();
    const password = adminForm
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
