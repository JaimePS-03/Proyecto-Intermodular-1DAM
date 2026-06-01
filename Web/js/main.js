/* =========================
   ATAJOS
   ========================= */

const $ = s => document.querySelector(s);
const $$ = s => document.querySelectorAll(s);

/* =========================
   MOBILE MENU
   ========================= */

const burger = $("#burgerBtn");
const closeBtn = $("#closeMenuBtn");
const mobile = $("#mobileMenu");
const backdrop = $("#menuBackdrop");

const toggleMenu = open => {
  mobile?.classList.toggle("is-open", open);
  mobile?.setAttribute("aria-hidden", !open);
  burger?.setAttribute("aria-expanded", open);
};

burger?.addEventListener("click", () => toggleMenu(true));
[closeBtn, backdrop].forEach(el =>
  el?.addEventListener("click", () => toggleMenu(false))
);

$$(".mobile-menu__link").forEach(l =>
  l.addEventListener("click", () => toggleMenu(false))
);

/* =========================
   REVEAL ON SCROLL
   ========================= */

new IntersectionObserver(
  entries => entries.forEach(e =>
    e.isIntersecting && e.target.classList.add("is-visible")
  ),
  { threshold: .12 }
).observe = undefined;

$$(".reveal").forEach(el =>
  new IntersectionObserver(
    e => e[0].isIntersecting && el.classList.add("is-visible"),
    { threshold: .12 }
  ).observe(el)
);

/* =========================
   CAROUSEL (AUTOPLAY LOOP)
   ========================= */

(() => {
  const viewport = $("#carousel");
  const track = viewport?.querySelector(".carousel__track");
  if (!viewport || !track) return;

  const slides = [...track.children];
  if (slides.length < 2) return;

  track.append(slides[0].cloneNode(true));

  let i = 0, timer;
  const delay = 4200;

  const move = (index, anim = true) => {
    track.style.transition = anim ? "transform .55s ease" : "none";
    track.style.transform = `translateX(-${index * 100}%)`;
  };

  const next = () => {
    i++;
    move(i);
    if (i === slides.length)
      setTimeout(() => (i = 0, move(0, false)), 560);
  };

  const play = () => timer ??= setInterval(next, delay);
  const stop = () => (clearInterval(timer), timer = null);

  move(0, false);
  play();

  viewport.addEventListener("pointerenter", stop);
  viewport.addEventListener("pointerleave", play);
  document.addEventListener("visibilitychange",
    () => document.hidden ? stop() : play()
  );
})();

/* =========================
   DARK / LIGHT MODE
   ========================= */

(() => {
  const btn = $("#themeSwitch");
  if (!btn) return;

  const KEY = "theme";
  const root = document.documentElement;

  const setTheme = mode => {
    const dark = mode === "dark";
    root.classList.toggle("theme-dark", dark);
    btn.setAttribute("aria-pressed", dark);
    localStorage.setItem(KEY, mode);
  };

  setTheme(localStorage.getItem(KEY) || "light");

  btn.addEventListener("click", () =>
    setTheme(root.classList.contains("theme-dark") ? "light" : "dark")
  );
})();

/* =========================
   LOGIN & RESERVATION MODAL
   ========================= */
lucide.createIcons();

(() => {
  const reserveBtn = document.getElementById('reserveBtn');
  const overlay = document.getElementById('loginOverlay');
  const welcomeModal = document.getElementById('welcomeModal');
  const loginModal = document.getElementById('loginModal');
  const openLoginForm = document.getElementById('openLoginForm');
  const closeOverlay = document.getElementById('closeOverlay');
  const cancelLogin = document.getElementById('cancelLogin');
  const loginForm = document.getElementById('loginForm');
  const formMessage = document.getElementById('formMessage');

  const reservationModal = document.getElementById('reservationModal');
  const reservationForm = document.getElementById('reservationForm');
  const reservationMessage = document.getElementById('reservationMessage');
  const cancelReservation = document.getElementById('cancelReservation');

  let isLogged = sessionStorage.getItem('isLogged') === 'true';

  const openModal = () => {
    overlay.classList.add('active');
    document.body.style.overflow = 'hidden';
  };

  const closeModal = () => {
    overlay.classList.remove('active');
    document.body.style.overflow = '';
    welcomeModal.classList.remove('hidden');
    loginModal.classList.add('hidden');
    reservationModal.classList.add('hidden');
    loginForm.reset();
    formMessage.textContent = '';
  };

  reserveBtn.addEventListener('click', () => {
    if (isLogged) {
      welcomeModal.classList.add('hidden');
      loginModal.classList.add('hidden');
      reservationModal.classList.remove('hidden');
      openModal();
      return;
    }
    openModal();
  });

  openLoginForm.addEventListener('click', () => {
    welcomeModal.classList.add('hidden');
    loginModal.classList.remove('hidden');
  });

  closeOverlay.addEventListener('click', closeModal);
  cancelLogin.addEventListener('click', closeModal);
  cancelReservation.addEventListener('click', closeModal);

  overlay.addEventListener('click', e => {
    if (e.target === overlay) {
      closeModal();
    }
  });

  /* =======================================================
     1. FORMULARIO DE RESERVAS
     ======================================================= */
  reservationForm.addEventListener('submit', e => {
    e.preventDefault();
    const date = document.getElementById('reservationDate').value;
    const time = document.getElementById('reservationTime').value;
    const guests = document.getElementById('reservationGuests').value;
    const tipo = document.getElementById('reservationType').value;

    if (!date || !time || !guests || !tipo) {
      reservationMessage.style.color = '#ef4444';
      reservationMessage.textContent = 'Completa todos los campos obligatorios.';
      return;
    }

    const reservationDateTime = new Date(`${date}T${time}`);
    const now = new Date();

if (reservationDateTime < now) {
  reservationMessage.style.color = '#ef4444';
  reservationMessage.textContent = 'No puedes hacer una reserva en una fecha u hora anterior a la actual.';
  return;
}

    const reservationData = new FormData();
    reservationData.append('date', date);
    reservationData.append('time', time);
    reservationData.append('guests', guests);
    reservationData.append('tipo', tipo);

    reservationMessage.style.color = '#3b82f6';
    reservationMessage.textContent = 'Procesando reserva...';

    fetch('../src/guardar_reserva.php', {
      method: 'POST',
      body: reservationData
    })
    .then(response => response.json().then(data => ({ status: response.status, data })))
    .then(res => {
      if (res.status === 200) {
        reservationMessage.style.color = '#22c55e';
        reservationMessage.textContent = res.data.mensaje;

        setTimeout(() => {
          closeModal();
          reservationForm.reset();
        }, 1400);
      } else {
        reservationMessage.style.color = '#ef4444';
        reservationMessage.textContent = res.data.mensaje;
      }
    })
    .catch(error => {
      console.error("Error:", error);
      reservationMessage.style.color = '#ef4444';
      reservationMessage.textContent = 'Error de comunicación con el servidor.';
    });
  });

  /* =======================================================
     2. FORMULARIO DE LOGIN
     ======================================================= */

  if (loginForm && loginForm.email) {
    loginForm.email.value = localStorage.getItem('usuario_correo') || '';
  }

  loginForm.addEventListener('submit', e => {
    e.preventDefault();

    const email = loginForm.email.value.trim();
    const password = loginForm.password.value.trim();
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email || !password) {
      formMessage.style.color = '#ef4444';
      formMessage.textContent = 'Completa todos los campos.';
      return;
    }

    if (!emailPattern.test(email)) {
      formMessage.style.color = '#ef4444';
      formMessage.textContent = 'Introduce un correo válido.';
      return;
    }

    if (password.length < 6) {
      formMessage.style.color = '#ef4444';
      formMessage.textContent = 'La contraseña debe tener mínimo 6 caracteres.';
      return;
    }

    localStorage.setItem('usuario_correo', email);

    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);

    formMessage.style.color = '#3b82f6';
    formMessage.textContent = 'Comprobando credenciales...';

    fetch('../src/login.php', {
      method: 'POST',
      body: formData
    })
    .then(response => {
      return response.json().then(data => ({ status: response.status, data }));
    })
    .then(res => {
      if (res.status === 200) {
        isLogged = true;
        sessionStorage.setItem('isLogged', 'true');
        formMessage.style.color = '#22c55e';
        formMessage.textContent = res.data.mensaje;

        setTimeout(() => {
          loginModal.classList.add('hidden');
          reservationModal.classList.remove('hidden');
          formMessage.textContent = '';
        }, 900);

      } else {
        formMessage.style.color = '#ef4444';
        formMessage.textContent = res.data.mensaje;
      }
    })
    .catch(error => {
      console.error("Error en la petición:", error);
      formMessage.style.color = '#ef4444';
      formMessage.textContent = 'Error al conectar con el servidor de autenticación.';
    });
  });

})();


/* =========================
   MAPA
   ========================= */


document.addEventListener("DOMContentLoaded", () => {

  const map = new maplibregl.Map({
    container: 'map',
    style: {
      version: 8,
      sources: {
        satellite: {
          type: 'raster',
          tiles: [
            'https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}'
          ],
          tileSize: 256
        },
        labels: {
          type: 'raster',
          tiles: [
            'https://services.arcgisonline.com/ArcGIS/rest/services/Reference/World_Boundaries_and_Places/MapServer/tile/{z}/{y}/{x}'
          ],
          tileSize: 256
        }
      },
      layers: [
        {
          id: 'satellite',
          type: 'raster',
          source: 'satellite'
        },
        {
          id: 'labels',
          type: 'raster',
          source: 'labels'
        }
      ]
    },
    center: [-0.095997, 38.659353],
    zoom: 16,
    pitch: 60,
    bearing: 0
  });

  map.addControl(
    new maplibregl.NavigationControl(),
    'top-right'
  );

});

/* =========================
   ES/EN
   ========================= */

const translations = {
  es: {
    nav_about: "Nosotros",
    nav_history: "Historia",
    nav_menu: "Menú",
    nav_reserve: "Reservar",
    history_kicker: "Desde 2025",
    history_title: "Nuestra historia",
    history_text: "Nuestro proyecto intermodular comenzó con la idea de crear un espacio gastronómico que combinara tradición y modernidad , ofreciendo platos inspirados en la cocina mediterránea con un toque creativo. Desde nuestra apertura, hemos trabajado con pasión para ofrecer una experiencia culinaria única, utilizando ingredientes frescos y de proximidad para garantizar la calidad en cada plato.",
    kicker: "Carta",
    car_title: "Restaurante Demo",
    lead: "Cocina mediterránea creativa y ambiente familiar.",
    cta_title: "El menú",
    cta: "Menú ficticio con platos de la zona, producto de proximidad y opciones para todos los gustos.",
    cta_btn: "Explorar el menú",
    section1_title: "Platos principales",
    section1_p: "Selección inventada para practicar maquetación de tarjetas.",
    dish1_title: "Arroz del día",
    dish1: "Arroz meloso con “toque cítrico” y caldo de mar (ficticio).",
    dish1_tag: "Recomendado",
    dish2_title: "Brasa mediterránea",
    dish2: "Corte a la parrilla con guarnición de temporada (inventado).",
    dish2_tag: "Brasas",
    dish3_title: "Pescado de lonja demo",
    dish3: "Plancha suave, aceite aromático y verduras.",
    dish3_tag: "Ligero",
    dish4_title: "Tarta de naranja",
    dish4: "Bizcocho esponjoso y crema suave.",
    dish4_tag: "Clásico",
    dish5_title: "Helado artesano",
    dish5: "Sabores rotativos: vainilla, cacao y fruta.",
    dish5_tag: "Frío",
    dish6_title: "Crema marina",
    dish6: "Textura ligera con caramelo salado.",
    dish6_tag: "Nuevo",
    section2_title: "Postres",
    section2_p: "Pequeños finales dulces también ficticios.",
    section3_title: "Nosotros",
    section3_p: "Somos un restaurante familiar de ambiente alegre: comedor interior, terraza y un rincón mini‑aventura para los peques.",
    card1_title: "Comedor interior",
    card1: "Espacio acogedor, mesas amplias y una iluminación suave para comidas tranquilas.",
    card2_title: "Terraza",
    card2: "Zona exterior con sombra, brisa y vistas “de postal” para alargar la sobremesa.",
    card3_title: "Reservas",
    card3: "Un lugar perfecto para celebrar ocasiones especiales con amigos y familiares.",
    time1: "Apertura del primer local.",
    time2: "Ampliación con terraza y parrilla.",
    time3: "Presentacion de nuestro proyecto intermodular",
    footer: "© 2026 Restaurante Demo. Proyecto Intermodular",
    top: "Volver arriba",
    btn_reserve: "Reservar"
  },
  en: {
    nav_about: "About Us",
    nav_history: "History",
    nav_menu: "Menu",
    nav_reserve: "Reserve",
    history_kicker: "Since 2025",
    history_title: "Our History",
    history_text: "Our intermodular project began with the idea of ​​creating a gastronomic space that combined tradition and modernity, offering dishes inspired by Mediterranean cuisine with a creative touch. Since our opening, we have worked passionately to offer a unique culinary experience, using fresh, locally sourced ingredients to guarantee quality in every dish.",
    kicker: "Menu",
    car_title: "Demo Restaurant",
    lead: "Creative Mediterranean cuisine and a family-friendly atmosphere.",
    cta_title: "The Menu",
    cta: "Fictional menu featuring local dishes, locally sourced ingredients, and options for everyone.",
    cta_btn: "Explore the menu",
    section1_title: "Main Dishes",
    section1_p: "Fictional selection created to practice card layout.",
    dish1_title: "Rice of the day",
    dish1: "Creamy rice with a 'citrus touch' and seafood broth (fictional).",
    dish1_tag: "Recommended",
    dish2_title: "Mediterranean grill",
    dish2: "Grilled cut with seasonal garnish (fictional).",
    dish2_tag: "Grill",
    dish3_title: "Demo market fish",
    dish3: "Gently grilled, aromatic oil, and vegetables.",
    dish3_tag: "Light",
    dish4_title: "Orange cake",
    dish4: "Fluffy sponge cake and smooth cream.",
    dish4_tag: "Classic",
    dish5_title: "Artisanal ice cream",
    dish5: "Rotating flavors: vanilla, cocoa, and fruit.",
    dish5_tag: "Cold",
    dish6_title: "Marine cream",
    dish6: "Light texture with salted caramel.",
    dish6_tag: "New",
    section2_title: "Desserts",
    section2_p: "Small sweet endings, also fictional.",
    section3_title: "About Us",
    section3_p: "We are a family restaurant with a cheerful atmosphere: indoor dining room, terrace, and a mini-adventure corner for kids.",
    card1_title: "Indoor Dining",
    card1: "Cozy space, spacious tables, and soft lighting for peaceful meals.",
    card2_title: "Terrace",
    card2: "Outdoor area with shade, breeze, and 'postcard' views to extend your after-dinner conversation.",
    card3_title: "Reservations",
    card3: "A perfect place to celebrate special occasions with friends and family.",
    time1: "Opening of the first location.",
    time2: "Expansion with terrace and grill.",
    time3: "Presentation of our intermodular project",
    footer: "© 2026 Demo Restaurant. Intermodular Project",
    top: "Back to top",
    btn_reserve: "Reserve"
  }
};


let currentLang = localStorage.getItem('app_lang') || 'es';

function setLanguage(lang) {
  currentLang = lang;
  localStorage.setItem('app_lang', lang);
  document.documentElement.lang = lang;

  const elements = document.querySelectorAll('[data-lang]');
  
  elements.forEach(element => {
    const key = element.getAttribute('data-lang');
    if (translations[lang][key]) {
      element.textContent = translations[lang][key];
    }
  });

  document.getElementById('btn-es').style.fontWeight = lang === 'es' ? 'bold' : 'normal';
  document.getElementById('btn-en').style.fontWeight = lang === 'en' ? 'bold' : 'normal';
}

document.getElementById('btn-es').addEventListener('click', () => setLanguage('es'));
document.getElementById('btn-en').addEventListener('click', () => setLanguage('en'));

setLanguage(currentLang);
