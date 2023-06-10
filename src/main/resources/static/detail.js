let errorMessage = document.getElementById("error-message")

window.onload = function () {
    errorMessage.style.display = "none"
}

async function login() {
    let nickName = document.getElementById("nickname").value
    let username = document.getElementById("email").value
    let password = document.getElementById("password").value
    localStorage.removeItem('token')

    let data = {
        email: username,
        password: password,
        nickName: nickName
    };

    let error = true
    let textError = ''

    await axios
        .post('http://localhost:8081/api/auth/register', data)
        .then((response) => {
            error = false
            localStorage.setItem('token', response.data.token);
        })
        .catch((error) => {
            textError = error.response.data.error
            console.log(error)
        });

    if (error === false) {
        window.location.href = "main.html";
    } else {
        errorMessage.style.display = "block"
        errorMessage.style.color = "red"
        errorMessage.textContent = textError
    }
}

function toRegistry() {
    window.location.href = "detail.html";
}