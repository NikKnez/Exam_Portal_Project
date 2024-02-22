import QuizService from './QuizService';
const baseURL = 'http://localhost:8080'; 

const addQuiz = async (quiz) => {
  try {
    const response = await fetch(`${baseURL}/quiz/`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(quiz),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error('Error adding quiz');
    }
  } catch (error) {
    console.error('Error during quiz addition:', error);
    throw error;
  }
};

// Add functions for other Quiz service operations as needed

export default QuizService;