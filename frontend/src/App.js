import { Routes, Route } from "react-router-dom";
import Header from "./NavbarComponent/Header";
import AdminRegisterForm from "./UserComponent/AdminRegisterForm";
import UserLoginForm from "./UserComponent/UserLoginForm";
import UserRegister from "./UserComponent/UserRegister";
import AboutUs from "./PageComponent/AboutUs";
import ContactUs from "./PageComponent/ContactUs";
import HomePage from "./PageComponent/HomePage";
import AddGradeForm from "./GradeComponent/AddGradeForm";
import UpdateGradeForm from "./GradeComponent/UpdateGradeForm";
import ViewAllGrades from "./GradeComponent/ViewAllGrades";
import ViewAllCourses from "./CourseComponent/ViewAllCourses";
import AddCourseForm from "./CourseComponent/AddCourseForm";
import UpdateCourseForm from "./CourseComponent/UpdateCourseForm";
import ViewAllTeachers from "./UserComponent/ViewAllTeachers";
import ViewAllStudents from "./UserComponent/ViewAllStudents";
import UserProfile from "./UserComponent/UserProfile";
import ViewStudentGradewise from "./UserComponent/ViewStudentGradewise";
import ViewExamQuestions from "./ExamQuestionComponent/ViewExamQuestions";
import AddExamForm from "./ExamComponent/AddExamForm";
import ViewGradeUpcomingExams from "./ExamComponent/ViewGradeUpcomingExams";
import ViewGradePreviousExams from "./ExamComponent/ViewGradePreviousExams";
import ViewStudentUpcomingExams from "./ExamComponent/ViewStudentUpcomingExams";
import ViewStudentPreviousExams from "./ExamComponent/ViewStudentPreviousExams";
import ViewStudentOngoingExams from "./ExamComponent/ViewStudentOngoingExams";
import StudentExamAttempt from "./ExamComponent/StudentExamAttempt";
import ViewStudentExamResults from "./ExamResultComponent/ViewStudentExamResults";
import ExamResult from "./ExamResultComponent/ExamResult";
import ViewGradeWiseStudentExamResults from "./ExamResultComponent/ViewGradeWiseStudentExamResults";
import ViewAllStudentExamResults from "./ExamResultComponent/ViewAllStudentExamResults";
import ViewAllExams from "./ExamComponent/ViewAllExams";
import StudentExamAttemptSpell from "./ExamComponent/StudentExamAttemptSpell";
import StudentExamAttemptBlanks from "./ExamComponent/StudentExamAttemptBlanks";

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/user/admin/register" element={<AdminRegisterForm />} />
        <Route path="/user/login" element={<UserLoginForm />} />
        <Route path="/user/student/register" element={<UserRegister />} />
        <Route path="/user/teacher/register" element={<UserRegister />} />
        <Route path="/aboutus" element={<AboutUs />} />
        <Route path="/contactus" element={<ContactUs />} />
        <Route path="/admin/grade/add" element={<AddGradeForm />} />
        <Route path="/admin/grade/all" element={<ViewAllGrades />} />
        <Route path="/admin/grade/update" element={<UpdateGradeForm />} />
        <Route path="/admin/course/add" element={<AddCourseForm />} />
        <Route path="/admin/course/all" element={<ViewAllCourses />} />
        <Route path="/admin/course/update" element={<UpdateCourseForm />} />
        <Route
          path="/admin/grade/:gradeId/course/"
          element={<ViewAllCourses />}
        />
        <Route path="/admin/student/all" element={<ViewAllStudents />} />
        <Route path="/admin/teacher/all" element={<ViewAllTeachers />} />
        <Route
          path="/teacher/grade/student/all"
          element={<ViewStudentGradewise />}
        />
        <Route path="/user/profile/detail" element={<UserProfile />} />
        <Route path="/exam/questions" element={<ViewExamQuestions />} />
        
        <Route path="/exam/add" element={<AddExamForm />} />
        <Route
          path="/exam/grade-wise/upcoming"
          element={<ViewGradeUpcomingExams />}
        />
        <Route
          path="/exam/grade-wise/previous"
          element={<ViewGradePreviousExams />}
        />
        <Route
          path="/exam/student/grade-wise/upcoming"
          element={<ViewStudentUpcomingExams />}
        />
        <Route
          path="/exam/student/grade-wise/previous"
          element={<ViewStudentPreviousExams />}
        />
        <Route
          path="/exam/student/grade-wise/previous"
          element={<ViewStudentPreviousExams />}
        />
        <Route
        // On clicking start Exam this is triggerd 
          path="/exam/student/grade-wise/ongoing"
          element={<ViewStudentOngoingExams />}
        />
        <Route path="/exam/student/attempt" element={<StudentExamAttempt />} />
        <Route path="/exam/student/attemptSpell" element={<StudentExamAttemptSpell />} />
        <Route path="/exam/student/attemptBlanks" element={<StudentExamAttemptBlanks />} />

        <Route
          path="/exam/student/result/all"
          element={<ViewStudentExamResults />}
        />
        <Route path="/exam/student/result/" element={<ExamResult />} />
        <Route
          path="/exam/grade/student/result/"
          element={<ViewGradeWiseStudentExamResults />}
        />
        <Route
          path="/exam/all/student/result/"
          element={<ViewAllStudentExamResults />}
        />
        <Route path="/exam/all/" element={<ViewAllExams />} />
      </Routes>
    </div>
  );
}

export default App;
