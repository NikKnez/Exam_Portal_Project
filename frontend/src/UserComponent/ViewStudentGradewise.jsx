import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { ToastContainer, toast } from "react-toastify";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const ViewStudentGradewise = () => {
  const [allStudent, setAllStudent] = useState([]);
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");
  const teacher = JSON.parse(sessionStorage.getItem("active-teacher"));

  useEffect(() => {
    const getAllUsers = async () => {
      const allUsers = await retrieveAllUser();
      if (allUsers) {
        setAllStudent(allUsers.users);
      }
    };

    getAllUsers();
  }, []);

  const retrieveAllUser = async () => {
    const response = await axios.get(
      url + "/user/fetch/student/grade-wise?gradeId=" +
        teacher.grade.id
      // ,
      // {
      //   headers: {
      //     Authorization: "Bearer " + admin_jwtToken, // Replace with your actual JWT token
      //   },
      // }
    );
    console.log(response.data);
    return response.data;
  };

  const deleteUser = (userId, e) => {
    fetch(url + "/user/delete/user-id?userId=" + userId, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        //    Authorization: "Bearer " + seller_jwtToken,
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
              window.location.href = "/home";
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
              window.location.href = "/home";
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
          window.location.href = "/home";
        }, 1000); // Redirect after 3 seconds
      });
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
          <h2>All Students</h2>
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
                  <th scope="col">First Name</th>
                  <th scope="col">Last Name</th>
                  <th scope="col">Email Id</th>
                  <th scope="col">Phone No</th>
                  <th scope="col">Address</th>
                  <th scope="col">Grade</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                {allStudent.map((student) => {
                  return (
                    <tr>
                      <td>
                        <b>{student.firstName}</b>
                      </td>
                      <td>
                        <b>{student.lastName}</b>
                      </td>
                      <td>
                        <b>{student.emailId}</b>
                      </td>
                      <td>
                        <b>{student.phoneNo}</b>
                      </td>
                      <td>
                        <b>
                          {student.address.street +
                            ", " +
                            student.address.city +
                            ", " +
                            student.address.pincode}
                        </b>
                      </td>
                      <td>
                        <b>{student.grade.name}</b>
                      </td>
                      <td>
                        <button
                          onClick={() => deleteUser(student.id)}
                          className="btn btn-sm bg-color custom-bg-text ms-2"
                        >
                          Delete
                        </button>
                        <ToastContainer />
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

export default ViewStudentGradewise;
