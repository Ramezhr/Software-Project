var tbody = document.getElementById("t-body");

async function fetchFeedback() {
  const apiUrl = "http://localhost:8060/feedback/get";
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

    const data = await response.json(); 
    console.log(data); 
    return data;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    throw error; 
  }
}
var feedbackList = [];
fetchFeedback()
  .then((data) => {
    ViewFeedback(data);
  })
  .catch((error) => {
    console.error("Error fetching feedback:", error);
    // Handle error
  });

function ViewFeedback(feedbackList) {
  var feedbackHtml = "";
  feedbackList.forEach((feedback) => {

    feedbackHtml += `
      <tr>
        <th scope="row">${feedback.id}</th>
        <td>${feedback.raterange}</td>
        <td>${feedback.text}</td>
      </tr>
    `;
  });
  tbody.innerHTML = feedbackHtml;
}

  

