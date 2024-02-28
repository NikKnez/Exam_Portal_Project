import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import CountdownTimer from "./CountdownTimer";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const StudentExamAttempt = () => {
  const location = useLocation();
  const exam = location.state;

  const [calculatedTime, setCalculatedTime] = useState(null);

  const handleCalculateTime = () => {
    const currentTime = new Date().getTime();
   // const now = new Date().getTime();
    //const newTime = new Date(currentTime.getTime() + 20 * 60000); // Adding 10 minutes in milliseconds
    const newTime = currentTime + (20 * 60000) ; 

    setCalculatedTime(newTime);
    exam.endTime = newTime ; 
  };


  let navigate = useNavigate();

  var questions = exam.questions;
  const student = JSON.parse(sessionStorage.getItem("active-student"));
  const [timeUp, setTimeUp] = useState(false);

  const student_jwtToken = sessionStorage.getItem("student-jwtToken");

  const [answers, setAnswers] = useState({});

  const handleCheckboxChange = (questionId, optionIndex) => {
    setAnswers((prevAnswers) => ({
      ...prevAnswers,
      [questionId]: optionIndex,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an array of question objects with student answers
    const studentResponses = questions.map(
      ({ id, question, options, marks, status }, index) => ({
        id,
        question,
        options,
        marks,
        status,
        answer: answers[id] !== undefined ? answers[id] : 4,
      })
    );

    // Log the array of question objects with student answers
    console.log("Student Responses:", studentResponses);

    fetch(url + "/student/answer/add", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        //    Authorization: "Bearer " + admin_jwtToken,
      },
      body: JSON.stringify({
        examId: exam.id,
        studentId: student.id,
        questions: studentResponses,
      }),
    })
      .then((result) => {
        result.json().then((res) => {
          if (res.success) {
            toast.success(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });

            setTimeout(() => {
              navigate("/home"); // sending added exam object
            }, 2000); // Redirect after 3 seconds
          } else if (!res.success) {
            toast.error(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 2000); // Redirect after 3 seconds
          } else {
            toast.error("It Seems Server is down!!!", {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 2000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          window.location.reload(true);
        }, 1000); // Redirect after 3 seconds
      });
  };

  useEffect(() => {
    console.log("Updated Exam in Child Component:", exam);
    console.log('End Time starting ', exam.endTime); 
    console.log('Duration of the Exam ', exam.duration); 
    handleCalculateTime() ; 

    console.log('End time calculated one : '+ calculatedTime); 
  }, [exam]);


  return (
    <div className="container-fluid mt-2">
      <div className="form-card border-color">
        <div className="container-fluid">
          <div
            className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
            style={{
              borderRadius: "1em",
              height: "38px",
            }}
          >
            <h5 className="card-title d-flex justify-content-between">
              <span className="me-5">
                {exam.name + " - " + exam.grade.name + " - " + exam.course.name}
              </span>
              
              {/* <CountdownTimer endTime={exam.endTime} /> */}

              <CountdownTimer endTime={exam.duration} />

            </h5>
          </div>
          <div className="card-body text-color mt-3">
            <div className="col-md-12 mb-5">
              <div className="container mt-4 d-flex justify-content-center">
                <form
                  onSubmit={(e) => handleSubmit(e)}
                  className="container mt-4"
                >
                  <div className="row">
                    {questions.map(
                      ({ id, question, options, marks }, index) => (
                        <div key={id} className="col-md-6 mb-3">
                          <p className="fw-bold text-color-second">
                            {index + 1}. {question}{" "}
                            <span className="text-color">
                              {"[" + marks + " Marks]"}
                            </span>
                          </p>
                          {options
                            .replace(/[\[\]]/g, "")
                            .split(",")
                            .map((option, optionIndex) => (
                              <div key={optionIndex} className="form-check">
                                <input
                                  className="form-check-input"
                                  type="checkbox"
                                  checked={answers[id] === optionIndex}
                                  onChange={() =>
                                    handleCheckboxChange(id, optionIndex)
                                  }
                                />
                                <label className="form-check-label">
                                  {option.trim()}
                                </label>
                              </div>
                            ))}
                        </div>
                      )
                    )}
                  </div>
                  <div className="container mt-4 d-flex justify-content-center">
                    <button
                      type="submit"
                      className="btn bg-color custom-bg-text"
                    >
                      Submit Exam
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentExamAttempt;
