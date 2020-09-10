import React, { Fragment, useContext, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import Loading from './Loading';
import Context from './context/picture/Context';

const PersonForm = () => {
  const context = useContext(Context);
  const { loading, person, getPerson, savePerson, deletePerson } = context;
  let params = useParams();
  let isNew = false;

  useEffect(() => {
    if (!isNew) getPerson(params.personId);
    // eslint-disable-next-line
  }, []);

  if (params.personId === 'newPerson') isNew = true;

  const { register, handleSubmit, errors } = useForm();

  const onSubmit = (data) => {
    savePerson(data);
  };

  const deleteSubmit = (personId) => deletePerson(personId);

  if (!isNew && !person.personId) return <Loading />;

  if (!loading) {
    return (
      <Fragment>
        {isNew ? (
          <Link to={`/person`}>
            <p>Back</p>
          </Link>
        ) : (
          <Link to={`/person/${params.personId}`}>
            <p>Back</p>
          </Link>
        )}

        <form
          name='personForm'
          onSubmit={handleSubmit(onSubmit)}
          key={person.personId}
        >
          {!isNew && (
            <input
              type='hidden'
              name='personId'
              value={person.personId}
              ref={register}
            />
          )}
          <label htmlFor='firstName'>First name</label>
          <input
            type='text'
            name='firstName'
            aria-invalid={errors.name ? 'true' : 'false'}
            defaultValue={isNew ? '' : person.firstName}
            ref={register({ required: true })}
          />
          <label htmlFor='lastName'>First name</label>
          <input
            type='text'
            name='lastName'
            aria-invalid={errors.name ? 'true' : 'false'}
            defaultValue={isNew ? '' : person.lastName}
            ref={register({ required: true })}
          />
          <label htmlFor='email'>Email</label>
          <input
            type='text'
            name='email'
            aria-invalid={errors.name ? 'true' : 'false'}
            defaultValue={isNew ? '' : person.email}
            ref={register({ required: true })}
          />
          <div>
            {errors.firstName && (
              <span role='alert'>First name is required</span>
            )}
            {errors.lastName && <span role='alert'>Last name is required</span>}
            {errors.email && <span role='alert'>Email is required</span>}
            <button type='submit'>Save</button>
          </div>
        </form>
        {!isNew ? (
          <button onClick={() => deleteSubmit(params.personId)}>Delete</button>
        ) : null}
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default PersonForm;
