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
          <p>Add person</p>
        </Link>
        {persons
          .sort((a, b) => (a.firstName < b.firstName ? -1 : 1))
          .map((person) => (
            <Link key={person.personId} to={`/person/${person.personId}`}>
              <div key={person.personId}>
                <p>{person.firstName}</p>
                <p>{person.lastName}</p>
              </div>
            </Link>
          ))}
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default Person;
