var nameCategorie = document.getElementById("name");
var description = document.getElementById("desc");

var add = document.getElementById("add");
var validatename = document.querySelector(".name-validation");
var validatedesc = document.querySelector(".des-validation");

add.addEventListener("click", function (e) {
  e.preventDefault();
  
  fetchData(nameCategorie.value, description.value)
    .then((data) => {
     
     
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
});

async function fetchData(name, description) {

  const jwtToken = sessionStorage.getItem("token");
  const apiUrl = `http://localhost:8050/categorie/manage/add?token=${jwtToken}`;
  

  try {
    const response = await fetch(apiUrl, {
      method: "POST",
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
    validatename.style.display = "none";
    setTimeout(() => {
      window.location = "Categories.html";
    }, 1500);
    return await response.text();
  }
  } catch (error) {
    console.error("Fetch operation failed:", error.message);
    throw error;
  }
}



