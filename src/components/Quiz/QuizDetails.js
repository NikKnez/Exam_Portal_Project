import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import QuizService from '../../services/QuizService';

const QuizDetails = () => {
  const { qid } = useParams();
  const [quiz, setQuiz] = useState({});

  useEffect(() => {
    // Use your QuizService or API endpoint to fetch the details of a specific quiz
    const fetchQuizDetails = async () => {
      try {
        const response = await QuizService.getQuizDetails(qid);
        setQuiz(response.data);
      } catch (error) {
        console.error('Failed to fetch quiz details:', error);
      }
    };

    fetchQuizDetails();
  }, [qid]);

  return (
    <div>
      <h2>Quiz Details</h2>
      <p>Title: {quiz.title}</p>
      <p>Description: {quiz.description}</p>
      <p>Maximum Marks: {quiz.maxMarks}</p>
      <p>Number of Questions: {quiz.noOfQuestions}</p>
      {/* Additional details as needed */}
    </div>
  );
};

export default QuizDetails;
