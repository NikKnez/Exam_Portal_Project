import React, { useState, useEffect } from "react";
  const CountdownTimer = ({ endTime }) => {
    const [timeLeft, setTimeLeft] = useState(endTime * 60);
    console.log('Duration : ', endTime) ; 
    useEffect(() => {
      const timer = setInterval(() => {
        setTimeLeft(prevTimeLeft => prevTimeLeft - 1);
      }, 1000);
  
      return () => clearInterval(timer);
    }, []); // Empty dependency array so the effect runs only once when the component mounts
  
    const hours = Math.floor(timeLeft / 3600);
    const minutes = Math.floor((timeLeft % 3600) / 60);
    const seconds = timeLeft % 60;

    return (
      <div>

     <p>
        
        Time Remaining: {minutes}{" "}
        minutes, {seconds} seconds
       </p>
        
       
      </div>
    );
  };
  
export default CountdownTimer;
