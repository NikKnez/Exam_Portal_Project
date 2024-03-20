import { useState } from "react";

const QuestionSpellCard = (props) => {
  const [question, setQuestion] = useState(props.question);

  function convertStringToArray(inputString) {
    return inputString
      .replace(/^\[|\]$/g, "") // Remove square brackets
      .split("#")
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
       
        </div>
      
      </div>
      <div className="row">
        <b className="col">
          <span className="text-success">
            {"Correct Answer: " +
              convertStringToArray(question.options)[question.correctAns]}
          </span>
        </b>
        
        <b className="col">
          <span className="text-success">
            
            {
           
              <div> <span className="text-success">Student Answer : </span>{ question.studentAnswer} </div>
            }
              
          </span>
        </b>
       
      </div>


    </div>
  );
};

export default QuestionSpellCard;
