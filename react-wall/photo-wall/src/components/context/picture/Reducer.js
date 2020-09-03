import {
  SET_LOADING,
  GET_PICTURES,
  GET_PICTURE,
  GET_PERSONS,
  GET_PERSON,
  GET_PERSONBYPICTURE,
} from './Types';

export default (state, action) => {
  switch (action.type) {
    case SET_LOADING:
      return {
        ...state,
        loading: true,
      };
    case GET_PICTURES:
      return {
        ...state,
        pictures: action.payload,
        loading: false,
      };
    case GET_PICTURE:
      return {
        ...state,
        picture: action.payload,
        loading: false,
      };
    case GET_PERSONS:
      return {
        ...state,
        persons: action.payload,
        loading: false,
      };
    case GET_PERSON:
      return {
        ...state,
        person: action.payload,
        loading: false,
      };
    case GET_PERSONBYPICTURE:
      return {
        ...state,
        personByPicture: action.payload,
        loading: false,
      };

    /* case GET_PICTURESBYPERSON:
      return {
        ...state,
        picturesByPerson: action.payload,
      }; */
    default:
      return state;
  }
};
