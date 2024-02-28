import Carousel from "./Carousel";
import Footer from "../NavbarComponent/Footer";
import { Link } from "react-router-dom";
import exam_1 from "../images/exam_1.png";
import exam_2 from "../images/exam_2.png";




<link href="path/to/tailwind.min.css" rel="stylesheet"></link>

const HomePage = () => {

  const data = [
    { class: 'Year 4', time: '6 PM to 7 PM', days: 'MON,WED,SAT' },
    { class: 'Year 5', time: '5 PM to 6 PM', days: 'MON,THUR,SAT' },
    { class: 'Year 6', time: '7 PM to 8 PM', days: 'WED,THUR,SAT' },
    { class: 'Year 7', time: '6 PM to 7 PM', days: 'TUE,FRI' },
    { class: 'Year 8', time: '7 PM to 8 PM', days: 'WED,FRI' },
    { class: 'GCSE', time: '6 PM to 7 PM', days: 'MON,TUE' },
  ];






  return (
    <div className="container-fluid mb-2">
  {/* <Carousel /> */}

      <div className="container mt-5">
        <div className="row">
          <div className="col-md-8 text-color">
            <h1>Welcome to Exam Portal </h1>
            <p>
              <br></br>
           
              <h3>
              Maximize your learning with our personalized sessions
              </h3>
            </p>
            <ul>

<li>3 Weekly sessions, each lasting one hour.</li>
<li>The syllabus is coverd topic by topic.</li>
<li>Topic specific exams.</li>
<li>Mock exams.</li>
<li>Separate sessions for Year 4, Year 5, Year 6, Year 7, Year 8, Year 9, GCSE and A Level.</li>

</ul>
<br></br>

<h3>We offer both individual and group sessions. </h3>




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
            {/* <p className="ms-5">
              Welcome to a hassle-free academic journey with our Online Exam
              Portal, where navigating assessments is a breeze. Say goodbye to
              complexities as you effortlessly manage your exams in a
              user-friendly environment. With a straightforward interface,
              submitting exams and accessing results becomes a seamless process,
              all from the comfort of your device.
            </p>
            <p className="ms-5">
              Experience the convenience of streamlined academic assessments.
              Our platform is designed for simplicity, ensuring you can focus on
              your exams without unnecessary stress. From straightforward
              submissions to easy result retrieval, we've made online exams a
              straightforward and user-friendly experience, redefining how you
              approach assessments.
            </p> */}
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
