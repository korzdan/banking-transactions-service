import React from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Login from "./components/Login/Login";
import ErrorTable from "./components/ErrorTable/ErrorTable";
import FileUpload from "./components/FileUpload/FileUpload";
import Navbar from "./components/Navbar/Navbar";
import Transactions from "./components/Transactions/Transactions";
import TransactionTable from "./components/Transactions/TransactionTable";
import TransactionCard from "./components/Transactions/TransactionCard";
import Statistics from "./components/Transactions/Statistics";


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
                  <Route path="/transactions" component={Transactions}/>
                  <Route exact path="/transactions/all">
                      <TransactionTable url="http://localhost:8080/transactions"/>
                  </Route>
                  <Route exact path="/transactions/top">
                      <TransactionTable url="http://localhost:8080/transactions/top"/>
                  </Route>
                  <Route exact path="/transactions/max">
                      <TransactionCard url="http://localhost:8080/transactions/max"/>
                  </Route>
                  <Route exact path="/transactions/min">
                      <TransactionCard url="http://localhost:8080/transactions/min"/>
                  </Route>
                  <Route exact path="/transactions/statistics">
                      <Statistics/>
                  </Route>
              </div>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
