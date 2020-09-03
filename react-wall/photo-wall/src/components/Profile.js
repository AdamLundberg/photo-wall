import { useParams } from 'react-router-dom';
import React, { Fragment, useEffect, useContext } from 'react';
import RenderPicture from './RenderPicture';
import Context from './context/picture/Context';
import Loading from './Loading';

const Profile = () => {
  const context = useContext(Context);
  const { loading, person, getPerson } = context;
  let params = useParams();

  useEffect(() => {
    getPerson(params.id);
    // eslint-disable-next-line
  }, []);

  if (person.personId && !loading) {
    return (
      <Fragment>
        <p>{person.firstName}</p>
        <p>{person.lastName}</p>
        <p>{person.email}</p>

        {person.pictures.length > 0 ? (
          person.pictures.map((pic) => (
            <Fragment key={pic.pictureId}>
              <RenderPicture {...pic} />
              <p>{pic.pictureCategory.pictureCategoryName}</p>
            </Fragment>
          ))
        ) : (
          <h1>No pictures posted!</h1>
        )}
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default Profile;
