var nameCategorie = document.getElementById("name");
var description = document.getElementById("desc");

var updatebutton = document.getElementById("update");
var validatename = document.querySelector(".name-validation");
var validatedesc = document.querySelector(".des-validation");
var successmsg = document.getElementById("success");
var categorie = JSON.parse(localStorage.getItem("categorie"));

nameCategorie.value = categorie.name;
description.value = categorie.description;

updatebutton.addEventListener("click", function (e) {
  e.preventDefault();
 
  updateCategory(categorie.id, nameCategorie.value, description.value)
    .then((data) => {
     
    })
    .catch((error) => {});
});

async function updateCategory(id, name, description) {
  const jwtToken = sessionStorage.getItem("token");
  const apiUrl = `http://localhost:8050/categorie/manage/${id}/update?token=${jwtToken}`;

  try {
    const response = await fetch(apiUrl, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, description }),
    });

    if (response.status === 400) {
      validatename.textContent = await response.text();
      validatename.style.display = "block";
      return;
    } else {
      const data = await response.text();
      console.log(data);
      successmsg.innerHTML = "Categorie Updated successfully";
      setTimeout(() => {
        window.location = "Categories.html";
      }, 1500);
      return data;
    }
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error;
  }
}
