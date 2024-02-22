import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UserService from '../../services/UserService';
import { loginUser, registerUser } from '../../services/UserService';

function Register () {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [error, setError] = useState('');
  const history = useNavigate();

  async function handleRegister () {
    try {
      // Use your registration service or API endpoint for registration
      // Example using a UserService (assuming it has a register method)
      await UserService.register({ username, password, firstName, lastName, email, phone });

      // Redirect to the login page after successful registration
      history.push('/login');
    } catch (error) {
      setError('Registration failed. Please check your details and try again.');
    }
  };

  return (
    <div>
      <h2>Register</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <label>Username:</label>
      <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      <br />
      <label>Password:</label>
      <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <br />
      <label>First Name:</label>
      <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
      <br />
      <label>Last Name:</label>
      <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
      <br />
      <label>Email:</label>
      <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <br />
      <label>Phone:</label>
      <input type="tel" value={phone} onChange={(e) => setPhone(e.target.value)} />
      <br />
      <button onClick={handleRegister}>Register</button>
    </div>
  );
};

export default Register;
