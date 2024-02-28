package ExamPortal.controllers;

//import io.swagger.v3.oas.annotations.Operation;
import ExamPortal.dto.AddQuestionDto;
import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.resource.ExamQuestionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exam/question")
@CrossOrigin(origins = "http://localhost:3000")
public class ExamQuestionController {
	
	@Autowired
	private ExamQuestionResource examQuestionResource;
	
	@PostMapping("/add")
	//@Operation(summary = "Api to add exam question")
	public ResponseEntity<QuestionsResponseDto> addExamQuestion(@RequestBody AddQuestionDto request) {
		return examQuestionResource.addExamQuestion(request);
	}
	
	@DeleteMapping("/delete")
	// @Operation(summary = "Api to delete exam question")
	public ResponseEntity<QuestionsResponseDto> deleteQuestion(@RequestParam("questionId") int questionId) {
		return examQuestionResource.deleteExamQuestion(questionId);
	}
	
	

}
