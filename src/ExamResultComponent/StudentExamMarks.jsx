import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const StudentExamMarks = ({ examResult }) => {
  var result = examResult;

  let navigate = useNavigate();

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  const viewProfile = () => {
    navigate("/user/profile/detail", { state: result.student });
  };

  return (
    <div>
      <div className="d-flex align-items-center justify-content-center ms-5 mt-1 me-5 mb-3">
        <div className="card rounded-card h-100 shadow-lg">
          <div className="card-body">
            <h4 className="card-title text-color-second text-center">
              Student Exam Result
            </h4>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Exam Name:</b> {result.exam.name}
                </p>
              </div>
              <div className="col-md-6">
                {/* <p className="mb-2">
                  <b>Exam Timing:</b>{" "}
                  {formatDateFromEpoch(result.exam.startTime) +
                    "-" +
                    formatDateFromEpoch(result.exam.endTime)}
                </p> */}
              </div>
            </div>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Grade:</b> {result.exam.grade.name}
                </p>
              </div>
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Course:</b> {result.exam.course.name}
                </p>
              </div>
            </div>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2" onClick={(e) => viewProfile()}>
                  <b>Student First Name:</b>{" "}
                  <span className="text-color-second">
                    <u>{result.student.firstName}</u>
                  </span>
                </p>
              </div>
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Student Last Name:</b> {result.student.lastName}
                </p>
              </div>
            </div>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Total Questions:</b> {result.totalQuestions}
                </p>
              </div>
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Total Marks:</b> {result.totalMarks}
                </p>
              </div>
            </div>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Total Correct Answers:</b> {result.totalCorrectAnswers}
                </p>
              </div>
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Total Wrong Answers:</b> {result.totalWrongAnswers}
                </p>
              </div>
            </div>
            <div className="row mt-4">
              <div className="col-md-6">
                <p className="mb-2">
                  <b>Score:</b> {result.score}
                </p>
              </div>
              <div className="col-md-6">
                {(() => {
                  if (result.resultStatus === "Pass") {
                    return (
                      <div>
                        <b>
                          Percentage:{" "}
                          <span className="text-success">
                            {result.percentage + " %"}
                          </span>
                        </b>
                      </div>
                    );
                  } else {
                    return (
                      <div>
                        <b>
                          Percentage:{" "}
                          <span className="text-danger">
                            {result.percentage + " %"}
                          </span>
                        </b>
                      </div>
                    );
                  }
                })()}
              </div>
            </div>
            <div className="row mt-4">
              <div className="d-flex justify-content-center">
                <p className="mb-2">
                  {(() => {
                    if (result.resultStatus === "Pass") {
                      return (
                        <div>
                          <h3>
                            Result:{" "}
                            <span className="text-success">
                              {result.resultStatus}
                            </span>
                          </h3>
                        </div>
                      );
                    } else {
                      return (
                        <div>
                          <h3>
                            Result:{" "}
                            <span className="text-danger">
                              {result.resultStatus}
                            </span>
                          </h3>
                        </div>
                      );
                    }
                  })()}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentExamMarks;
