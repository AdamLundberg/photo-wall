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
        <nav aria-label='breadcrumb'>
          <ol className='breadcrumb'>
            <li className='breadcrumb-item'>
              <Link to={`/person`}>Person </Link>
            </li>
            <li className='breadcrumb-item active'>
              {person.firstName} {person.lastName}
            </li>
          </ol>
        </nav>

        <div className='mt-3'>
          <p className='d-inline '>
            {person.firstName} {person.lastName}
          </p>
          <p>Email: {person.email}</p>
        </div>

        <div className='mt-3'>
          <Link to={`/person/profile/${params.personId}`}>
            <button className='btn btn-outline-primary d-inline'>
              Edit person
            </button>
          </Link>
          <Link to={`/person/${params.personId}/newPicture`}>
            <button className='btn btn-outline-success d-inline float-right'>
              Add picture
            </button>
          </Link>
        </div>
        <div className='card-columns mt-4'>
          {person.pictures.length > 0 ? (
            person.pictures
              .sort(
                (a, b) =>
                  new Date(b.localDateCreated) - new Date(a.localDateCreated)
              )
              .map((pic) => (
                <Fragment key={pic.pictureId}>
                  <div className='card'>
                    <RenderPicture {...pic} />
                    <p className='text-center'>{pic.name}</p>
                    <Link to={`/person/${params.personId}/${pic.pictureId}`}>
                      <button
                        type='button'
                        className='btn btn-outline-primary btn-block'
                      >
                        Edit
                      </button>
                    </Link>
                  </div>
                </Fragment>
              ))
          ) : (
            <h6>{person.firstName} has not posted anything yet!</h6>
          )}
        </div>
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default Profile;
