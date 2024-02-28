import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AdminHeader = () => {
  let navigate = useNavigate();

  const user = JSON.parse(sessionStorage.getItem("active-admin"));
  console.log(user);

  const adminLogout = () => {
    toast.success("logged out!!!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("active-admin");
    sessionStorage.removeItem("admin-jwtToken");
    window.location.reload(true);
    setTimeout(() => {
      navigate("/home");
    }, 2000); // Redirect after 3 seconds
  };
  return (
    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
      <li class="nav-item">
        <Link to="/admin/grade/add" class="nav-link active" aria-current="page">
          <b className="text-color"> Add Grade</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link to="/admin/grade/all" class="nav-link active" aria-current="page">
          <b className="text-color">View Grades</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/admin/course/add"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color"> Add Course</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/admin/grade/all/course/"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View Courses</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/user/teacher/register"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Register Teacher</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link to="/exam/all/" class="nav-link active" aria-current="page">
          <b className="text-color">All Exams</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/exam/all/student/result/"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Exam Results</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/admin/teacher/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View Teachers</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to="/admin/student/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View Students</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to=""
          class="nav-link active"
          aria-current="page"
          onClick={adminLogout}
        >
          <b className="text-color">Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default AdminHeader;
