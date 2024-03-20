import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const ViewStudentExamResults = () => {
  const [results, setResults] = useState([]);
  const student_jwtToken = sessionStorage.getItem("student-jwtToken");
  const student = JSON.parse(sessionStorage.getItem("active-student"));

  let navigate = useNavigate();

  useEffect(() => {
    const getAllResults = async () => {
      const allResults = await retrieveAllExamResults();
      if (allResults) {
        setResults(allResults.results);
      }
    };

    getAllResults();
  }, []);

  const retrieveAllExamResults = async () => {
    const response = await axios.get(
      url + "/exam/result/fetch/student-wise?studentId=" +
        student.id
    );
    console.log(response.data);
    return response.data;
  };

  const viewExamResult = (resullt) => {
    navigate("/exam/student/result", { state: resullt });
  };

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  // sending added exam object

  return (
    <div className="mt-3">
      <div
        className="card form-card ms-2 me-2 mb-5 shadow-lg"
        style={{
          height: "45rem",
        }}
      >
        <div
          className="card-header custom-bg-text text-center bg-color"
          style={{
            borderRadius: "1em",
            height: "50px",
          }}
        >
          <h2>Exam Results</h2>
        </div>
        <div
          className="card-body"
          style={{
            overflowY: "auto",
          }}
        >
          <div className="table-responsive">
            <table className="table table-hover text-color text-center">
              <thead className="table-bordered border-color bg-color custom-bg-text">
                <tr>
                  <th scope="col">Exam</th>
                  <th scope="col">Grade</th>
                  <th scope="col">Course</th>
                  <th scope="col">Timing</th>
                  <th scope="col">Result</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                {results.map((result) => {
                  return (
                    <tr>
                      <td>
                        <b>{result.exam.name}</b>
                      </td>
                      <td>
                        <b>{result.exam.grade.name}</b>
                      </td>
                      <td>
                        <b>{result.exam.course.name}</b>
                      </td>
                      <td>
                        <b>
                          {formatDateFromEpoch(result.exam.startTime)
                          //  +   "-" +   formatDateFromEpoch(result.exam.endTime)
                           }
                        </b>
                      </td>
                      <td>
                        {(() => {
                          if (result.resultStatus === "Pass") {
                            return (
                              <div>
                                <b className="text-success">
                                  {result.resultStatus}
                                </b>
                              </div>
                            );
                          } else {
                            return (
                              <div>
                                <b className="text-danger">
                                  {result.resultStatus}
                                </b>
                              </div>
                            );
                          }
                        })()}
                      </td>
                      <td>
                        <div>
                          <button
                            onClick={(e) => viewExamResult(result)}
                            className="btn btn-sm bg-color custom-bg-text ms-2"
                          >
                            View Result
                          </button>
                        </div>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewStudentExamResults;
