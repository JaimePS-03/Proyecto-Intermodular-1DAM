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

// 1. Diccionario de traducciones
const translations = {
  es: {
    nav_about: "Nosotros",
    nav_history: "Historia",
    nav_menu: "Menú",
    nav_reserve: "Reservar",
    history_kicker: "Desde 2025",
    history_title: "Nuestra historia",
    history_text: "Nuestro proyecto intermodular comenzó con la idea de crear un espacio gastronómico que combinara tradición y modernidad , ofreciendo platos inspirados en la cocina mediterránea con un toque creativo. Desde nuestra apertura, hemos trabajado con pasión para ofrecer una experiencia culinaria única, utilizando ingredientes frescos y de proximidad para garantizar la calidad en cada plato."
  },
  en: {
    nav_about: "About Us",
    nav_history: "History",
    nav_menu: "Menu",
    nav_reserve: "Reserve",
    history_kicker: "Since 2025",
    history_title: "Our History",
    history_text: "Our intermodular project began with the idea of ​​creating a gastronomic space that combined tradition and modernity, offering dishes inspired by Mediterranean cuisine with a creative touch. Since our opening, we have worked passionately to offer a unique culinary experience, using fresh, locally sourced ingredients to guarantee quality in every dish."
  }
};

// 2. Comprobar si el usuario ya eligió un idioma antes (persistencia)
let currentLang = localStorage.getItem('app_lang') || 'es';

// 3. Función principal para cambiar el idioma
function setLanguage(lang) {
  currentLang = lang;
  localStorage.setItem('app_lang', lang); // Guarda la elección en el navegador
  document.documentElement.lang = lang; // Cambia el <html lang="es"> a "en"

  // Buscar todos los elementos que tienen el atributo data-i18n
  const elements = document.querySelectorAll('[data-i18n]');
  
  elements.forEach(element => {
    const key = element.getAttribute('data-i18n');
    // Si la clave existe en nuestro diccionario, actualizamos el texto
    if (translations[lang][key]) {
      element.textContent = translations[lang][key];
    }
  });

  // Actualizar el estilo visual de los botones (opcional, para saber cuál está activo)
  document.getElementById('btn-es').style.fontWeight = lang === 'es' ? 'bold' : 'normal';
  document.getElementById('btn-en').style.fontWeight = lang === 'en' ? 'bold' : 'normal';
}

// 4. Asignar los eventos a los botones
document.getElementById('btn-es').addEventListener('click', () => setLanguage('es'));
document.getElementById('btn-en').addEventListener('click', () => setLanguage('en'));

// 5. Iniciar la web con el idioma correcto
setLanguage(currentLang);
