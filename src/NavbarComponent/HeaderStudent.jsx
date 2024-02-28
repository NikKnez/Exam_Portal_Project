import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const HeaderStudent = () => {
  let navigate = useNavigate();

  const student = JSON.parse(sessionStorage.getItem("active-student"));

  const userLogout = () => {
    toast.success("logged out!!!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("active-student");
    sessionStorage.removeItem("student-jwtToken");
    window.location.reload(true);
    setTimeout(() => {
      navigate("/home");
    }, 2000); // Redirect after 3 seconds
  };

  const viewStudentProfile = () => {
    navigate("/user/profile/detail", { state: student });
  };

  return (
    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
      <li class="nav-item">
        <Link
          to="/exam/student/grade-wise/ongoing"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Ongoing Exam</b>
        </Link>
      </li>

      {/* <li class="nav-item">
        <Link
          to="/exam/student/grade-wise/previous"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Previous Exams</b>
        </Link>
      </li> */}
      <li class="nav-item">
        <Link
          to="/exam/student/grade-wise/upcoming"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Upcoming Exams</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/exam/student/result/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Exam Results</b>
        </Link>
      </li>
      <li class="nav-item">
        <div class="nav-link active" aria-current="page">
          <b className="text-color" onClick={viewStudentProfile}>
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
        >
          <b className="text-color">Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default HeaderStudent;
