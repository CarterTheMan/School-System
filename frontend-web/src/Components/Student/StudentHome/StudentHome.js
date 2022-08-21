import './StudentHome.css'
import React from 'react'
import axios from 'axios'
import ReactDOM from 'react-dom'

class StudentHome extends React.Component {
    // This is where it will go to the students home page
    // For the students home page it will have to render each class like was done with the posts in the school project
    // This will allow it to be dynamic in how many classes there can be.
    // Will have to pass the data as a prop so the home screen and use it to find the classes of that user

    async getCourses() {
        const courseUrl = '/student/' + this.props.id + '/courses';

        return await axios({
            method: 'get',
            url: courseUrl
        })
        .then(function (response) {
            console.log(response);
            console.log(response.data);
            return response.data;
        })
            .catch(function (error) {
            console.log(error);
        });
    }

    render() {
        // Do a get here and then map the results in the return statement
        // {this.state.data.map(({ link, text, score }) => (
        
        var data = 'none';

        // This does the get request and sets data equal to the data that is gotten back (an array of courses)
        (async () => {
            data = await this.getCourses();
            console.log("data: " + data);
        })();
        
        // Need to find way for render to wait for 3 seconds or until data is not 'none' since it gets changed due to the call
        return (
            <>
                <h3>Welcome {this.props.name}!</h3>
                <p>Here are your classes</p>
                <p>{data}</p>
            </> 
        )
    }
}

export default StudentHome