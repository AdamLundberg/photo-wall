import { useParams, Link } from 'react-router-dom';
import React, { Fragment, useEffect, useContext } from 'react';
import RenderPicture from './RenderPicture';
import Context from './context/picture/Context';
import Loading from './Loading';

const Profile = () => {
  const context = useContext(Context);
  const { loading, person, getPerson } = context;
  let params = useParams();

  useEffect(() => {
    getPerson(params.personId);
    // eslint-disable-next-line
  }, []);

  if (person.personId && !loading) {
    return (
      <Fragment key={person.personId}>
        <Link to={`/person`}>
          <p>Back</p>
        </Link>
        <p>{person.firstName}</p>
        <p>{person.lastName}</p>
        <p>{person.email}</p>
        <Link to={`/person/profile/${params.personId}`}>
          <p>Edit person</p>
        </Link>
        <Link to={`/person/${params.personId}/newPicture`}>
          <p>Add picture</p>
        </Link>

        {person.pictures.length > 0 ? (
          person.pictures
            .sort(
              (a, b) =>
                new Date(b.localDateCreated) - new Date(a.localDateCreated)
            )
            .map((pic) => (
              <Fragment key={pic.pictureId}>
                <RenderPicture {...pic} />
                {/* <p>{pic.pictureCategory.pictureCategoryName}</p> */}
                <Link to={`/person/${params.personId}/${pic.pictureId}`}>
                  <p>Edit</p>
                </Link>
              </Fragment>
            ))
        ) : (
          <h1>{person.firstName} have not posted anything yet!</h1>
        )}
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default Profile;
