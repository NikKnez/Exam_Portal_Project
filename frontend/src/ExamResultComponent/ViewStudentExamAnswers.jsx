import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import QuestionCard from "./QuestionCard";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const ViewStudentExamAnswers = (props) => {
  const [result, setResult] = useState(props.examResult);

  const teacher_jwtToken = sessionStorage.getItem("teacher-jwtToken");

  return (
    <div className="container-fluid mt-2">
      <div class="form-card border-color">
        <div className="container-fluid">
          <div
            className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
            style={{
              borderRadius: "1em",
              height: "38px",
            }}
          >
            <h5 class="card-title">Student Answers</h5>
          </div>
          <div class="card-body text-color mt-3">
            <div className="col-md-12 mb-5">
              {result.exam.questions.map((question, index) => {
                return (
                  <QuestionCard
                    question={question}
                    key={question.id}
                    serialNumber={index + 1}
                  />
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewStudentExamAnswers;
