document.querySelectorAll(".table-action").forEach((button) =>
  button.addEventListener("click", () => {
    button.textContent = "Opened";
  }),
);
