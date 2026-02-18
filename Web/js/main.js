const burgerBtn = document.getElementById("burgerBtn");
const closeMenuBtn = document.getElementById("closeMenuBtn");
const mobileMenu = document.getElementById("mobileMenu");
const menuBackdrop = document.getElementById("menuBackdrop");

function openMenu(){
  mobileMenu.classList.add("is-open");
  mobileMenu.setAttribute("aria-hidden", "false");
  burgerBtn.setAttribute("aria-expanded", "true");
}

function closeMenu(){
  mobileMenu.classList.remove("is-open");
  mobileMenu.setAttribute("aria-hidden", "true");
  burgerBtn.setAttribute("aria-expanded", "false");
}

burgerBtn?.addEventListener("click", openMenu);
closeMenuBtn?.addEventListener("click", closeMenu);
menuBackdrop?.addEventListener("click", closeMenu);

document.querySelectorAll(".mobile-menu__link").forEach(link => {
  link.addEventListener("click", closeMenu);
});

// Reveal on scroll
const observer = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) entry.target.classList.add("is-visible");
  });
}, { threshold: 0.12 });

document.querySelectorAll(".reveal").forEach(el => observer.observe(el));

// Colapsar/expandir bloque derecho del nav (desktop)
const navArea = document.getElementById("navArea");
const navSecondary = document.getElementById("navSecondary");
const navCollapseBtn = document.getElementById("navCollapseBtn");

function updateNavShift(){
  if (!navArea || !navSecondary) return;
  const gap = 14; // debe coincidir con el gap del CSS (.nav-area)
  const w = Math.ceil(navSecondary.getBoundingClientRect().width);
  navArea.style.setProperty("--navShift", `${w + gap}px`);
}

function toggleRightNav(){
  if (!navArea || !navCollapseBtn) return;

  const isCollapsed = navArea.classList.toggle("is-collapsed");
  if (isCollapsed) {
    updateNavShift();
  } else {
    navArea.style.setProperty("--navShift", "0px");
  }
  navCollapseBtn.setAttribute("aria-pressed", String(isCollapsed));
}

navCollapseBtn?.addEventListener("click", toggleRightNav);

window.addEventListener("resize", () => {
  if (navArea?.classList.contains("is-collapsed")) updateNavShift();
});

// Toggle del nav desktop al clicar el brand
const siteHeader = document.querySelector(".site-header");
const brandLink = document.querySelector(".brand");
const navClip = document.getElementById("navClip");
const navDesktop = document.getElementById("navDesktop");

function syncNavWidth(){
  if (!navClip || !navDesktop) return;
  // Medimos el ancho real del nav para que el max-width sea exacto
  navClip.style.setProperty("--navW", `${navDesktop.scrollWidth}px`);
}

function toggleDesktopNav(e){
  // En móvil tu CSS ya oculta .nav-desktop, así que dejamos el comportamiento normal
  if (window.matchMedia("(max-width: 840px)").matches) return;

  // Si el usuario quiere navegar (Ctrl/Cmd click), no interceptamos
  if (e.ctrlKey || e.metaKey || e.shiftKey || e.button !== 0) return;

  e.preventDefault();
  syncNavWidth();
  siteHeader?.classList.toggle("is-nav-hidden");
}

syncNavWidth();
window.addEventListener("resize", syncNavWidth);
brandLink?.addEventListener("click", toggleDesktopNav);

// Carrusel autoplay
(function(){
  const viewport = document.getElementById("carousel");
  if (!viewport) return;

  const track = viewport.querySelector(".carousel__track");
  if (!track) return;

  const slides = Array.from(track.children);
  if (slides.length < 2) return;

  // Clonamos la primera slide para hacer loop suave
  const firstClone = slides[0].cloneNode(true);
  track.appendChild(firstClone);

  let index = 0;
  let timer = null;
  const intervalMs = 4200;

  function setTranslate(i, withTransition = true){
    track.style.transition = withTransition
      ? "transform .55s cubic-bezier(.2,.9,.2,1)"
      : "none";
    track.style.transform = `translateX(-${i * 100}%)`;
  }

  function next(){
    index += 1;
    setTranslate(index, true);

    // Si llegamos al clon, saltamos (sin transición) al inicio real
    if (index === slides.length) {
      window.setTimeout(() => {
        index = 0;
        setTranslate(index, false);
      }, 560);
    }
  }

  function play(){
    if (timer) return;
    timer = window.setInterval(next, intervalMs);
  }

  function stop(){
    window.clearInterval(timer);
    timer = null;
  }

  // Arranque
  setTranslate(0, false);
  play();

  // Pausa al pasar el ratón / tocar
  viewport.addEventListener("pointerenter", stop);
  viewport.addEventListener("pointerleave", play);

  // Evita gastar recursos si cambias de pestaña
  document.addEventListener("visibilitychange", () => {
    if (document.hidden) stop();
    else play();
  });
})();

// Tema claro/oscuro
(() => {
  const btn = document.getElementById("themeSwitch");
  if (!btn) return;

  const KEY = "theme";
  const root = document.documentElement;

  const apply = (mode) => {
    const dark = mode === "dark";
    root.classList.toggle("theme-dark", dark);
    btn.setAttribute("aria-pressed", dark ? "true" : "false");
  };

  apply(localStorage.getItem(KEY) || "light");

  btn.addEventListener("click", () => {
    const next = root.classList.contains("theme-dark") ? "light" : "dark";
    localStorage.setItem(KEY, next);
    apply(next);
  });
})();

apply(localStorage.getItem(KEY) || "dark");  // Cambia "light" por "dark"

