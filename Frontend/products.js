async function loadProductDetails() {
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get('id');

  if (!productId) {
    alert('No product ID provided!');
    window.location.href = 'index.html#products';
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/products/${productId}`);
    if (!response.ok) {
      throw new Error('Product not found');
    }

    const product = await response.json();

    document.getElementById('product-image').src = `data:${product.imageType};base64,${product.imageBase64}`;
    document.getElementById('product-name').textContent = product.name;
    document.getElementById('product-description').textContent = product.description;

    const featuresList = document.getElementById('product-features');
    featuresList.innerHTML = '';
    product.points.forEach(point => {
      const li = document.createElement('li');
      li.textContent = point;
      featuresList.appendChild(li);
    });
  } catch (error) {
    console.error('Error loading product details:', error);
    alert('Failed to load product details. Please try again later.');
    window.location.href = 'index.html#products';
  }
}

function requestQuote() {
  window.location.href = 'index.html#contact';
}

function goBack() {
  window.location.href = 'index.html#products';
}

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

// Load product details on page load
window.addEventListener('DOMContentLoaded', loadProductDetails);
