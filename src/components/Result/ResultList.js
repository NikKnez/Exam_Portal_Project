import React, { useState, useEffect } from 'react';
import ResultService from '../../services/ResultService';

const ResultList = () => {
  const [results, setResults] = useState([]);

  useEffect(() => {
    // Use your ResultService or API endpoint to fetch the list of results
    const fetchResults = async () => {
      try {
        const response = await ResultService.getAllResults();
        setResults(response.data);
      } catch (error) {
        console.error('Failed to fetch results:', error);
      }
    };

    fetchResults();
  }, []);

  return (
    <div>
      <h2>Result List</h2>
      <ul>
        {results.map((result) => (
          <li key={result.resultId}>Marks Scored: {result.marksScored}</li>
        ))}
      </ul>
    </div>
  );
};

export default ResultList;
