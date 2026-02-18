const dishes = [
  { id: "arroz-dia", name: "Arroz del día", price: "14,90 €", people: "2-4", category: "Arroces" },
  { id: "brasa-mediterranea", name: "Brasa mediterránea", price: "19,50 €", people: "1-2", category: "Brasas" },
  { id: "pescado-lonja", name: "Pescado de lonja (demo)", price: "18,90 €", people: "1-2", category: "Pescados" },
  { id: "tarta-naranja", name: "Tarta de naranja", price: "5,20 €", people: "1", category: "Postres" },
  { id: "helado-artesano", name: "Helado artesano", price: "4,60 €", people: "1", category: "Postres" },
  { id: "crema-marina", name: "Crema marina", price: "4,90 €", people: "1", category: "Postres" },
];

// semillas “random” por carga (para que cada refresh cambie fotos)
const imageSeedById = Object.fromEntries(
  dishes.map(d => [d.id, Math.random().toString(16).slice(2)])
);

function imgUrl(d){
  // picsum: imagen aleatoria estable por (id + seed)
  const seed = encodeURIComponent(`${d.id}-${imageSeedById[d.id]}`);
  return `https://picsum.photos/seed/${seed}/800/520`;
}

const categories = ["Todos", ...Array.from(new Set(dishes.map(d => d.category)))];

const catList = document.getElementById("catList");
const cardsGrid = document.getElementById("cardsGrid");
const activeCategoryTitle = document.getElementById("activeCategoryTitle");

let activeCategory = "Todos";

function peopleIconSvg(){
  return `
    <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M12 12a4 4 0 1 0-4-4 4 4 0 0 0 4 4Z" stroke="currentColor" stroke-width="2" />
      <path d="M4 22c0-4 4-7 8-7s8 3 8 7" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
    </svg>
  `;
}

function renderCategories(){
  catList.innerHTML = categories.map(cat => {
    const isActive = cat === activeCategory ? "is-active" : "";
    return `<button class="cat-btn ${isActive}" type="button" data-cat="${cat}">${cat}</button>`;
  }).join("");

  catList.querySelectorAll(".cat-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      activeCategory = btn.dataset.cat;
      renderCategories();
      renderCards();
    });
  });
}

function renderCards(){
  activeCategoryTitle.textContent = activeCategory;

  const filtered = activeCategory === "Todos"
    ? dishes
    : dishes.filter(d => d.category === activeCategory);

  cardsGrid.style.opacity = "0";
  cardsGrid.style.transform = "translateY(4px)";

  window.requestAnimationFrame(() => {
    cardsGrid.innerHTML = filtered.map(d => `
      <article class="menu-card">
        <div class="menu-card__img">
          <img src="${imgUrl(d)}" alt="Imagen aleatoria de ${d.name}" loading="lazy" />
        </div>
        <div class="menu-card__body">
          <div class="menu-card__top">
            <h3>${d.name}</h3>
            <span class="menu-card__price">${d.price}</span>
          </div>
          <div class="menu-card__meta">
            <span class="people-badge" title="Raciones aproximadas">
              ${peopleIconSvg()}
              ${d.people}
            </span>
          </div>
        </div>
      </article>
    `).join("");

    cardsGrid.style.transition = "opacity .18s ease, transform .18s ease";
    cardsGrid.style.opacity = "1";
    cardsGrid.style.transform = "translateY(0)";
  });
}

renderCategories();
renderCards();
