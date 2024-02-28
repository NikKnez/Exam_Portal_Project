import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const ViewAllCourses = () => {
  const [allCourses, setAllCourses] = useState([]);
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  const { gradeId } = useParams();

  let navigate = useNavigate();

  useEffect(() => {
    const getAllCourse = async () => {
      const allCourse = await retrieveAllCourses(gradeId);
      if (allCourse) {
        setAllCourses(allCourse.courses);
      }
    };

    getAllCourse();
  }, []);

  const retrieveAllCourses = async (gradeId) => {
    if (gradeId === "all") {
      const response = await axios.get(
        url + "/course/fetch/all"
      );
      console.log(response.data);
      return response.data;
    } else {
      const response = await axios.get(
        url + "/course/fetch/all/grade-wise?gradeId=" +
          gradeId
      );
      console.log(response.data);
      return response.data;
    }
  };

  const deleteCourse = (courseId, e) => {
    fetch(url + "/course/delete?courseId=" + courseId, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + admin_jwtToken,
      },
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
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
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
            }, 1000); // Redirect after 3 seconds
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

  const updateGrade = (course) => {
    navigate("/admin/course/update", { state: course });
  };

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
          <h2>All Courses</h2>
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
                  <th scope="col">Category Id</th>
                  <th scope="col">Category Name</th>
                  <th scope="col">Description</th>
                  <th scope="col">Grade</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                {allCourses.map((course) => {
                  return (
                    <tr>
                      <td>
                        <b>{course.id}</b>
                      </td>
                      <td>
                        <b>{course.name}</b>
                      </td>
                      <td>
                        <b>{course.description}</b>
                      </td>
                      <td>
                        <b>{course.grade.name}</b>
                      </td>
                      <td>
                        <button
                          onClick={() => updateGrade(course)}
                          className="btn btn-sm bg-color custom-bg-text ms-2"
                        >
                          Update
                        </button>

                        <button
                          onClick={() => deleteCourse(course.id)}
                          className="btn btn-sm bg-color custom-bg-text ms-2"
                        >
                          Delete
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

export default ViewAllCourses;
