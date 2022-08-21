import './Login.css'
import React from 'react'
import ReactDOM from 'react-dom';
import axios from 'axios'
import StudentHome  from '../Student/StudentHome/StudentHome';

class Login extends React.Component {

    // Handles the log in once that button is pressed. If it doesn't succeed, it tells the user why.
    // If it does succeed it goes to the users home page.
    handleLogin(type) {
        if (type === "student") {
            axios.post('/login-student', {
                "name": document.getElementById('Username').value,
                "password": document.getElementById('Password').value
            })
            .then(function (response) {
                if (response.data === "There are no students with the name " + document.getElementById('Username').value ||
                    response.data === "Incorrect password") {
                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                } else {
                    // Option 1: Have the login part constantly showing
                    // ReactDOM.render(<div id='loginSuccess'></div>, document.getElementById('loginMessage'));        // This is to refresh the message so StudentHome is remounted when student changes
                    // ReactDOM.render(<StudentHome studentName={document.getElementById('Username').value} studentId={response.data} />, document.getElementById('loginMessage'));
                    
                    // Option 2: Have the login part disappear after a login (would have to add a button in the student home to go back to this point)
                    ReactDOM.render(<StudentHome studentName={document.getElementById('Username').value} studentId={response.data} />, document.getElementById('root'));
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        } else if (type === "teacher") {
            axios.post('/login-teacher', {
                "name": document.getElementById('Username').value,
                "password": document.getElementById('Password').value
            })
            .then(function (response) {
                if (response.data === "There are no teachers with the name " + document.getElementById('Username').value ||
                    response.data === "Incorrect password") {
                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                } else {
                    // This is where it will go to the teachers home page. There will be two options for this
                    // Option 1: Have the login part constantly showing (will copy the format of the student homepage)
                    // Option 2: Have the login part disappear after a login (Would have to add a button in the teacher home to go back to this point)

                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                    // ReactDOM.render(<p>{response.data}</p>, document.getElementById('root'));
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    }

    render() {
        return (
            <>
                <p>Enter login information for {this.props.userType}</p>
                <label for="Username">Username:</label>
                <input type="text" id="Username" name="Username"></input>
                <label for="Password">Password:</label>
                <input type="text" id="Password" name="Password"></input>
                <button onClick={() => this.handleLogin(this.props.userType)}>Login</button>
            </>
        )
    }
}

export default Login;