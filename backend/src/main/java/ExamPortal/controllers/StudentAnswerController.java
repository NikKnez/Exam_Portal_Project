package ExamPortal.controllers;

import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.entities.StudentAnswerRequest;
import ExamPortal.resource.StudentAnswerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student/answer")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentAnswerController {
	
	@Autowired
	private StudentAnswerResource studentAnswerResource;
	
	@PostMapping("/add")
	// Api to add student answers
	public ResponseEntity<QuestionsResponseDto> addExamQuestion(@RequestBody StudentAnswerRequest request) {
		return studentAnswerResource.addStudentAnswers(request);
	}

}
