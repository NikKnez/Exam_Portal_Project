import Carousel from "./Carousel";
import Footer from "../NavbarComponent/Footer";
import { Link } from "react-router-dom";
import exam_1 from "../images/exam_1.png";
import exam_2 from "../images/exam_2.png";




<link href="path/to/tailwind.min.css" rel="stylesheet"></link>

const HomePage = () => {

  const data = [
    { class: 'Grade A', time: '17:00 to 18:00', days: 'MON, WED, SAT' },
    { class: 'Grade B', time: '18:00 to 19:00', days: 'MON, THUR, SAT' },
    { class: 'Grade C', time: '19:00 to 20:00', days: 'WED, THUR, SAT' },
  ];






  return (
    <div className="container-fluid mb-2">
      <div className="container mt-5">
        <div className="row">
          <div className="col-md-8 text-color">
            <h1>Welcome to Exam Portal </h1>
            <p>
              <br></br>
           
              <h3>
              Unlock your full potential with our tailored educational sessions
              </h3>
            </p>
            <ul>

<li>Engaging Weekly Sessions.</li>
<li>Comprehensive Syllabus Coverage, Topic by Topic.</li>
<li>Topic-Specific Exams.</li>
<li>Mock Exams for Effective Preparation</li>
<li>Dedicated Sessions for Grade 1 to Grade 3.</li>
<li>Flexible Learning: Individual and Group Sessions.</li>

</ul>
<br></br>

<h3>Session Schedule: </h3>




<div className="overflow-x-auto">
      <table className="table-auto min-w-full">
        <thead>
          <tr>
         
            <th className="px-4 py-2">Class</th>
            <th className="px-4 py-2">Time</th>
            <th className="px-4 py-2">Days</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id} className="bg-gray-100 border-b">
              
              <td className="px-4 py-2">{item.class}</td>
              <td className="px-4 py-2">{item.time}</td>
              <td className="px-4 py-2">{item.days}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>




            <p>
            <p>
              <h3>
              <br></br>

              Mock Exams 
              </h3>
            </p>
            </p>
            <Link to="/user/login" className="btn bg-color custom-bg-text">
              Get Started
            </Link>
          </div>
          <div className="col-md-4">
            <img
              src={exam_1}
              alt="Logo"
              width="400"
              height="auto"
              className="home-image"
            />
          </div>
        </div>

        <div className="row mt-5">
          <div className="col-md-4">
            <img
              src={exam_2}
              alt="Logo"
              width="350"
              height="auto"
              className="home-image"
            />
          </div>
          <div className="col-md-8 text-color">
          <br></br>

            <h1 className="ms-5">Simplify and Upgrade Your skills</h1>
          
            <Link to="/user/login" className="btn bg-color custom-bg-text ms-5">
              Get Started
            </Link>
          </div>
        </div>
      </div>
      <hr />

    
    

      <Footer />
    </div>
  );
};

export default HomePage;
