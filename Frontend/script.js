// Navbar scroll effect
window.addEventListener("scroll", function () {
  const navbar = document.getElementById("navbar");
  if (window.scrollY > 50) {
    navbar.classList.add("scrolled");
  } else {
    navbar.classList.remove("scrolled");
  }
});

// Mobile menu toggle
const hamburger = document.getElementById("hamburger");
const navMenu = document.getElementById("nav-menu");

hamburger.addEventListener("click", function () {
  hamburger.classList.toggle("active");
  navMenu.classList.toggle("active");
});

// Close mobile menu when clicking on a link
document.querySelectorAll(".nav-link").forEach((link) => {
  link.addEventListener("click", function () {
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
  });
});

// Smooth scrolling for navigation links
document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();
    const target = document.querySelector(this.getAttribute("href"));
    if (target) {
      target.scrollIntoView({
        behavior: "smooth",
        block: "start",
      });
    }
  });
});

// Contact form submission
document
  .querySelector(".contact-form")
  .addEventListener("submit", function (e) {
    e.preventDefault();
    // alert("Thank you for your message! We will get back to you soon.");
    whatsapp();
    this.reset();
  });

// Send to whatsapp
function whatsapp() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var phone = document.getElementById("phone").value;
  var product = document.getElementById("product").value;
  var message = document.getElementById("message").value;

  var whatsappurl = "https://wa.me/7320092809?text="
  + "Name: " + name + "%0a"
  + "Email: " + email + "%0a"
  + "Mobile No.: " + phone + "%0a"
  + "Product: " + product + "%0a"
  + "Message: " + message + "%0a"

  window.open(whatsappurl,"_blank").focus();
}

// Animate elements on scroll
const observerOptions = {
  threshold: 0.1,
  rootMargin: "0px 0px -50px 0px",
};

const observer = new IntersectionObserver(function (entries) {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      entry.target.style.opacity = "1";
      entry.target.style.transform = "translateY(0)";
    }
  });
}, observerOptions);

// Observe elements for animation
document
  .querySelectorAll(
    ".product-card, .service-card, .testimonial-card, .feature-item"
  )
  .forEach((el) => {
    el.style.opacity = "0";
    el.style.transform = "translateY(30px)";
    el.style.transition = "opacity 0.6s ease, transform 0.6s ease";
    observer.observe(el);
  });

// Data fetching
async function loadProducts() {
  const response = await fetch("http://localhost:8080/products");
  const products = await response.json();

  const container = document.querySelector(".products-grid");
  products.forEach((product) => {
    const div = document.createElement("div");
    div.classList.add("product-card");

    div.innerHTML = `
          <div class="product-image">
                        <img src="data:${product.imageType};base64,${
      product.imageBase64
    }" alt="Agricultural Sprayers" />
                        <div class="product-overlay">
                            <button class="product-btn" onclick="viewDetails('${
                              product.id
                            }')" >View Details</button>
                        </div>
                    </div>
                    <div class="product-content">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <ul class="product-features">
                            ${product.points
                              .map((p) => `<li>${p}</li>`)
                              .join("")}
                        </ul>
                    </div>
        `;
    container.appendChild(div);
  });
}

loadProducts();

// Product Details page
async function viewDetails(productId) {
  window.location.href = `products.html?id=${productId}`;
}
