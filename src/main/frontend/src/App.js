import React from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Login from "./Components/Login/Login";
import Success from "./Components/Success/Success";
import ErrorTable from "./Components/ErrorTable/ErrorTable";
import FileUpload from "./Components/FileUpload/FileUpload";
import Navbar from "./Components/Navbar/Navbar";


function App() {
  return (
      <BrowserRouter>
          <Switch>
              <Route exact path="/">
                  <Redirect to="/login" component={Login}/>
              </Route>
              <Route path="/login" component={Login}/>
              <div>
                  <Navbar/>
                  <Route path="/success" component={Success}/>
                  <Route path="/errors" component={ErrorTable}/>
                  <Route path="/upload" component={FileUpload}/>
              </div>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
