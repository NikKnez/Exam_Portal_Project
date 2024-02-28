package ExamPortal.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse extends CommonApiResponse {

	private UserDto user;

	private String jwtToken;

}
