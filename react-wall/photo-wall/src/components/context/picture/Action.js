import React, { useReducer } from 'react';
import Reducer from './Reducer';
import Axios from 'axios';
import {
  GET_PICTURES,
  GET_PICTURE,
  GET_PERSONS,
  GET_PERSON,
  GET_PERSONBYPICTURE,
  GET_CATEGORIES,
  GET_CATEGORY,
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
    categories: [],
    category: {},
  };

  const [state, dispatch] = useReducer(Reducer, initialState);

  const savePicture = (picture, personId) => {
    setLoading();
    if (picture.pictureId != null) {
      Axios.put(
        'http://localhost:8080/api/pictures/' + picture.pictureId,
        picture,
        {
          headers: {
            Accept: '*/*',
          },
        }
      )
        .then((res) => {
          saveCategory(res);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      console.log('action savePicture');
      console.log(picture);

      Axios.post(`http://localhost:8080/api/pictures`, picture, {
        headers: {
          Accept: '*/*',
        },
      })
        .then((res) => {
          console.log('RESULT');
          console.log(res);
          //saveCategory(res);
          //addPictureToPerson(personId, res.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

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

  const addPictureToPerson = (personId, picture) => {
    setLoading();
    Axios.put(
      'http://localhost:8080/api/persons/pic/' + personId,
      picture.data,
      {
        headers: {
          Accept: '*/*',
        },
      }
    )
      .then((res) => {})
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

  const saveCategory = (picture) => {
    console.log(picture.data);
    setLoading();
    Axios.put(
      'http://localhost:8080/api/categories/' + picture.data.pictureCategory,
      picture,
      {
        headers: {
          Accept: '*/*',
        },
      }
    )
      .then((res) => {
        getCategory(res.data.pictureCategoryId);
        getPicture(picture.pictureId);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getCategories = async () => {
    setLoading();
    await Axios.get('http://localhost:8080/api/categories')
      .then((res) => {
        dispatch({
          type: GET_CATEGORIES,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getCategory = async (categoryId) => {
    setLoading();
    await Axios.get('http://localhost:8080/api/categories/' + categoryId)
      .then((res) => {
        dispatch({
          type: GET_CATEGORY,
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
        categories: state.categories,
        category: state.category,
        savePicture,
        getPictures,
        getPicture,
        getPersons,
        getPerson,
        getPersonByPicture,
        saveCategory,
        getCategories,
        getCategory,
      }}
    >
      {props.children}
    </PictureContext.Provider>
  );
};

export default Action;
