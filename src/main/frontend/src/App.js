import React from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Login from "./Components/Login/Login";
import Success from "./Components/Success/Success";
import Home from "./Components/Home/Home";
import ErrorTable from "./Components/ErrorTable/ErrorTable";


function App() {
  return (
    <div>
        <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/login" component={Login}/>
                    <Route path="/success" component={Success}/>
                    <Route path="/errors" component={ErrorTable}/>
                </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
