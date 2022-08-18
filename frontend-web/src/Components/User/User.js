import './User.css'
import React from 'react'
import ReactDOM from 'react-dom';
import Login from '../Login/Login'

class User extends React.Component {

    teacher() {
        ReactDOM.render(<Login userType="teacher" />, document.getElementById('loginInfo'))
    }

    student() {
        ReactDOM.render(<Login userType="student" />, document.getElementById('loginInfo'))
    }

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