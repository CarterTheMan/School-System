import './StudentHome.css'
import React from 'react'
import axios from 'axios'
import ReactDOM from 'react-dom'
import StudentClass from '../StudentClass/StudentClass'
import User from '../../User/User'

class StudentHome extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            allClasses: null,
        }
    }

    // Does the get request after the component has been rendered so the reqeust has time
    componentDidMount() {
        const courseUrl = '/student/' + this.props.studentId + '/courses';

        axios({
            method: 'get',
            url: courseUrl
        })
        .then(response => {
            const data = response.data;
            const allArr = [];
            for (let i = 0; i < data.length; i++) {
                const classInfo = {title : data[i].teacherCourse.course.title, classId : data[i].id, description : data[i].teacherCourse.course.description, name : data[i].teacherCourse.teacher.name};
                allArr[i] = classInfo;
            }
            console.log('ALL before: ', allArr);
            this.setState({allClasses : allArr});
            console.log('ALL after: ', this.state.all);
        })
            .catch(function (error) {
            console.log(error);
        });
    }

    // Returns the user to the login page so they no longer have access to their information
    handleLogout() {
        ReactDOM.render(<><User />
                  <div id="loginInfo"></div>
                  <div id="loginMessage"></div></>, document.getElementById('root'));
    }

    // Loads the student class and shows the details of the class
    goToClass(className, classDescription, classTeacher, classNumber) {
        // Option 1 (from login page)
        // ReactDOM.render(<StudentClass studentName={this.props.studentName} 
        //                               studentId={this.props.studentId} 
        //                               className={className} 
        //                               classDescription={classDescription}
        //                               classTeacher={classTeacher} 
        //                               classNumber={classNumber}  />, document.getElementById('loginMessage'));

        // Option 2 (from login page)
        ReactDOM.render(<StudentClass studentName={this.props.studentName} 
            studentId={this.props.studentId} 
            className={className} 
            classDescription={classDescription}
            classTeacher={classTeacher} 
            classNumber={classNumber}  />, document.getElementById('root'));
    }

    // Says loading while the axios call loads and once that has run it shows the basic information for each class and allows the student to 
    // click on each class to view more informations specific to that class
    render() {

        // Uses a ternary statement to decide to show its loading or the results of classes for the student
        return (
            <>
                {this.state.allClasses === null ? 
                <div>Loading</div>
                :
                <>
                    <h2>Welcome {this.props.studentName}!</h2>
                    <button onClick={this.handleLogout}>Logout</button>
                    <h3>Here are your classes</h3>

                    <div>
                        {this.state.allClasses.map(allClasses => (
                            <>
                                <p>Class: {allClasses.title}</p>
                                <p>Description: {allClasses.description}</p>
                                <p>Teacher: {allClasses.name}</p>
                                <button onClick={() => this.goToClass(allClasses.title, allClasses.description, allClasses.name, allClasses.classId)}>Click here to learn more about {allClasses.title}</button>
                            </>
                        ))}
                    </div>
                </>

                }
            </> 
        )
    }
}

export default StudentHome