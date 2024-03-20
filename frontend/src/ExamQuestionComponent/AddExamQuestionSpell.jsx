import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { config } from '../ConsantsFile/Constants';
const url = config.url.BASE_URL;

const AddExamQuestionSpell = ({ exam }) => {
  var propsExam = exam;

  let navigate = useNavigate();

  const [questionRequest, setQuestionRequest] = useState({
    question: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    option5: "",
    option6: "",
    option7: "",
    option8: "",
    option9: "",
    option10: "",
    option11: "",
    option12: "",
    option13: "",
    option14: "",
    option15: "",
    option16: "",
    option17: "",
    option18: "",
    option19: "",
    option20: "",
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
      questionRequest.option4 === "" ||
      questionRequest.option5 === "" ||
      questionRequest.option6 === "" ||
      questionRequest.option7 === "" ||
      questionRequest.option8 === "" ||
      questionRequest.option9 === "" ||
      questionRequest.option10 === "" ||
      questionRequest.option11 === "" ||
      questionRequest.option12 === "" ||
      questionRequest.option13 === "" ||
      questionRequest.option14 === "" ||
      questionRequest.option15 === "" ||
      questionRequest.option16 === "" ||
      questionRequest.option17 === "" ||
      questionRequest.option18 === "" ||
      questionRequest.option19 === "" ||
      questionRequest.option20 === ""
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
        "," +
        questionRequest.option5 +
        "," +
        questionRequest.option6 +

        "," +
        questionRequest.option7 +
        "," +
        questionRequest.option8 +
        "," +
        questionRequest.option9 +
        "," +
        questionRequest.option10 +

        "," +
        questionRequest.option11 +
        "," +
        questionRequest.option12 +
        "," +
        questionRequest.option13 +
        "," +
        questionRequest.option14 +

        "," +
        questionRequest.option15 +
        "," +
        questionRequest.option16 +
        "," +
        questionRequest.option17 +
        "," +
        questionRequest.option18 +
        "," +
        questionRequest.option19 +
        "," +
        questionRequest.option20 +

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
                option5: "",
                option6: "",
                option7: "",
                option8: "",
                option9: "",
                option10: "",
                option11: "",
                option12: "",
                option13: "",
                option14: "",
                option15: "",
                option16: "",
                option17: "",
                option18: "",
                option19: "",
                option20: "",
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
                    <b>Spelling Test </b>
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
                    <b>Word 1</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option1"
                    name="option1"
                    placeholder="enter Word 1.."
                    onChange={handleUserInput}
                    value={questionRequest.option1}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 2</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option2"
                    name="option2"
                    placeholder="enter Word 2.."
                    onChange={handleUserInput}
                    value={questionRequest.option2}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 3</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option3"
                    name="option3"
                    placeholder="enter Word 3.."
                    onChange={handleUserInput}
                    value={questionRequest.option3}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 4</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option4"
                    name="option4"
                    placeholder="enter Word 4.."
                    onChange={handleUserInput}
                    value={questionRequest.option4}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 5</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option5"
                    name="option5"
                    placeholder="enter Word 5.."
                    onChange={handleUserInput}
                    value={questionRequest.option5}
                  />
                </div>



                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 6</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option6"
                    name="option6"
                    placeholder="enter Word 6.."
                    onChange={handleUserInput}
                    value={questionRequest.option6}
                  />
                </div>



                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 7</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option7"
                    name="option7"
                    placeholder="enter Word 7.."
                    onChange={handleUserInput}
                    value={questionRequest.option7}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 8</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option8"
                    name="option8"
                    placeholder="enter Word 8.."
                    onChange={handleUserInput}
                    value={questionRequest.option8}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 9</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option9"
                    name="option9"
                    placeholder="enter Word 9.."
                    onChange={handleUserInput}
                    value={questionRequest.option9}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 10</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option10"
                    name="option10"
                    placeholder="enter Word 10.."
                    onChange={handleUserInput}
                    value={questionRequest.option10}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 11</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option11"
                    name="option11"
                    placeholder="enter Word 11.."
                    onChange={handleUserInput}
                    value={questionRequest.option11}
                  />
                </div>



                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 12</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option12"
                    name="option12"
                    placeholder="enter Word 12.."
                    onChange={handleUserInput}
                    value={questionRequest.option12}
                  />
                </div>

                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 13</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option13"
                    name="option13"
                    placeholder="enter Word 13.."
                    onChange={handleUserInput}
                    value={questionRequest.option13}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 14</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option14"
                    name="option14"
                    placeholder="enter Word 14.."
                    onChange={handleUserInput}
                    value={questionRequest.option14}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 15</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option15"
                    name="option15"
                    placeholder="enter Word 15.."
                    onChange={handleUserInput}
                    value={questionRequest.option15}
                  />
                </div>
                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 16</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option16"
                    name="option16"
                    placeholder="enter Word 16.."
                    onChange={handleUserInput}
                    value={questionRequest.option16}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 17</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option17"
                    name="option17"
                    placeholder="enter Word 17.."
                    onChange={handleUserInput}
                    value={questionRequest.option17}
                  />
                </div>



                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 18</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option18"
                    name="option18"
                    placeholder="enter Word 18.."
                    onChange={handleUserInput}
                    value={questionRequest.option18}
                  />
                </div>


                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 19</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option19"
                    name="option19"
                    placeholder="enter Word 19.."
                    onChange={handleUserInput}
                    value={questionRequest.option19}
                  />
                </div>



                <div class="mb-3">
                  <label for="title" class="form-label">
                    <b>Word 20</b>
                  </label>
                  <input
                    type="text"
                    class="form-control"
                    id="option20"
                    name="option20"
                    placeholder="enter Word 20.."
                    onChange={handleUserInput}
                    value={questionRequest.option20}
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
                      Word 1
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
                      Word 2
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
                      Word 3
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
                      Word 4
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
export default AddExamQuestionSpell;
