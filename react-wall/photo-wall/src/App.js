import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import Home from './components/Home';
import Person from './components/Person';
import Profile from './components/Profile';
import Picture from './components/Picture';
import NotFound from './components/NotFound';
import './App.css';
import Action from './components/context/picture/Action';

const App = () => {
  return (
    <Action>
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
    </Action>
  );
};

export default App;
