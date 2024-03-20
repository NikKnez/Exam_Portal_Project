import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const HeaderTeacher = () => {
  let navigate = useNavigate();

  const teacher = JSON.parse(sessionStorage.getItem("active-teacher"));

  const userLogout = () => {
    toast.success("Logged out!!!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("active-teacher");
    sessionStorage.removeItem("teacher-jwtToken");
    setTimeout(() => {
      window.location.href = "/home";
    }, 2000); // Redirect after 3 seconds
  };

  const viewProfile = () => {
    navigate("/user/profile/detail", { state: teacher });
  };

  return (
    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
      <li class="nav-item">
        <Link
          to="/user/student/register"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Register Student</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/teacher/grade/student/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View Students</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link to="/exam/add" class="nav-link active" aria-current="page">
          <b className="text-color">Add Exam</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/exam/grade-wise/upcoming"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Upcoming Exams</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/exam/grade-wise/previous"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Previous Exams</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/exam/grade/student/result/"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Student Results</b>
        </Link>
      </li>
      {teacher && teacher.grade && (<li className="nav-item">
        <Link
          to={`/admin/grade/${teacher.grade.id}/course/`}
          className="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View Courses</b>
        </Link>
      </li>
  )}
      <li class="nav-item">
        <div class="nav-link active" aria-current="page">
          <b className="text-color" onClick={viewProfile}>
            My Profile
          </b>
          <ToastContainer />
        </div>
      </li>
      <li class="nav-item">
        <Link
          to=""
          class="nav-link active"
          aria-current="page"
          onClick={userLogout}
          navigate 
        >
          <b className="text-color">Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default HeaderTeacher;
