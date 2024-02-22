import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import QuizService from '../../services/QuizService';

const QuizQuestions = () => {
  const { qid } = useParams();
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    // Use your QuizService or API endpoint to fetch the questions of a specific quiz
    const fetchQuizQuestions = async () => {
      try {
        const response = await QuizService.getQuizQuestions(qid);
        setQuestions(response.data);
      } catch (error) {
        console.error('Failed to fetch quiz questions:', error);
      }
    };

    fetchQuizQuestions();
  }, [qid]);

  return (
    <div>
      <h2>Quiz Questions</h2>
      <ul>
        {questions.map((question) => (
          <li key={question.quesId}>{question.content}</li>
        ))}
      </ul>
    </div>
  );
};

export default QuizQuestions;
