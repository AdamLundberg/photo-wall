import React, { Fragment, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';
import Context from './context/picture/Context';
import Loading from './Loading';

const Person = () => {
  const context = useContext(Context);
  const { loading, persons, getPersons } = context;

  useEffect(() => {
    getPersons();
    // eslint-disable-next-line
  }, []);

  if (!loading) {
    return (
      <Fragment>
        <Link to={`/person/profile/newPerson`}>
          <button className='btn btn-outline-success d-inline mt-3'>
            Add person
          </button>
        </Link>
        <div className='list-group mt-3'>
          {persons
            .sort((a, b) => (a.firstName < b.firstName ? -1 : 1))
            .map((person) => (
              <button
                type='button'
                className='list-group-item list-group-item-action'
                key={person.personId}
              >
                <Link key={person.personId} to={`/person/${person.personId}`}>
                  <p>
                    {person.firstName} {person.lastName}
                  </p>
                </Link>
              </button>
            ))}
        </div>
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default Person;
