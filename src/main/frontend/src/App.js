import React from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Login from "./components/Login/Login";
import ErrorTable from "./components/ErrorTable/ErrorTable";
import FileUpload from "./components/FileUpload/FileUpload";
import Navbar from "./components/Navbar/Navbar";


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
                  <Route path="/errors" component={ErrorTable}/>
                  <Route path="/upload" component={FileUpload}/>
              </div>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
