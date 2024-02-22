import CategoryService from './CategoryService';
const baseURL = 'http://localhost:8080'; 

export const addCategory = async (category) => {
  try {
    const response = await fetch(`${baseURL}/category/`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(category),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Error adding category');
    }
  } catch (error) {
    console.error('Error during category addition:', error);
    throw error;
  }
};

// Add functions for other Category service operations as needed
export default CategoryService;