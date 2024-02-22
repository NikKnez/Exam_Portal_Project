import UserService from './UserService';
const baseURL = 'http://localhost:8080'; 

 async function registerUser (user) {
  try {
    const response = await fetch(`${baseURL}/user/`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(user),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Error registering user');
    }
  } catch (error) {
    console.error('Error during user registration:', error);
    throw error;
  }
};

 async function loginUser (credentials) {
  try {
    const response = await fetch(`${baseURL}/generate-token`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(credentials),
    });

    if (response.ok) {
      const data = await response.json();
      return data.token;
    } else {
      throw new Error('Error logging in');
    }
  } catch (error) {
    console.error('Error during user login:', error);
    throw error;
  }
};

export default UserService;

