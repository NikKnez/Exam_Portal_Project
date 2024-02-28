import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const AddExamForm = () => {
  const teacher = JSON.parse(sessionStorage.getItem("active-teacher"));

  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [duration,setDuration] = useState(""); 
  const [examRequest, setExamRequest] = useState({
    name: "",
    teacherId: teacher.id,
    courseId: "",
    gradeId: teacher.grade.id,
    startTime: "",
    endTime: "",
    duration:"" ,
    examType: "", 
    description: "", 
    topic: "",
    path:"" 
  });

  const handleUserInput = (e) => {
    setExamRequest({ ...examRequest, [e.target.name]: e.target.value });
  };

  const [allCourses, setAllCourses] = useState([]);

  const admin_jwtToken = sessionStorage.getItem("admin-jwtToken");

  let navigate = useNavigate();

  useEffect(() => {
    const getAllCourse = async () => {
      const allCourse = await retrieveAllCourses(teacher.grade.id);
      if (allCourse) {
        setAllCourses(allCourse.courses);
      }
    };

    getAllCourse();
  }, []);

  const retrieveAllCourses = async (gradeId) => {
    const response = await axios.get(
      url + "/course/fetch/all/grade-wise?gradeId=" + gradeId
    );
    console.log(response.data);
    return response.data;
  };

  const saveExam = (e) => {
    examRequest.startTime = new Date(startTime).getTime();
    examRequest.endTime = new Date(endTime).getTime();
  // examRequest.duration = duration ; 

    fetch(url + "/exam/add", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        //    Authorization: "Bearer " + admin_jwtToken,
      },
      body: JSON.stringify(examRequest),
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
              navigate("/exam/questions", { state: res.exams[0] }); // sending added exam object
            //  navigate("/exam/questions", { state: res.exams[0] }); // sending added exam object
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
              <h5 class="card-title">Schedule Exam</h5>
            </div>
            <div class="card-body text-color mt-3">
              <form>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Exam Name</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    name="name"
                    placeholder="enter name.."
                    onChange={handleUserInput}
                    value={examRequest.name}
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    <b>Course</b>
                  </label>

                  <select
                    name="courseId"
                    onChange={handleUserInput}
                    className="form-control"
                  >
                    <option value="">Select Course</option>

                    {allCourses.map((course) => {
                      return <option value={course.id}> {course.name} </option>;
                    })}
                  </select>
                </div>

                <div className="mb-3 text-color">
                  <label htmlFor="title" className="form-label">
                    <b>Exam Start Time</b>
                  </label>
                  <input
                    type="datetime-local"
                    className="form-control"
                    value={startTime}
                    onChange={(e) => setStartTime(e.target.value)}
                  />
                </div>
                <div className="mb-3 text-color">
                  <label htmlFor="title" className="form-label">
                    <b>Exam End Time</b>
                  </label>
                  <input
                    type="datetime-local"
                    className="form-control"
                    value={endTime}
                    onChange={(e) => setEndTime(e.target.value)}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Duration in Mins</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    name="duration"
                    placeholder="enter duration.."
                    onChange={handleUserInput}
                    value={examRequest.duration}
                  />
                </div>

                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Description</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    name="description"
                    placeholder="enter description.."
                    onChange={handleUserInput}
                    value={examRequest.description}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>path</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    name="path"
                    placeholder="enter filepath.."
                    onChange={handleUserInput}
                    value={examRequest.path}
                  />
                </div>

                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Topic</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    name="topic"
                    placeholder="enter topic.."
                    onChange={handleUserInput}
                    value={examRequest.topic}
                  />
                </div>




                <div class="mb-3 text-color">
                  <label for="examType" class="form-label">
                    <b>Exam Type</b>
                  </label>
                  <select
                    onChange={handleUserInput}
                    className="form-control"
                    name="examType"
                  >
                    <option value="0">Select Exam Type</option>
                    <option value="Multiple"> Multiple </option>
                    <option value="Spell"> Spell </option>
                    <option value="Blanks"> Blanks </option>
                    <option value="Blanks"> Match  </option>
                  </select>
                </div>


                <div className="d-flex aligns-items-center justify-content-center mb-2">
                  <button
                    type="submit"
                    onClick={saveExam}
                    class="btn bg-color custom-bg-text"
                  >
                    Add Exam
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

export default AddExamForm;
