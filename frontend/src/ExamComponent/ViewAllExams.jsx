import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const ViewAllExams = () => {
  const [exams, setExams] = useState([]);
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");
  const admin = JSON.parse(sessionStorage.getItem("active-admin"));

  let navigate = useNavigate();

  useEffect(() => {
    const getAllExams = async () => {
      const allExams = await retrieveAllExams();
      if (allExams) {
        setExams(allExams.exams);
      }
    };

    getAllExams();
  }, []);

  const retrieveAllExams = async (gradeId) => {
    const response = await axios.get(
      url + "/exam/fetch/all"
    );
    console.log(response.data);
    return response.data;
  };

  const viewQuestions = (exam) => {
    navigate("/exam/questions", { state: exam });
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
          <h2>All Exams</h2>
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
                  <th scope="col">Course</th>
                  <th scope="col">Exam Scheduled</th>
                  <th scope="col">Added Time</th>
                  <th scope="col">Added By</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                {exams.map((exam) => {
                  return (
                    <tr>
                      <td>
                        <b>{exam.name}</b>
                      </td>
                      <td>
                        <b>{exam.course.name}</b>
                      </td>
                      <td>
                        <b>
                          {formatDateFromEpoch(exam.startTime) +
                            "-" +
                            formatDateFromEpoch(exam.endTime)}
                        </b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(exam.addedDateTime)}</b>
                      </td>
                      <td>
                        <b>{exam.teacher.firstName}</b>
                      </td>
                      <td>
                        <button
                          onClick={() => viewQuestions(exam)}
                          className="btn btn-sm bg-color custom-bg-text ms-2"
                        >
                          View Questions
                        </button>
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

export default ViewAllExams;
