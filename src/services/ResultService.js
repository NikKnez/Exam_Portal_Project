import ResultService from './ResultService';
const baseURL = 'http://localhost:8080'; 

async function addResult (result) {
  try {
    const response = await fetch(`${baseURL}/result/`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(result),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Error adding result');
    }
  } catch (error) {
    console.error('Error during result addition:', error);
    throw error;
  }
};


// Add functions for other Result service operations as needed
export default ResultService;