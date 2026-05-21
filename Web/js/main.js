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
   LOGIN MODAL
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

  const reservationModal =
    document.getElementById('reservationModal');

  const reservationForm =
    document.getElementById('reservationForm');

  const reservationMessage =
    document.getElementById('reservationMessage');

  const cancelReservation =
    document.getElementById('cancelReservation');

  let isLogged = false;

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

  overlay.addEventListener('click', e => {
    if (e.target === overlay) {
      closeModal();
    }
  });

  loginForm.addEventListener('submit', e => {

    cancelReservation.addEventListener('click', closeModal);

    reservationForm.addEventListener('submit', e => {

      e.preventDefault();

      const date =
        document.getElementById('reservationDate').value;

      const time =
        document.getElementById('reservationTime').value;

      const guests =
        document.getElementById('reservationGuests').value;

      if (!date || !time || !guests) {

        reservationMessage.textContent =
          'Completa los campos obligatorios.';

        return;
      }

      reservationMessage.style.color = '#22c55e';

      reservationMessage.textContent =
        'Reserva realizada correctamente.';

      setTimeout(() => {

        closeModal();

        reservationForm.reset();

      }, 1400);

    });

    e.preventDefault();

    const username = loginForm.username.value.trim();
    const email = loginForm.email.value.trim();
    const password = loginForm.password.value.trim();

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!username || !email || !password) {
      formMessage.textContent = 'Completa todos los campos.';
      return;
    }

    if (!emailPattern.test(email)) {
      formMessage.textContent = 'Introduce un correo válido.';
      return;
    }

    if (password.length < 6) {
      formMessage.textContent =
        'La contraseña debe tener mínimo 6 caracteres.';
      return;
    }

    isLogged = true;

    formMessage.style.color = '#22c55e';

    formMessage.textContent = 'Inicio de sesión correcto.';

    setTimeout(() => {

      loginModal.classList.add('hidden');

      reservationModal.classList.remove('hidden');

    }, 900);

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
