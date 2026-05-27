/* =========================
   CONFIG
   ========================= */

const KEY = "menu-dishes";

/* =========================
   DATA
   ========================= */

let dishes = JSON.parse(localStorage.getItem(KEY)) || [];

/* =========================
   ELEMENTOS
   ========================= */

const catList = document.querySelector("#catList");
const grid = document.querySelector("#cardsGrid");
const title = document.querySelector("#activeCategoryTitle");

let active = "Todos";

/* =========================
   UTILIDADES
   ========================= */

const categories = () => ["Todos", ...new Set(dishes.map(d => d.category))];

/* =========================
   RENDER
   ========================= */

function renderCategories() {
  catList.innerHTML = categories().map(c =>
    `<button class="cat-btn ${c === active ? "is-active" : ""}" data-cat="${c}">${c}</button>`
  ).join("");

  catList.querySelectorAll("button").forEach(b =>
    b.onclick = () => (active = b.dataset.cat, render())
  );
}

function renderCards() {
  const filtered = active === "Todos"
    ? dishes
    : dishes.filter(d => d.category === active);

  title.textContent = active;

  grid.innerHTML = filtered.map(d => `
    <article class="menu-card">
      <div class="menu-card__img">
        <img src="${d.image || ""}" alt="${d.name}" loading="lazy">
      </div>
      <div class="menu-card__body">
        <div class="menu-card__top">
          <h3>${d.name}</h3>
          <span class="menu-card__price">${d.price}</span>
        </div>
        <div class="menu-card__meta">
          <span class="people-badge">${d.people}</span>
        </div>
      </div>
    </article>
  `).join("");
}

function render() {
  renderCategories();
  renderCards();
}

function save() {
  localStorage.setItem(KEY, JSON.stringify(dishes));
}

dishes.push(
  {
    id: "002",
    name: "Pizza Margarita",
    price: "11,90 €",
    people: "1-2",
    category: "Popi",
    image: "img/logo.jpeg"
  },  {
    id: "002",
    name: "Pizza Margarita",
    price: "11,90 €",
    people: "1-2",
    category: "Popi",
    image: "img/logo.jpeg"
  },{
    id: "002",
    name: "Pizza Margarita",
    price: "11,90 €",
    people: "1-2",
    category: "Popi2",
    image: "img/logo.jpeg"
  },  {
    id: "002",
    name: "Pizza Margarita",
    price: "11,90 €",
    people: "1-2",
    category: "Popi",
    image: "img/logo.jpeg"
  },  {
    id: "002",
    name: "Pizza Margarita",
    price: "11,90 €",
    people: "1-2",
    category: "Popi",
    image: "img/logo.jpeg"
  }
);
render();
localStorage.removeItem(KEY);