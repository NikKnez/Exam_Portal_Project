package ExamPortal.entities;

import ExamPortal.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse extends CommonApiResponse {

	private UserDto user;

	private String jwtToken;

}
