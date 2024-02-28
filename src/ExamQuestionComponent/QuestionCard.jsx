import { useState } from "react";

const QuestionCard = (props) => {
  const [question, setQuestion] = useState(props.question);

  function convertStringToArray(inputString) {
    return inputString
      .replace(/^\[|\]$/g, "") // Remove square brackets
      .split(",")
      .map((item) => item.trim())
      .map((item) => item.replace(/"/g, "")); // Remove double quotes if present
  }

  return (
    <div className="mt-3">
      <div className="card-body text-color">
        <h5 className="card-title d-flex justify-content-between text-color-second">
          <div>
            <b>{props.serialNumber + "] " + question.question}</b>
          </div>
          <div>
            <b>
              <span className="text-color">
                {"  [" + question.marks + " Marks]"}
              </span>
            </b>
          </div>
        </h5>
      </div>
      <div className="row">
        <div className="col-6">
          <h6 className="option">
            {"1." + convertStringToArray(question.options)[0]}
          </h6>
          <h6 className="option">
            {"2." + convertStringToArray(question.options)[1]}
          </h6>
        </div>
        <div className="col-6">
          <h6 className="option">
            {"3." + convertStringToArray(question.options)[2]}
          </h6>
          <h6 className="option">
            {"4." + convertStringToArray(question.options)[3]}
          </h6>
        </div>
      </div>
      <div className="row">
        <b className="col">
          <span className="text-success">
            {"Correct Answer: " +
              convertStringToArray(question.options)[question.correctAns]}
          </span>
        </b>

        {(() => {
          if (question.answer !== 5) {
            if (question.answer === 4) {
              return (
                <b className="col">
                  {"Student Answer: "}
                  <span className="text-danger">Not Attempted</span>
                </b>
              );
            } else {
              return (
                <b className="col">
                  <span className="text-color">
                    {"Student Answer: " +
                      convertStringToArray(question.options)[question.answer]}
                  </span>
                </b>
              );
            }
          }
        })()}
      </div>
    </div>
  );
};

export default QuestionCard;
