import React, { useState } from 'react';
import ResultService from '../../services/ResultService';
import { addResult } from '../../services/ResultService';

function AddResult() {
  const [marksScored, setMarksScored] = useState('');
  const [quizId, setQuizId] = useState('');
  const [userId, setUserId] = useState('');

  async function handleAddResult() {
    try {
      // Use your ResultService or API endpoint for adding a result
      await ResultService.addResult({ marksScored, quizId, userId });

      // Optionally, redirect to the result list or another page
    } catch (error) {
      console.error('Failed to add result:', error);
    }
  };

  return (
    <div>
      <h2>Add Result</h2>
      <label>Marks Scored:</label>
      <input type="text" value={marksScored} onChange={(e) => setMarksScored(e.target.value)} />
      <br />
      <label>Quiz ID:</label>
      <input type="text" value={quizId} onChange={(e) => setQuizId(e.target.value)} />
      <br />
      <label>User ID:</label>
      <input type="text" value={userId} onChange={(e) => setUserId(e.target.value)} />
      <br />
      <button onClick={handleAddResult}>Add Result</button>
    </div>
  );
};

export default AddResult;
