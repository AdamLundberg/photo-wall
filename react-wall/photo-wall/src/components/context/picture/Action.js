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
import { useHistory } from 'react-router-dom';

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

  const history = useHistory();
  const goToPage = (path) => {
    history.push(path);
  };

  const [state, dispatch] = useReducer(Reducer, initialState);

  const savePicture = (picture, personId) => {
    setLoading();

    if (picture.pictureId != null) {
      Axios.put(
        'http://localhost:8080/api/pictures/' + picture.pictureId,
        picture
      )
        .then((res) => {
          saveCategory(picture.pictureCategoryId, res);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      Axios.post(`http://localhost:8080/api/pictures`, picture)
        .then((res) => {
          addPictureToPerson(personId, res, picture.pictureCategoryId);

          getPerson(personId);
          getPictures();

          goToPage(`/person/${personId}`);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const deletePicture = (pictureId, personId) => {
    Axios.delete(`http://localhost:8080/api/pictures/` + pictureId).then(
      (res) => {
        goToPage(`/person/${personId}`);
      }
    );
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

  const savePerson = (person) => {
    setLoading();
    if (person.personId != null) {
      Axios.put('http://localhost:8080/api/persons/' + person.personId, person)
        .then((res) => {
          getPerson(res.data.personId);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      Axios.post('http://localhost:8080/api/persons', person)
        .then((res) => {
          goToPage(`/person`);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const deletePerson = (personId) => {
    Axios.delete(`http://localhost:8080/api/persons/` + personId)
      .then(() => {
        goToPage(`/person`);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const addPictureToPerson = (personId, picture, pictureCategoryId) => {
    setLoading();
    Axios.put('http://localhost:8080/api/persons/pic/' + personId, picture.data)
      .then((res) => {
        saveCategory(pictureCategoryId, picture);
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

  const saveCategory = (pictureCategoryId, picture) => {
    setLoading();
    Axios.put(
      'http://localhost:8080/api/categories/' + pictureCategoryId,
      picture.data
    )
      .then((res) => {
        getPicture(picture.data.pictureId);
        getCategory(res.data.pictureCategoryId);
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
        deletePicture,
        getPictures,
        getPicture,
        getPersons,
        getPerson,
        savePerson,
        deletePerson,
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
