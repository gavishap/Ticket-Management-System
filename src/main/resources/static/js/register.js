document.getElementById('register-button').addEventListener('click', function() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    })
    .then(response => {
        if(response.ok) {
            return response.json();
        } else {
            throw new Error('Registration failed.');
        }
    })
    .then(data => {
        document.getElementById('message').innerText = 'Registration successful!';
        document.getElementById('message').style.color = 'green';
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('message').innerText = 'Registration failed.';
        document.getElementById('message').style.color = 'red';
    });
});
