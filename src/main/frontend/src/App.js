import React from "react";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Login from "./components/Login/Login";
import ErrorTable from "./components/ErrorTable/ErrorTable";
import FileUpload from "./components/FileUpload/FileUpload";
import Navbar from "./components/Navbar/Navbar";
import TransactionsOperations from "./components/TransactionsOperations/TransactionsOperations";
import TransactionTable from "./components/TransactionsTable/TransactionTable";
import TransactionCard from "./components/TransactionCard/TransactionCard";
import Statistics from "./components/Statistics/Statistics";
import TransactionSearch from "./components/TransactionSearch/TransactionSearch";
import FoundTransaction from "./components/FoundTransaction/FoundTransaction";


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
                  <Route path="/transactions" component={TransactionsOperations}/>
                  <Route exact path="/transactions">
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
                  <Route exact path="/transactions/search">
                      <TransactionSearch/>
                  </Route>
                  <Route exact path="/transactions/search/:id">
                      <FoundTransaction/>
                  </Route>
              </div>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
