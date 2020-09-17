import React, { Fragment, useContext, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import Loading from './Loading';
import Context from './context/picture/Context';
import DeleteModal from './DeleteModal';

const PersonForm = () => {
  const context = useContext(Context);
  const { loading, person, getPerson, savePerson } = context;
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

  if (!isNew && !person.personId) return <Loading />;

  if (!loading) {
    return (
      <Fragment>
        <nav aria-label='breadcrumb'>
          <ol className='breadcrumb'>
            <li className='breadcrumb-item'>
              <Link to={`/person`}>Person </Link>
            </li>
            {isNew ? (
              <Fragment>
                <li className='breadcrumb-item active'>
                  <p>New Person</p>
                </li>
              </Fragment>
            ) : (
              <Fragment>
                <li className='breadcrumb-item'>
                  <Link to={`/person/${params.personId}`}>
                    <p>
                      {person.firstName} {person.lastName}
                    </p>
                  </Link>
                </li>
                <li className='breadcrumb-item active'>
                  <p>Edit Person</p>
                </li>
              </Fragment>
            )}
          </ol>
        </nav>
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
          <div className='row'>
            <div className='col'>
              <label htmlFor='firstName'>First name</label>
              <input
                className='form-control'
                type='text'
                name='firstName'
                aria-invalid={errors.name ? 'true' : 'false'}
                defaultValue={isNew ? '' : person.firstName}
                ref={register({ required: true })}
              />
            </div>
            <div className='col'>
              <label htmlFor='lastName'>First name</label>
              <input
                className='form-control'
                type='text'
                name='lastName'
                aria-invalid={errors.name ? 'true' : 'false'}
                defaultValue={isNew ? '' : person.lastName}
                ref={register({ required: true })}
              />
            </div>
            <div className='col'>
              <label htmlFor='email'>Email</label>
              <input
                className='form-control'
                type='text'
                name='email'
                aria-invalid={errors.name ? 'true' : 'false'}
                defaultValue={isNew ? '' : person.email}
                ref={register({ required: true })}
              />
            </div>
          </div>

          <div className='mt-3'>
            <button className='btn btn-success' type='submit'>
              Save
            </button>
            {!isNew ? (
              <button
                className='btn btn-danger float-right'
                type='button'
                data-toggle='modal'
                data-target='#deleteModal'
              >
                Delete
              </button>
            ) : null}
          </div>
          <div className='mt-4'>
            <div className='mt-4'>
              {errors.firstName && (
                <span class='alert alert-danger' role='alert'>
                  First name is required
                </span>
              )}
            </div>
            <div className='mt-4'>
              {errors.lastName && (
                <span class='alert alert-danger' role='alert'>
                  Last name is required
                </span>
              )}
            </div>
            <div className='mt-4'>
              {errors.email && (
                <span class='alert alert-danger' role='alert'>
                  Email is required
                </span>
              )}
            </div>
          </div>
        </form>
        <DeleteModal {...person} />
      </Fragment>
    );
  } else {
    return <Loading />;
  }
};

export default PersonForm;
