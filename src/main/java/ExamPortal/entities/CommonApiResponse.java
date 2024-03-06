package ExamPortal.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonApiResponse {

	private String responseMessage;

	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
