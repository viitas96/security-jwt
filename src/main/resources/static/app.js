let errorMessage = document.getElementById("error-message")

window.onload = function () {
    errorMessage.style.display = "none"
}

async function login() {
    let username = document.getElementById("email").value
    let password = document.getElementById("password").value
    localStorage.removeItem('token')

    let data = {
        email: username,
        password: password,
    };

    let error = true

    await axios
        .post('http://localhost:9090/api/auth/login', data)
        .then((response) => {
            error = false
            localStorage.setItem('token', response.data.token);
        })
        .catch((error) => {
            console.log(error)
        });

    console.log(localStorage.getItem('token'))

    if (!error) {
        window.location.href = "main.html";
    } else {
        errorMessage.style.display = "block"
    }
}