let globalVariable;

window.onload = async function () {

    let config = {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    }

    await axios.get('http://localhost:9090/api/questions', config)
        .then(function (response) {
            globalVariable = response.data;
            console.log('Data loaded successfully');
        })
        .catch(function (error) {
            console.log('Error:', error);
        });

    let table = document.getElementById("table-body")

    globalVariable.forEach(function(item) {
        console.log("ID: " + item.id);
        console.log("Name: " + item.name);

        let row = table.insertRow(-1);  // Add a new row at the end of the table

        let cell1 = row.insertCell(0);  // Add a new cell in the first column
        cell1.textContent = item.id;  // Set the cell's text to the id

        let cell2 = row.insertCell(1);  // Add a new cell in the second column
        cell2.textContent = item.name;

        let cell3 = row.insertCell(2);  // Add a new cell in the third column
        let button = document.createElement('button');  // Create a new button element
        button.textContent = "Open";  // Set the button's text
        button.onclick = function() { setId(item.id); };  // Set the button's onclick attribute to invoke yourFunction with item.id as an argument
        cell3.appendChild(button);   // Add the link to the third cell

    });
}

function setId (id) {
    localStorage.setItem("id", id)
    console.log(id)
    window.location.href = "detail.html";
}
