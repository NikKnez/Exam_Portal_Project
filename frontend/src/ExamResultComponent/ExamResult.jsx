import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import StudentExamMarks from "./StudentExamMarks";
import ViewAllQuestions from "../ExamQuestionComponent/ViewAllQuestions";

const ExamResult = () => {
  const location = useLocation();

  var [examResult, setExamResult] = useState(location.state);

  return (
    <div className="container-fluid mb-5">
      <div class="row">
        <div class="col-sm-6 mt-2">
          <StudentExamMarks examResult={examResult} />
        </div>

        <div class="col-sm-6 mt-2">
          <ViewAllQuestions exam={examResult.exam} />
        </div>
      </div>
    </div>
  );
};

export default ExamResult;
