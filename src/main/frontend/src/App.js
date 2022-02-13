import React from "react";
import {BrowserRouter, Switch, Route} from "react-router-dom";
import './App.css';
import axios from "axios";
import Login from "./Login";
import Success from "./Success";
import Home from "./Home";


function App() {
  return (
    <div className="App">
        <BrowserRouter>
                <Switch>
                    <Route path="/login" component={Login}/>
                    <Route path="/success" component={Success}/>
                    <Route exact path="/" component={Home}/>
                </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
