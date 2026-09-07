const save = document.querySelector("#save-pet");
if (save)
  save.addEventListener("click", () => {
    save.classList.toggle("saved");
    save.innerHTML = save.classList.contains("saved")
      ? "&#9829; Saved to your pets"
      : "&#9825; Save Milo";
  });
