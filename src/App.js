import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './components/Auth/Register';
import Login from './components/Auth/Login';
import AddQuiz from './components/Quiz/AddQuiz';
import QuizList from './components/Quiz/QuizList';
import AddResult from './components/Result/AddResult';
import ResultList from './components/Result/ResultList';
import WelcomePage from './components/Admin/WelcomePage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/register" component={Register} />
        <Route path="/login" component={Login} />
        <Route path="/quiz/add" component={AddQuiz} />
        <Route path="/quiz/list" component={QuizList} />
        <Route path="/result/add" component={AddResult} />
        <Route path="/result/list" component={ResultList} />
        <Route path="/admin/welcome" component={WelcomePage} />
      </Routes>
    </Router>
  );
};

export default App;
