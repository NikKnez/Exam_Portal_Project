package ExamPortal.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {

	private String emailId;

	private String password;

	private String role;

}
