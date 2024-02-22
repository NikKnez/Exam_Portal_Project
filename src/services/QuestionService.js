import QuestionService from './QuestionService';
const baseURL = 'http://localhost:8080';

export const addQuestion = async (question) => {
  try {
    const response = await fetch(`${baseURL}/question/`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(question),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Error adding question');
    }
  } catch (error) {
    console.error('Error during question addition:', error);
    throw error;
  }
};

// Add functions for other Question service operations as needed

export default QuestionService;
