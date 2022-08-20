import './Login.css'
import React from 'react'
import ReactDOM from 'react-dom';
import axios from 'axios'

class Login extends React.Component {

    handleLogin(type) {
        if (type === "student") {
            axios.post('/login-student', {
                "name": document.getElementById('Username').value,
                "password": document.getElementById('Password').value
            })
            .then(function (response) {
                console.log(response);
                if (response.data === "There are no students with the name " + document.getElementById('Username').value ||
                    response.data === "Incorrect password") {
                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                } else {
                    // This is where it will go to the students home page
                    // For the students home page it will have to render each class like was done with the posts in the school project
                    // This will allow it to be dynamic in how many classes there can be.
                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                    // ReactDOM.render(<p>{response.data}</p>, document.getElementById('root'));
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
                console.log(response);
                if (response.data === "There are no teachers with the name " + document.getElementById('Username').value ||
                    response.data === "Incorrect password") {
                    ReactDOM.render(<p>{response.data}</p>, document.getElementById('loginMessage'));
                } else {
                    // This is where it will go to the teachers home page
                    // For the teachers home page it will have to render each class like was done with the posts in the school project
                    // This will allow it to be dynamic in how many classes there can be.
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