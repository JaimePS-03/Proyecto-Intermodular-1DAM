/* =========================
   ELEMENTOS DE LA INTERFAZ
   ========================= */
const catList = document.querySelector("#catList");
const grid = document.querySelector("#cardsGrid");
const title = document.querySelector("#activeCategoryTitle");

let dishes = [];
let active = "Todos";

/* =========================
   UTILIDADES
   ========================= */
const categories = () => ["Todos", ...new Set(dishes.map(d => d.category))];

/* =========================
   FUNCIONES DE RENDERIZADO
   ========================= */
function renderCategories() {
  catList.innerHTML = categories().map(c =>
    `<button class="cat-btn ${c === active ? "is-active" : ""}" data-cat="${c}">${c}</button>`
  ).join("");

  catList.querySelectorAll("button").forEach(b =>
    b.onclick = () => {
      active = b.dataset.cat;
      render();
    }
  );
}

function renderCards() {
  const filtered = active === "Todos" ? dishes : dishes.filter(d => d.category === active);
  title.textContent = active;

  grid.innerHTML = filtered.map(d => `
    <article class="menu-card">
      <div class="menu-card__img">
        <img src="${d.image}" alt="${d.name}" loading="lazy">
      </div>
      <div class="menu-card__body">
        <div class="menu-card__top">
          <h3>${d.name}</h3>
          <span class="menu-card__price">${d.price}</span>
        </div>
        <div class="menu-card__meta">
          <span class="people-badge">${d.people}</span>
          
          ${renderAllergenIcons(d.allergens)}
        </div>
      </div>
    </article>
  `).join("");
}

function renderAllergenIcons(allergensList) {
  if (!allergensList || allergensList.length === 0) return '';

  const mapping = {
    "Gluten": { letter: "G", cssClass: "ale-gluten" },
    "Crustáceos": { letter: "C", cssClass: "ale-crustaceos" },
    "Huevos": { letter: "H", cssClass: "ale-huevos" },
    "Pescado": { letter: "P", cssClass: "ale-pescado" },
    "Leche": { letter: "L", cssClass: "ale-leche" },
    "Moluscos": { letter: "M", cssClass: "ale-moluscos" }
  };

  return `<div class="allergen-wrapper">` + 
    allergensList.map(name => {
      const item = mapping[name];
      if (!item) return '';
      return `<span class="allergen-badge ${item.cssClass}" title="${name}">${item.letter}</span>`;
    }).join('') + 
  `</div>`;
}

function render() {
  renderCategories();
  renderCards();
}

/* =======================================================
   CONEXIÓN REAL CON LA BASE DE DATOS (FETCH)
   ======================================================= */
function cargarPlatosDesdeDB() {
  const imagenesPorCategoria = {
    "Arroces y paellas": "img/arroces.jpg",
    "Aperitivos": "img/entrantes.jpg",
    "Postres": "img/postres.jpg",
    "Pizzas": "img/pizzas.jpg",
    "Carnes": "img/carnes.jpg",
    "Platos combinados": "img/combinados.jpg",
    "Helados": "img/helados.jpg",
    "Pan y ensaladas": "img/pan.jpg"
  };

  fetch('../src/obtener_platos.php')
    .then(response => response.json())
    .then(data => {
      dishes = data.map(plato => ({
        id: plato.n_plato,
        name: plato.nombre,
        price: parseFloat(plato.precio).toFixed(2) + " €",
        category: plato.tipo,
        people: "1-2",
        image: imagenesPorCategoria[plato.tipo] || "img/logo.jpeg",
        allergens: plato.alergenos
      }));
      render();
    })
    .catch(err => console.error("Error:", err));
}

cargarPlatosDesdeDB();