import React, { useState } from 'react';

const Login = ({ setuser }) => {
  const [username, setUsername] = useState('');

  const handleLogin = () => {
    // Assuming you want to set the user with the entered username
    setuser(username);
  };

  return (
    <div>
      <h2>Login</h2>
      <form>
        <label>
          Username:
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </label>
        <button type="button" onClick={handleLogin}>
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;
