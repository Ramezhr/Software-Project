var nameproduct = document.getElementById("name");
var price = document.getElementById("price");
var amount = document.getElementById("amount");
var description = document.getElementById("Description");
var selectcategorie = document.getElementById("selectcategorie");
var image = document.getElementById("image");
var add = document.getElementById("add");
var msg = document.getElementById("msg");
price.value = 0;
amount.value = 0;
add.addEventListener("click", function (e) {
  e.preventDefault();

  if (image.files.length == 0) {
    msg.innerHTML = "Please Upload the Product Image";
    return;
  }
  if (selectcategorie.value == 0) {
    msg.innerHTML = "Please enter the Product Categorie";
    return;
  }
  if (nameproduct.value == "" || description.value == "") {
    msg.innerHTML = "Please enter the All Fields";
    return;
  }
  createProduct(image, nameproduct, price, description, selectcategorie, amount)
    .then((data) => {})
    .catch((error) => {});
});

async function createProduct(
  image,
  nameproduct,
  price,
  description,
  selectcategorie,
  amount
) {
  try {
    // Create a new FormData object
    const productFormData = new FormData();
    productFormData.append("file", image.files[0]);
    productFormData.append("name", nameproduct.value);
    productFormData.append("price", price.value);
    productFormData.append("description", description.value);
    productFormData.append("categorie", selectcategorie.value);
    productFormData.append("amount", amount.value);
    const jwtToken = sessionStorage.getItem("token");

    // Append the encoded parameters to the URL

    // Make a POST request with the FormData object in the body
    const response = await fetch(
      `http://localhost:8050/products/manage/create_product?token=${jwtToken}`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
        body: productFormData,
      }
    );

    if (response.status === 400) {
      msg.innerHTML = await response.text();
      msg.style.display = "block";
      return;
    } else {
      const data = await response.text();
      console.log("Product created:", data);
      setTimeout(() => {
        window.location = "../../Admin/products.html";
      }, 1500);
      return data;
    }
  } catch (error) {
    console.error("Error creating product:", error);
    throw error;
  }
}
