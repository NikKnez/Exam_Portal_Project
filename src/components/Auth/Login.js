import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UserService from '../../services/UserService';
import { loginUser, registerUser } from '../../services/UserService';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const history = useNavigate();

  async function handleLogin() {
    try {
      // Use your authentication service or API endpoint for login
      // Example using a UserService (assuming it has a login method)
      const response = await UserService.login({ username, password });

      // Assuming the response includes a token upon successful login
      const token = response.data.token;

      // Save the token to your preferred storage (e.g., localStorage)
      localStorage.setItem('token', token);

      // Redirect to the desired page (e.g., dashboard)
      history.push('/dashboard');
    } catch (error) {
      setError('Invalid username or password. Please try again.');
    }
  }

  return (
    <div className="login-container">
      <h2>Login</h2>
      {error && <p className="error-message">{error}</p>}
      <label>Username:</label>
      <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      <br />
      <label>Password:</label>
      <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <br />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}

export default Login;