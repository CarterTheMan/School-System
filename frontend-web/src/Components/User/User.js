import './User.css'
import React from 'react'
import ReactDOM from 'react-dom';
import Login from '../Login/Login'

class User extends React.Component {

    // Changes the login to a teacher login
    teacher() {
        ReactDOM.render(<Login userType="teacher" />, document.getElementById('loginInfo'))
    }

    // Changes the login to a student login
    student() {
        ReactDOM.render(<Login userType="student" />, document.getElementById('loginInfo'))
    }

    // Renders two buttons that allow the user to select if they are a student or teacher logging in
    render() {
        return (
            <div>
                <p>What user are you?</p>
                <button onClick={this.teacher}>Teacher</button>
                <button onClick={this.student}>Student</button>
            </div>
        )
    }
}

export default User;