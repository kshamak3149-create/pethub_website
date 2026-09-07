const registerForm = document.querySelector("#register-form");
if (registerForm) {
  registerForm.addEventListener("submit", (event) => {
    const name = registerForm.querySelector("input[name='name']").value.trim();
    const email = registerForm
      .querySelector("input[name='email']")
      .value.trim();
    const password = registerForm
      .querySelector("input[name='password']")
      .value.trim();
    const phone = registerForm
      .querySelector("input[name='phone']")
      .value.trim();
    const address = registerForm
      .querySelector("textarea[name='address']")
      .value.trim();

    if (!name || !email || !password || !phone || !address) {
      event.preventDefault();
      alert("Please fill in all fields before creating your account.");
      return;
    }

    alert("Successfully registered! You can now sign in.");
  });
}
