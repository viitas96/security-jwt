let globalVariable;
let today = new Date();
let dd = String(today.getDate()).padStart(2, '0');
let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
let yyyy = today.getFullYear();

today = yyyy + '-' + mm + '-' + dd;

document.getElementById("start").setAttribute("min", today);

let now = new Date();
let year = now.getFullYear();
let month = now.getMonth() + 1; // months start at 0 in JavaScript
let day = now.getDate();
let hours = now.getHours();
let minutes = now.getMinutes();

// Add leading zeros to month, day, hours, minutes if needed
month = month < 10 ? `0${month}` : month;
day = day < 10 ? `0${day}` : day;
hours = hours < 10 ? `0${hours}` : hours;
minutes = minutes < 10 ? `0${minutes}` : minutes;

let dateTimeLocalString = `${year}-${month}-${day}T${hours}:${minutes}`;

document.getElementById("datetime").min = dateTimeLocalString;

async function getData() {
    let shit = document.getElementById("start").value
    console.log(shit)

    let config = {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        },
        params: {
            date: shit
        }
    }

    await axios.get(`http://localhost:8081/api/bookings`, config)
        .then(function (response) {
            globalVariable = response.data;
            console.log('Data loaded successfully');
        })
        .catch(function (error) {
            console.log('Error:', error);
        });

    document.getElementById('content').innerHTML = '';

    for (let [key, value] of Object.entries(globalVariable)) {
        // Create card elements
        let card = document.createElement('div');
        card.className = 'card mt-5';
        card.style.width = '14rem';

        let cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        let cardTitle = document.createElement('h5');
        cardTitle.className = 'card-title';
        cardTitle.textContent = key;

        cardBody.appendChild(cardTitle);

        let listGroup = document.createElement('ul');
        listGroup.className = 'list-group list-group-flush';
        for (let i = 0; i < value.length; i++) {
            let listItem = document.createElement('li');
            listItem.className = 'list-group-item';
            listItem.textContent = value[i].username + ' ' + value[i].dateTime;
                        listGroup.appendChild(listItem);
        }
        card.appendChild(cardBody);
        card.appendChild(listGroup);

        // Add to container
        document.getElementById('content').appendChild(card);
    }
}

async function book() {
    document.getElementById("alert").style.display = "none"
    let e = document.getElementById("table-select");
    let value = e.value;
    let text = e.options[e.selectedIndex].text;
    let datetime = document.getElementById("datetime").value

    let payload = {
        localDateTime: datetime
    }

    let config = {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    }

    await axios.post(`http://localhost:8081/api/bookings/${text}`, payload, config)
        .then(function (response) {
            document.getElementById("alert").style.display = "block"
            document.getElementById("alert").style.color = "green"
            document.getElementById("alert").textContent = "Adaugat cu succes"
            console.log('Data loaded successfully');
        })
        .catch(function (error) {
            document.getElementById("alert").style.display = "block"
            console.log('Error:', error);
        });

}
