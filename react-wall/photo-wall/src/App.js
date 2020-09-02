import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
//import logo from './logo.svg';
import './App.css';
import Home from './components/Home';
import Navbar from './components/navbar/Navbar';
import Person from './components/Person';
import Profile from './components/Profile';
import NotFound from './components/NotFound';
import Picture from './components/Picture';

function App() {
  return (
    <Router>
      <div className='app'>
        <Navbar />
        <div className='container'>
          <Switch>
            <Route exact path='/' component={Home} />
            <Route exact path='/person' component={Person} />
            <Route exact path='/person/:id?' component={Profile} />
            <Route exact path='/picture/:id?' component={Picture} />
            <Route component={NotFound} />
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
