import React, { Fragment, useContext } from 'react';
import RenderPicture from './RenderPicture';
import Context from './context/picture/Context';

const DeleteModal = (props) => {
  const context = useContext(Context);
  const { deletePicture, deletePerson } = context;

  const deleteSubmit = () => {
    if (props.pictureId && props.personId) {
      deletePicture(props.pictureId, props.personId);
    } else if (props.personId) {
      deletePerson(props.personId);
    }
  };

  return (
    <Fragment>
      <div
        className='modal fade'
        id='deleteModal'
        tabIndex='-1'
        role='dialog'
        aria-labelledby='deleteModalLabel'
        aria-hidden='true'
      >
        <div className='modal-dialog' role='document'>
          <div className='modal-content'>
            <div className='modal-body'>
              {props.pictureId ? (
                <Fragment>
                  <RenderPicture {...props} />
                  <p className='text-center mt-3'>
                    Are you sure you want to delete: {props.name}?
                  </p>
                </Fragment>
              ) : (
                <Fragment>
                  <p>
                    Are you sure you want to delete: {props.firstName}{' '}
                    {props.lastName} and all pictures?
                  </p>
                </Fragment>
              )}
            </div>
            <div className='modal-footer'>
              <button
                type='button'
                className='btn btn-success'
                data-dismiss='modal'
              >
                Back
              </button>
              <button
                type='button'
                className='btn btn-danger float-right'
                data-dismiss='modal'
                onClick={() => deleteSubmit()}
              >
                Delete!
              </button>
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default DeleteModal;
