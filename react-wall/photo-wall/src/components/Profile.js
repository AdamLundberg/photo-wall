import { useParams } from 'react-router-dom';
import React, { Fragment, useEffect, useState } from 'react';
import axios from 'axios';

const Profile = () => {
  const [person, setPerson] = useState('');
  let params = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const getPerson = await axios(
        `http://localhost:8080/api/persons/` + params.id
      );

      setPerson(getPerson.data);
    };

    fetchData();
  }, [params]);

  if (person) {
    return (
      <Fragment>
        <p>{person.firstName}</p>
        <p>{person.lastName}</p>
        <p>{person.email}</p>

        {person.pictures.length > 0 ? (
          person.pictures.map((pic) => (
            <Fragment key={pic.pictureId}>
              <img key={pic.pictureId} src={pic.url} alt={pic.name}></img>
              <p>{pic.pictureCategory.pictureCategoryName}</p>
            </Fragment>
          ))
        ) : (
          <h1>No pictures posted!</h1>
        )}
      </Fragment>
    );
  } else {
    return null;
  }
};

export default Profile;
