import './StudentClass.css'
import React from 'react'
import axios from 'axios'
import ReactDOM from 'react-dom'
import StudentHome from '../StudentHome/StudentHome'

class StudentClass extends React.Component {

    // When the back button is pressed, this function is envoked and it goes back to the student home
    handleBack(studentName, studentId) {
        // Option 1 (from login page)
        // ReactDOM.render(<div id='loginSuccess'></div>, document.getElementById('loginMessage'));        // This is to refresh the message so StudentHome is remounted when student changes
        // ReactDOM.render(<StudentHome studentName={studentName} studentId={studentId} />, document.getElementById('loginMessage'));
        
        // Option 2 (from login page)
        ReactDOM.render(<StudentHome studentName={studentName} studentId={studentId} />, document.getElementById('root'));
    }

    render() {
        return (
            <>
                <button onClick={() => this.handleBack(this.props.studentName, this.props.studentId)}>Go back</button>
                <p>This is {this.props.className}</p>
                <p>Description: {this.props.classDescription}</p>
                <p>Teacher: {this.props.classTeacher}</p>
            </>
        )
    }
}

export default StudentClass;