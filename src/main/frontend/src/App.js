import React from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Login from "./Components/Login/Login";
import Success from "./Components/Success/Success";
import ErrorTable from "./Components/ErrorTable/ErrorTable";
import FileUpload from "./Components/FileUpload/FileUpload";


function App() {
  return (
      <BrowserRouter>
          <Switch>
              <Route exact path="/">
                  <Redirect to="/login"/>
              </Route>
              <Route path="/login" component={Login}/>
              <Route path="/success" component={Success}/>
              <Route path="/errors" component={ErrorTable}/>
              <Route path="/upload" component={FileUpload}/>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
