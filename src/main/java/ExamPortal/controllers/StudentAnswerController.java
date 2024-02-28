package ExamPortal.controllers;

import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.dto.StudentAnswerRequest;
import ExamPortal.resource.StudentAnswerResourse;
//import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student/answer")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentAnswerController {
	
	@Autowired
	private StudentAnswerResourse studentAnswerResourse;
	
	@PostMapping("/add")
	//@Operation(summary = "Api to add student answers")
	public ResponseEntity<QuestionsResponseDto> addExamQuestion(@RequestBody StudentAnswerRequest request) {
		return studentAnswerResourse.addStudentAnswers(request);
	}

}
