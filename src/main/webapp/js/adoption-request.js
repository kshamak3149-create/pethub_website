const requestForm = document.querySelector("#adoption-form");

if (requestForm) {
  requestForm.addEventListener("submit", () => {
    const submitButton = requestForm.querySelector("button[type='submit']");
    submitButton.disabled = true;
    submitButton.querySelector("span").textContent = "...";
  });
}
