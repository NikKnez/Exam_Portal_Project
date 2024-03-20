import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import QuestionCard from "./QuestionCard";
import QuestionSpellCard from "./QuestionSpellCard";

const ViewAllQuestions = (props) => {
  const [exam, setExam] = useState(props.exam);

  console.warn(exam);

  const teacher_jwtToken = sessionStorage.getItem("teacher-jwtToken");

  useEffect(() => {
    console.log("Updated Exam in Child Component:", exam);
  }, [props.exam]);

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
            <h5 class="card-title">Questions</h5>
          </div>
          <div class="card-body text-color mt-3">
            <div className="col-md-12 mb-5">
              {exam.questions.map((question, index) => {

                return (

                  <div>

                    { ( exam.examType === 'Multiple' ) ? (
                      <div><QuestionCard
                        question={question}
                        key={question.id}
                        serialNumber={index + 1}
                      /></div>
                    ) : (
                      <div><QuestionSpellCard
                        question={question}
                        key={question.id}
                        serialNumber={index + 1}
                      /></div>
                    )}


                  </div>

                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewAllQuestions;
