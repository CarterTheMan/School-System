import './Login.css'
import React from 'react'

class Login extends React.Component {

    // Login: http://127.0.0.1:8080/login-student       http://10.0.2.2:8080/login-student
    // To login you have to make a JSON object that gets passed to the above link
    // JSONObject object = new JSONObject();
    // try {
    //     object.put("name", u);
    //     object.put("password", p);
    // } catch (Exception e) {
    //     e.printStackTrace();
    // }

    // To fix this issue, a new method for logging in may have to be made that takes the username and password as parameters
    // ex: 127.0.0.1:8080/login-student/{username}/{password}

    // Link with example: https://www.codegrepper.com/code-examples/javascript/sending+json+to+url+react
    handleLogin2() {
        // Simple POST request with a JSON body using fetch
        // console.log(document.getElementById('Username').value);
        // var cors_api_url = 'https://cors-anywhere.herokuapp.com/';
        const requestOptions = {
            method: 'POST',
            mode: 'cors',    // options: 'cors', 'no-cors', 'same-origin'
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "name": document.getElementById('Username').value, "password": document.getElementById('Password').value})
        };
        fetch('http://127.0.0.1:8080/login-student', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));      // This has to be changed to put the data somewhere
        console.log("2");
    }

    handleLogin() {

    }

    render() {
        return (
            <>
                <p>Enter login information for {this.props.userType}</p>
                <label for="Username">Username:</label>
                <input type="text" id="Username" name="Username"></input>
                <label for="Password">Password:</label>
                <input type="text" id="Password" name="Password"></input>
                <button onClick={this.handleLogin2}>Login</button>
            </>
        )
    }
}

export default Login;