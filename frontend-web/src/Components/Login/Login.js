import './Login.css'
import React from 'react'

class Login extends React.Component {

    // Login: http://127.0.0.1:8080//login-student
    // To login you have to make a JSON object that gets passed to the above link
    // JSONObject object = new JSONObject();
    // try {
    //     object.put("name", u);
    //     object.put("password", p);
    // } catch (Exception e) {
    //     e.printStackTrace();
    // }

    // Link with example: https://www.codegrepper.com/code-examples/javascript/sending+json+to+url+react
    // handleLogin2(username, password) {
    //     // Simple POST request with a JSON body using fetch
    //     const requestOptions = {
    //         method: 'POST',
    //         headers: { 'Content-Type': 'application/json' },
    //         body: JSON.stringify({ name: username, pass: password})
    //     };
    //     fetch('http://127.0.0.1:8080//login-student', requestOptions)
    //         .then(response => response.json())
    //         .then(data => this.setState({ postId: data.id }));      // This has to be changed
    // }

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
                <button onClick={this.handleLogin}>Login</button>
            </>
        )
    }
}

export default Login;