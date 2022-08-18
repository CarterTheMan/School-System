import './Login.css'
import React from 'react'

class Login extends React.Component {

    render() {
        return (
            <>
                <p>Enter login information for {this.props.userType}</p>
                <label for="Username">Username:</label>
                <input type="text" id="Username" name="Username"></input>
                <label for="Password">Password:</label>
                <input type="text" id="Password" name="Password"></input>
                <input type="submit" value="Submit"></input>
            </>
        )
    }
}

export default Login;