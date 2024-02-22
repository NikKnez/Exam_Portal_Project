import React, { useState, useEffect } from 'react';
import QuizService from '../../services/QuizService';

async function QuizList () {
  const [quizzes, setQuizzes] = useState([]);

  useEffect(() => {
    // Use your QuizService or API endpoint to fetch the list of quizzes
    async function fetchQuizzes () {
      try {
        const response = await QuizService.getQuizzes();
        setQuizzes(response.data);
      } catch (error) {
        console.error('Failed to fetch quizzes:', error);
      }
    };

    fetchQuizzes();
  }, []);

  return (
    <div>
      <h2>Quiz List</h2>
      <ul>
        {quizzes.map((quiz) => (
          <li key={quiz.qid}>{quiz.title}</li>
        ))}
      </ul>
    </div>
  );
};

export default QuizList;
