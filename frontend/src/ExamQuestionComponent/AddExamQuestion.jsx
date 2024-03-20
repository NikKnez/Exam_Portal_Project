import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const AddExamQuestion = ({ exam }) => {
  var propsExam = exam;

  let navigate = useNavigate();

  const [questionRequest, setQuestionRequest] = useState({
    question: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    correctAnswer: "",
    marks: "",
    examId: propsExam.id,
  });

  const handleUserInput = (e) => {
    setQuestionRequest({
      ...questionRequest,
      [e.target.name]: e.target.value,
    });
  };

  const teacher_jwtToken = sessionStorage.getItem("teacher-jwtToken");

  const saveExamQuestion = (e) => {
    if (
      questionRequest.option1 === "" ||
      questionRequest.option2 === "" ||
      questionRequest.option3 === "" ||
      questionRequest.option4 === ""
    ) {
      toast.error("Give Proper Options for Question!!!", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else if (questionRequest.correctAnswer === "") {
      toast.error("Select Correct Answer!!!", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else if (questionRequest.marks <= 0) {
      toast.error("Enter Valid Marks!!!", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else {
      let option =
        "[" +
        questionRequest.option1 +
        "," +
        questionRequest.option2 +
        "," +
        questionRequest.option3 +
        "," +
        questionRequest.option4 +
        "]";

      fetch(url + "/exam/question/add", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          //    Authorization: "Bearer " + teacher_jwtToken,
        },
        body: JSON.stringify({
          question: questionRequest.question,
          correctAnswer: questionRequest.correctAnswer,
          options: option,
          examId: questionRequest.examId,
          marks: questionRequest.marks,
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

              exam.questions = res.questions;
              setQuestionRequest({
                question: "",
                option1: "",
                option2: "",
                option3: "",
                option4: "",
                correctAnswer: "",
                marks: "",
                examId: propsExam.id,
              });

              navigate("/exam/questions", { state: exam });
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
    }

    e.preventDefault();
  };

  return (
    <div>
      <div class="mt-2">
        <div class="form-card border-color">
          <div className="container-fluid">
            <div
              className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
              style={{
                borderRadius: "1em",
                height: "38px",
              }}
            >
              <h5 class="card-title">Add Question</h5>
            </div>
            <div class="card-body text-color mt-3">
              <form>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Question</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="question"
                    name="question"
                    placeholder="enter question.."
                    onChange={handleUserInput}
                    value={questionRequest.name}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Option 1</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option1"
                    name="option1"
                    placeholder="enter option 1.."
                    onChange={handleUserInput}
                    value={questionRequest.option1}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Option 2</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option2"
                    name="option2"
                    placeholder="enter option 2.."
                    onChange={handleUserInput}
                    value={questionRequest.option2}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Option 3</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option3"
                    name="option3"
                    placeholder="enter option 3.."
                    onChange={handleUserInput}
                    value={questionRequest.option3}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Option 4</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option4"
                    name="option4"
                    placeholder="enter option 4.."
                    onChange={handleUserInput}
                    value={questionRequest.option4}
                  />
                </div>

                <label for="title" class="form-label">
                  <b>Select Correct Answer</b>
                </label>

                <div class="row">
                  <div class="col-md-6">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        name="correctAnswer"
                        value="0"
                        id="flexCheckDefault1"
                        onChange={handleUserInput}
                      />
                      <label class="form-check-label" for="flexCheckDefault1">
                        Option 1
                      </label>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        id="flexCheckDefault2"
                        name="correctAnswer"
                        value="1"
                        onChange={handleUserInput}
                      />
                      <label class="form-check-label" for="flexCheckDefault2">
                        Option 2
                      </label>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        name="correctAnswer"
                        value="2"
                        id="flexCheckDefault3"
                        onChange={handleUserInput}
                      />
                      <label class="form-check-label" for="flexCheckDefault3">
                        Option 3
                      </label>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        type="checkbox"
                        id="flexCheckDefault4"
                        name="correctAnswer"
                        value="3"
                        onChange={handleUserInput}
                      />
                      <label class="form-check-label" for="flexCheckDefault4">
                        Option 4
                      </label>
                    </div>
                  </div>
                </div>

                <div class="mb-3 mt-3">
                  <label for="title" class="form-label">
                    <b>Marks</b>
                  </label>
                  <input
                    type="number"
                    class="form-control"
                    id="marks"
                    name="marks"
                    placeholder="enter marks.."
                    onChange={handleUserInput}
                    value={questionRequest.marks}
                  />
                </div>

                <div className="d-flex aligns-items-center justify-content-center mb-2 mt-2">
                  <button
                    type="submit"
                    onClick={saveExamQuestion}
                    class="btn bg-color custom-bg-text"
                  >
                    Add Question
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

export default AddExamQuestion;
