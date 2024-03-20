package ExamPortal.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ExamResultResponse extends CommonApiResponse {

	private List<ExamResult> results = new ArrayList<>();

}
