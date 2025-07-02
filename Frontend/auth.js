document.addEventListener("DOMContentLoaded", function () {
  const tabs = document.querySelectorAll(".auth-tab");
  const forms = document.querySelectorAll(".auth-form");

  tabs.forEach((tab, index) => {
    tab.addEventListener("click", () => {
      tabs.forEach((t) => t.classList.remove("active"));
      forms.forEach((f) => f.classList.remove("active"));

      tab.classList.add("active");
      forms[index].classList.add("active");
    });
  });
});

// login
document
  .getElementById("login-form")
  .addEventListener("submit", async function (e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    const user = {
      userEmail: formData.get("email"),
      password: formData.get("password"),
    };

    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      });

      if (!response.ok) throw new Error("Registration failed");

      const result = await response.text();
      console.log("Login Data:", result);
      localStorage.setItem("token", result);
      form.reset();
      window.location.href = `index.html`
    } catch (err) {
      console.error(err);
      alert("Login failed");
    }
  });

// register
document
  .getElementById("register-form")
  .addEventListener("submit", async function (e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    const user = {
      userName: formData.get("userName"),
      userEmail: formData.get("userEmail"),
      password: formData.get("userPassword"),
    };

    try {
      const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      });

      if (!response.ok) throw new Error("Registration failed");

      const result = await response.json();

      alert("Registered successfully!");
      form.reset();
    } catch (err) {
      console.error(err);
      alert("Registration failed");
    }
  });
