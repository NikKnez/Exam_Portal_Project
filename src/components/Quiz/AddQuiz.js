import React, { useState } from 'react';
import QuizService from '../../services/QuizService';
import { addQuiz } from '../../services/QuizService';

const AddQuiz = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [maxMarks, setMaxMarks] = useState('');
  const [noOfQuestions, setNoOfQuestions] = useState('');

  async function handleAddQuiz () {
    try {
      // Use your QuizService or API endpoint for adding a quiz
      await QuizService.addQuiz({ title, description, maxMarks, noOfQuestions });

      // Optionally, redirect to the quiz list or another page
    } catch (error) {
      console.error('Failed to add quiz:', error);
    }
  };

  return (
    <div>
      <h2>Add Quiz</h2>
      <label>Title:</label>
      <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      <br />
      <label>Description:</label>
      <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
      <br />
      <label>Maximum Marks:</label>
      <input type="text" value={maxMarks} onChange={(e) => setMaxMarks(e.target.value)} />
      <br />
      <label>Number of Questions:</label>
      <input type="text" value={noOfQuestions} onChange={(e) => setNoOfQuestions(e.target.value)} />
      <br />
      <button onClick={handleAddQuiz}>Add Quiz</button>
    </div>
  );
};

export default AddQuiz;
