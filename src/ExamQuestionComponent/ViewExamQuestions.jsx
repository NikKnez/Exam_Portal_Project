import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import AddExamQuestion from "./AddExamQuestion";
import ViewAllQuestions from "./ViewAllQuestions";
import AddExamQuestionSpell from "./AddExamQuestionSpell";

const ViewExamQuestions = () => {
  const location = useLocation();

  let [exam, setExam] = useState(location.state);

  let selectedView;

  // Determine which view to render based on the variableValue
  switch (exam.examType) {
    case 'Multiple':
      selectedView = <AddExamQuestion exam={exam} />;
      break;
    case 'Spell':
      selectedView = <AddExamQuestionSpell exam={exam} />;
      break;
    case 'Blank':
      selectedView = <AddExamQuestion exam={exam} />;
      break;
    case 'Match':
      selectedView = <AddExamQuestionSpell exam={exam} />;
      break;
    default:
      selectedView = <AddExamQuestion exam={exam} />;
  }




  return (
    <div className="container-fluid mb-5">
      <div class="row">

        <div class="col-sm-4 mt-2">

        {selectedView}



          
          {/* <AddExamQuestion exam={exam} /> */}
          {/* <AddExamQuestionSpell exam={exam} />
          <div>
            {console.log('Writing the contents ')}
            {console.log(exam)}; 
            {console.log('Writing the Exam id ')}
            {console.log(exam.id)}; 
            {console.log('Writing the Cource id ')}
            {console.log(exam.course.id)}; 
            {console.log('Trying to fetch exam type  ')}
            {console.log(exam.course)}; 

            {console.log('Trying to fetch exam type corrected one')}
            {console.log(exam.examType)}; 
          </div> */}




        </div>

        <div class="col-sm-8 mt-2">
          <ViewAllQuestions exam={exam} />
        </div>
      </div>
    </div>
  );
};

export default ViewExamQuestions;
