package ExamPortal.dto;

import ExamPortal.entities.CommonApiResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserResponseDto extends CommonApiResponse {

	private List<UserDto> users = new ArrayList<>();

}
