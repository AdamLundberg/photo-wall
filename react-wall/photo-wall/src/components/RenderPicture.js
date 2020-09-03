import React, { Fragment, useEffect, useState } from 'react';

const RenderPicture = (props) => {
  const [picture, setpicture] = useState(props);

  useEffect(() => {
    setpicture(props);
  }, [props]);

  return (
    <Fragment>
      <img key={picture.pictureId} src={picture.url} alt={picture.name} />
    </Fragment>
  );
};

export default RenderPicture;
