import React, { Fragment, useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Person = () => {
  const [persons, setPersons] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      const getPersons = await axios(`http://localhost:8080/api/persons`);

      setPersons(getPersons.data);
    };

    fetchData();
  }, []);

  console.log(persons);

  if (persons) {
    return (
      <Fragment>
        {persons.map((person) => (
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
    return null;
  }
};

export default Person;
