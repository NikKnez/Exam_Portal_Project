import AdminHeader from "./AdminHeader";
import HeaderStudent from "./HeaderStudent";
import HeaderTeacher from "./HeaderTeacher";
import NormalHeader from "./NormalHeader";

const RoleNav = () => {
  const teacher = JSON.parse(sessionStorage.getItem("active-teacher"));
  const admin = JSON.parse(sessionStorage.getItem("active-admin"));
  const student = JSON.parse(sessionStorage.getItem("active-student"));

  if (teacher != null) {
    return <HeaderTeacher />;
  } else if (admin != null) {
    return <AdminHeader />;
  } else if (student != null) {
    return <HeaderStudent />;
  } else {
    return <NormalHeader />;
  }
};

export default RoleNav;
