import React, { useRef ,useState } from 'react';
import { ToastContainer, toast } from "react-toastify";
import emailjs from '@emailjs/browser';

const ContactUs = () => {

  const [submitted, setSubmitted] = useState(false);
  const form = useRef();

  const sendEmail = (e) => {
    e.preventDefault();

    emailjs.sendForm('service_nfpgg6g', 'template_lvu2iwh', form.current, 'iDwvUQnW8W3704oM6')
      .then((result) => {
        console.log(result.text);
      }, (error) => {
        console.log(error.text);
      });

      setSubmitted(true);
  };


  return (
    <div className="text-color ms-5 me-5 mr-5 mt-3">
      <h4 className="mb-3">Contact Us</h4>
      <b>
        We value your feedback, questions, and inquiries.
        Feel free to reach out through the provided
        contact form. Your satisfaction is our priority, and we look forward to hearing from you.
      </b>


      {/* <div className="d-flex align-items-center justify-content-center ms-5 mt-1 me-5 mb-3"> */}

      <div className="col-md-6 mb-3 text-color">
        <div className="card rounded-card h-100 shadow-lg">
          <div className="card-body">
            <h4 className="card-title text-color-second text-center">
              Contact Form
            </h4>
            <form ref={form} onSubmit={sendEmail}>


              <div className="col-md-6 mb-3 text-color">
                <label htmlFor="title" className="form-label">
                  <b>Name</b>
                </label>
                <input
                  type="text"
                  className="form-control"

                  name="user_name"

                />
              </div>

              <div className="col-md-6 mb-3 text-color">
                <b>
                  <label className="form-label">Email Id</label>
                </b>
                <input
                  type="email"
                  className="form-control"

                  name="user_email"

                />
              </div>






              <div className="col-md-6 mb-3">
                <label htmlFor="contact" className="form-label">
                  <b>Contact No</b>
                </label>
                <input
                  type="number"
                  className="form-control"

                  name="user_phone"

                />
              </div>





              <div className="col-md-6 mb-3">
                  <label htmlFor="description" className="form-label">
                    <b> Message</b>
                  </label>
                  <textarea
                    className="form-control"
               
                    name="message"
                    rows="6" cols="60"
              
                  />
                </div>


                <div className="col-md-6 mb-3">
      {submitted ? (
        <p><h5> Thank you for reaching out. We will get back to you shortly. </h5> </p>
      ) : (
             
                <input
                  type="submit"
                  className="btn bg-color custom-bg-text"
                  value="Send"
                />) }
              </div> 


            </form>


          </div>
        </div>
      </div>

    </div>

  );
};

export default ContactUs;


