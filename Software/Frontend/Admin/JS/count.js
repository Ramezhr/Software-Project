const q = document.getElementById("yoser");

async function fetchUserCount() {
  const jwtToken = sessionStorage.getItem("token");
  const apiUrl = `http://localhost:8050/count`;
  try {
    const response = await fetch(apiUrl, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json(); 
    console.log(data); 
    return data;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error;
  }
}

fetchUserCount()
  .then((count) => {
    ViewData(count);
  })
  .catch((error) => {
    console.error("Error fetching user count:", error);
  });

function ViewData(count) {
  q.innerText = count;
}
const ee = document.getElementById("product");

async function fetchcountproduct() {
  const jwtToken = sessionStorage.getItem("token");
  const apiUrl = `http://localhost:8050/products/count?token=${jwtToken}`;

  try {
    const response = await fetch(apiUrl, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json(); 
    console.log(data); 
    return data; 
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error; 
  }
}

fetchcountproduct()
  .then((countproduct) => {
    Viewsata(countproduct);
  })
  .catch((error) => {
    console.error("Error fetching user count:", error);
    // Handle error
  });

function Viewsata(countproduct) {
  ee.innerText = countproduct;
}
const www = document.getElementById("order");
async function fetchCount() {
  const apiUrl = "http://localhost:8050/counta";
  const jwtToken = sessionStorage.getItem("token");

  try {
    const response = await fetch(apiUrl, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json(); // Parse JSON response
    console.log(data); // Handle response data
    return data; // Return the data if needed
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error; // Rethrow the error for further handling if needed
  }
}

fetchCount()
  .then((counta) => {
    Viewtta(counta);
  })
  .catch((error) => {
    console.error("Error fetching user count:", error);
    // Handle error
  });

function Viewtta(counta) {
  www.innerText = counta;
}

const oooo = document.getElementById("categories");

async function fetchUserCounta() {
  const jwtToken = sessionStorage.getItem("token");
  const apiUrl = `http://localhost:8050/categorie/count?token=${jwtToken}`;

  try {
    const response = await fetch(apiUrl, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json(); // Parse JSON response
    console.log(data); // Handle response data
    return data; // Return the data if needed
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error; // Rethrow the error for further handling if needed
  }
}

fetchUserCounta()
  .then((count) => {
    Viewa(count);
  })
  .catch((error) => {
    console.error("Error fetching user count:", error);
    // Handle error
  });

function Viewa(count) {
  oooo.innerText = count;
}
