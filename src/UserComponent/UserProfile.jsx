import { useParams, useNavigate, Link } from "react-router-dom";
import { useLocation } from "react-router-dom";

const UserProfile = () => {
  const location = useLocation();
  var user = location.state; // use this in case of Student & Teacher

  return (
    <div>
      {/* User Profile Card */}
      <div className="d-flex align-items-center justify-content-center ms-5 mt-1 me-5 mb-3">
        <div
          className="card rounded-card h-100 shadow-lg"
          style={{
            width: "900px",
          }}
        >
          <div className="card-body">
            <h4 className="card-title text-color-second text-center">
              Personal Detail
            </h4>
            <div>
              <p className="mb-2">
                <b>
                  Role: <span className="text-color-second"> {user.role}</span>
                </b>
              </p>
            </div>
            <div className="row mt-4">
              <div className="col-md-4">
                <p className="mb-2">
                  <b>First Name:</b> {user.firstName}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b>Last Name:</b> {user.lastName}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b>Email Id:</b> {user.emailId}
                </p>
              </div>
            </div>
            <div className="row mt-2">
              <div className="col-md-4">
                <p className="mb-2">
                  <b>Contact:</b> {user.phoneNo}
                </p>
              </div>
              <div className="col-md-4">
                <p className="mb-2">
                  <b>Address:</b>{" "}
                  {user.address.street +
                    " " +
                    user.address.city +
                    " " +
                    user.address.pincode}
                </p>
              </div>

              <div className="col-md-4">
                <p className="mb-2">
                  <b>Grade:</b> {user.grade.name}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
