const search = document.querySelector("#pet-search");
const species = document.querySelector("#species-filter");
const size = document.querySelector("#size-filter");
const clear = document.querySelector("#clear-filters");
const empty = document.querySelector("#empty-state");
const cards = [...document.querySelectorAll(".pet-card")];
function filterPets() {
  const query = (search.value || "").toLowerCase();
  let visible = 0;
  cards.forEach((card) => {
    const matchText = (card.dataset.name + " " + card.dataset.breed).includes(
      query,
    );
    const matchSpecies =
      !species.value || card.dataset.species === species.value;
    const matchSize = !size.value || card.dataset.size === size.value;
    const show = matchText && matchSpecies && matchSize;
    card.style.display = show ? "block" : "none";
    if (show) visible++;
  });
  empty.style.display = visible ? "none" : "block";
}
[search, species, size].forEach((control) =>
  control.addEventListener("input", filterPets),
);
clear.addEventListener("click", () => {
  search.value = "";
  species.value = "";
  size.value = "";
  filterPets();
});
document.querySelectorAll(".heart-button").forEach((button) =>
  button.addEventListener("click", () => {
    button.classList.toggle("saved");
    button.innerHTML = button.classList.contains("saved")
      ? "&#9829;"
      : "&#9825;";
  }),
);
