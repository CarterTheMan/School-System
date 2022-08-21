import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import User from './Components/User/User'

// This is the start home page
ReactDOM.render(<><User />
                  <div id="loginInfo"></div>
                  <div id="loginMessage"></div></>, document.getElementById('root'))


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
