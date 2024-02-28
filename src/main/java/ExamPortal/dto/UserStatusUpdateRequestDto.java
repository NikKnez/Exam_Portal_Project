package ExamPortal.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStatusUpdateRequestDto {

	private int userId;

	private String status;

}
