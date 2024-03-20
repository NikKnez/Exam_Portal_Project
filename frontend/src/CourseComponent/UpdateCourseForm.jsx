import { useState } from "react";
import { useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const UpdateCourseForm = () => {
  const location = useLocation();
  const course = location.state;
  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  const [id, setId] = useState(course.id);
  const [name, setName] = useState(course.name);
  const [description, setDescription] = useState(course.description);
  const [gradeId, setGradeId] = useState(course.grade.id);
  const [allGrades, setAllGrades] = useState([]);

  let navigate = useNavigate();

  useEffect(() => {
    const getAllGrade = async () => {
      const allGrades = await retrieveAllGrade();
      if (allGrades) {
        setAllGrades(allGrades.grades);
      }
    };

    getAllGrade();
  }, []);

  const retrieveAllGrade = async () => {
    const response = await axios.get(
      url + "/grade/fetch/all"
    );
    console.log(response.data);
    return response.data;
  };

  const saveCourse = (e) => {
    let data = { id, name, description, gradeId };

    fetch(url + "/course/update", {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + admin_jwtToken,
      },
      body: JSON.stringify(data),
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
              navigate("/admin/grade/all/course/");
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
              window.location.href = "/home";
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
              window.location.href = "/home";
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
          window.location.href = "/home";
        }, 1000); // Redirect after 3 seconds
      });
    e.preventDefault();
  };

  return (
    <div>
      <div class="mt-2 d-flex aligns-items-center justify-content-center">
        <div class="form-card border-color" style={{ width: "25rem" }}>
          <div className="container-fluid">
            <div
              className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
              style={{
                borderRadius: "1em",
                height: "38px",
              }}
            >
              <h5 class="card-title">Update Course</h5>
            </div>
            <div class="card-body text-color mt-3">
              <form>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Course Name</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    placeholder="enter title.."
                    onChange={(e) => {
                      setName(e.target.value);
                    }}
                    value={name}
                  />
                </div>
                <div class="mb-3">
                  <label for="description" class="form-label">
                    <b>Course Description</b>
                  </label>
                  <textarea
                    class="form-control"
                    id="description"
                    rows="3"
                    placeholder="enter description.."
                    onChange={(e) => {
                      setDescription(e.target.value);
                    }}
                    value={description}
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    <b>Grade</b>
                  </label>

                  <select
                    name="gradeId"
                    onChange={(e) => {
                      setGradeId(e.target.value);
                    }}
                    className="form-control"
                  >
                    <option value="">Select Grade</option>

                    {allGrades.map((grade) => {
                      return <option value={grade.id}> {grade.name} </option>;
                    })}
                  </select>
                </div>

                <div className="d-flex aligns-items-center justify-content-center mb-2">
                  <button
                    type="submit"
                    onClick={saveCourse}
                    class="btn bg-color custom-bg-text"
                  >
                    Update Course
                  </button>
                </div>

                <ToastContainer />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateCourseForm;
