import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import Home from './components/Home';
import Person from './components/Person';
import Profile from './components/Profile';
import PictureFrom from './components/PictureForm';
import Picture from './components/Picture';
import NotFound from './components/NotFound';
import './App.css';
import Action from './components/context/picture/Action';
import PersonForm from './components/PersonForm';

const App = () => {
  return (
    <Router>
      <Action>
        <div className='app'>
          <Navbar />
          <div className='container'>
            <Switch>
              <Route exact path='/' component={Home} />
              <Route exact path='/person' component={Person} />
              <Route
                exact
                path='/person/profile/:personId?'
                component={PersonForm}
              />
              <Route exact path='/person/:personId?' component={Profile} />
              <Route
                exact
                path='/person/:personId?/:pictureId?'
                component={PictureFrom}
              />
              <Route exact path='/picture/:id?' component={Picture} />
              <Route component={NotFound} />
            </Switch>
          </div>
        </div>
      </Action>
    </Router>
  );
};

export default App;
