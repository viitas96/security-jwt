let serverResponse
let results

window.onload = async function () {

    let config = {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    }

    let id = localStorage.getItem("id")

    await axios.get(`http://localhost:9090/api/questions/${id}`, config)
        .then(function (response) {
            serverResponse = response.data;
            console.log('Data loaded successfully');
        })
        .catch(function (error) {
            console.log('Error:', error);
        });

    await axios.get(`http://localhost:9090/api/questions/get-info/${id}`, config)
        .then(function (response) {
            results = response.data;
            console.log('Data loaded successfully');
        })
        .catch(function (error) {
            console.log('Error:', error);
        });

    console.log(results)

    console.log(serverResponse)

    document.getElementById("question").innerText = serverResponse.name
    document.getElementById("first-value").innerText = `1. ${serverResponse.firstVariant} - voturi: ${results.firstValue}`
    document.getElementById("second-value").innerText = `2. ${serverResponse.secondVariant} - voturi: ${results.secondValue}`
    document.getElementById("third-value").innerText = `3. ${serverResponse.thirdVariant} - voturi: ${results.thirdValue}`
    document.getElementById("fourth-value").innerText = `4. ${serverResponse.fourthVariant} - voturi: ${results.fourthValue}`

    if (results.answered) {
        let warning = document.createElement("p")
        warning.textContent = "Dvs ati raspuns deja la aceasta intrebare!"
        warning.style.color = "red"
        let root = document.getElementById("root")
        root.appendChild(warning)
        document.getElementById("answer-dialog").style.display = "none"
    }

}

function sendAnswers() {
    let e = document.getElementById("exampleFormControlSelect1");
    let value = e.value;
    let text = e.options[e.selectedIndex].text;
    let questionId = localStorage.getItem("id");

    let config = {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    }

    axios.post(`http://localhost:9090/api/questions/response/${questionId}/${text}`, {}, config)
        .then((response) => {

        }).catch((error) => {
        console.log(error)
    })

    location.reload()
}

function back() {
    window.location.href = "main.html";
}
