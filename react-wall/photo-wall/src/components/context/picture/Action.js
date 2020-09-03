import React, { useReducer } from 'react';
import Reducer from './Reducer';
import Axios from 'axios';
import {
  GET_PICTURES,
  GET_PICTURE,
  GET_PERSONS,
  GET_PERSON,
  GET_PERSONBYPICTURE,
  SET_LOADING,
} from './Types';
import PictureContext from './Context';

const Action = (props) => {
  const initialState = {
    loading: false,
    pictures: [],
    picture: {},
    persons: [],
    person: [],
    personByPicture: {},
  };

  const [state, dispatch] = useReducer(Reducer, initialState);

  const getPictures = async () => {
    setLoading();
    await Axios.get('http://localhost:8080/api/pictures')
      .then((res) => {
        dispatch({
          type: GET_PICTURES,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getPicture = async (pictureId) => {
    setLoading();
    await Axios.get('http://localhost:8080/api/pictures/' + pictureId)
      .then((res) => {
        dispatch({
          type: GET_PICTURE,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getPersons = async () => {
    setLoading();
    await Axios.get('http://localhost:8080/api/persons')
      .then((res) => {
        dispatch({
          type: GET_PERSONS,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getPerson = async (personId) => {
    setLoading();
    await Axios.get('http://localhost:8080/api/persons/' + personId)
      .then((res) => {
        dispatch({
          type: GET_PERSON,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getPersonByPicture = async (pictureId) => {
    setLoading();
    await Axios.get('http://localhost:8080/api/persons/pic/' + pictureId)
      .then((res) => {
        dispatch({
          type: GET_PERSONBYPICTURE,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const setLoading = () => dispatch({ type: SET_LOADING });

  return (
    <PictureContext.Provider
      value={{
        loading: state.loading,
        pictures: state.pictures,
        picture: state.picture,
        persons: state.persons,
        person: state.person,
        personByPicture: state.personByPicture,
        getPictures,
        getPicture,
        getPersons,
        getPerson,
        getPersonByPicture,
      }}
    >
      {props.children}
    </PictureContext.Provider>
  );
};

export default Action;
