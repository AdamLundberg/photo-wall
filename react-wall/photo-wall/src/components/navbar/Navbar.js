/* Navbar component is used as navigation in the application, here we use Link to navigate to different components.
 */

import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav>
      <ul>
        <li>
          <Link to='/'>Home</Link>
        </li>
      </ul>
    </nav>
  );
};
export default Navbar;
