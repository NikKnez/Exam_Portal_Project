import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import CountdownTimer from "./CountdownTimer";
import { config } from '../ConsantsFile/Constants';


const url = config.url.BASE_URL;






const StudentExamAttemptBlanks = () => {
  const location = useLocation();
  const exam = location.state;

  const [calculatedTime, setCalculatedTime] = useState(null);
  
  const handleCalculateTime = () => {
    const currentTime = new Date().getTime();
    // const now = new Date().getTime();
    //const newTime = new Date(currentTime.getTime() + 20 * 60000); // Adding 10 minutes in milliseconds
    const newTime = currentTime + (20 * 60000);

    setCalculatedTime(newTime);
    exam.endTime = newTime;
  };


  const LeftPanel = () => {
    return (
     
      <div className="left-panel" style={{ height: "600px", overflowY: "auto" }} >
        {/* PDF viewer component */}
        {/* <iframe src={require('./../resourcesfiles/SpTest1.pdf')} width="100%" height="100%"></iframe> */}

        {/* <iframe src='/resourceFiles/maths/SAT Buster_B_T5.pdf' width="100%" height="100%"></iframe> */}
        {/* <iframe src="file:///E:/Dheekshi/sa.pdf" width="100%" height="100%"></iframe> */}

        <iframe src={exam.path} width="100%" height="100%"></iframe>
        {/* <iframe src="file:///E:/Dheekshi%20School/SATs/Maths/CGP%20book/Tests/SAT%20Buster_A_T1.pdf" width="100%" height="100%"></iframe> */}
        {/* Not allowed to load local resource: file:///E:/Dheekshi%20School/SandA.pdf */}
       {/* // file:///E:/Dheekshi%20School/SandA.pdf */}
      </div>
    );
  };
  


  let navigate = useNavigate();

  var questions = exam.questions;
  const student = JSON.parse(sessionStorage.getItem("active-student"));
  const [timeUp, setTimeUp] = useState(false);

  const student_jwtToken = sessionStorage.getItem("student-jwtToken");

  const [answers, setAnswers] = useState({});

  


  const handleChange = (questionId, value) => {
    setAnswers((prevAnswers) => ({
      ...prevAnswers,
      [questionId]: value,
    }));
   // console.log('Handle on Change'); 
  //  console.log(questionId); 
   // console.log(value); 

    console.log('Handle Answers'); 
    console.log(answers); 

   // console.log(answers); 
  };


  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an array of question objects with student answers
    const studentResponses = questions.map(
      ({ id, question, options, marks, status}, index) => ({
        id,
        question,
        options,
        marks,
        status,
       // studentAnswer: answers[id] !== undefined ? answers[id] : 4,
        studentAnswer: answers[id],
      })

    
    );

    // Log the array of question objects with student answers
    console.log('Printing student resonses ')
    console.log("Student Responses:", studentResponses);

    fetch(url + "/student/answer/add", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        //    Authorization: "Bearer " + admin_jwtToken,
      },
      body: JSON.stringify({
        examId: exam.id,
        studentId: student.id,
        questions: studentResponses,
      }),
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
              navigate("/home"); // sending added exam object
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
  };

  useEffect(() => {
    console.log("Updated Exam in Child Component:", exam);
    console.log('End Time starting ', exam.endTime);
    console.log('Duration of the Exam ', exam.duration);

    console.log('Description', exam.description); 
    console.log('Description', exam.path); 
    handleCalculateTime();

    console.log('End time calculated one : ' + calculatedTime);
  }, [exam]);


  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      e.preventDefault(); // Prevent form submission
    }
  };


  return (

    <div >
   
   
  


   <div className="container-fluid mt-2">
      <div className="form-card border-color">
        <div className="container-fluid">
          <div
            className="card-header bg-color custom-bg-text mt- d-flex justify-content-center align-items-center"
            style={{
              borderRadius: "1em",
              height: "38px",
            }}
          >
            <h5 className="card-title d-flex justify-content-between">
              <span className="me-5">
                {exam.name + " - " + exam.grade.name + " - " + exam.course.name}
              </span>

              {/* <CountdownTimer endTime={exam.endTime} /> */}

              <CountdownTimer endTime={exam.duration} />

            </h5>
          </div>

          
          <LeftPanel />
        


          <div className="card-body text-color mt-3">
            <div className="col-md-12 mb-5">
              <div className="container mt-4 d-flex justify-content-center">
                <form
                  onSubmit={(e) => handleSubmit(e)}
                  className="container mt-12"
                >
                  <div className="row"  >
                    {questions.map(
                      ({ id, question, options, marks }, index) => (
                        <div key={id} className="col-md-2 mb-1">
                          <p className="fw-bold text-color-second">
                            {/* {index + 1}. {question}{" "} */}
                            <p> {index + 1}.
                            
                            <input
                                    type="text" spellCheck="false"
                                    class="form-control"
                                    key={id}                                  
                                  
                                    onChange={e => handleChange(id, e.target.value)}
                                    onKeyDown={handleKeyDown}

                                  />
                                                     
                            
                             </p>
                            {/* <span className="text-color">
                              {"[" + marks + " Marks]"}
                            </span> */}
                          
                          
                          {/* {options
                            .replace(/[\[\]]/g, "")
                            .split("#")
                            .map((option, optionIndex) => (
                              <div key={optionIndex} className="form-check">
                              
                                <div class="mb-3">
                                  <label for="title" class="form-label">

                                  </label>
                                  
                                </div>

                              </div>
                            ))} */}
                            </p>
                        </div>
                      )
                    )}
                  </div>
                  <div className="container mt-4 d-flex justify-content-center">
                    <button
                      type="submit"
                      className="btn bg-color custom-bg-text"
                    >
                      Submit Exam
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>



  
    </div>// added with left panel
  );
};



export default StudentExamAttemptBlanks;
