document.getElementById('signin-button').addEventListener('click', function() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/api/users/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    })
    .then(response => response.json())
    .then(data => {
        if (data.isAuthenticated) {
            document.getElementById('message').innerText = 'Sign in successful!';
            document.getElementById('message').style.color = 'green';
        } else {
            document.getElementById('message').innerText = 'Invalid username or password.';
            document.getElementById('message').style.color = 'red';
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
