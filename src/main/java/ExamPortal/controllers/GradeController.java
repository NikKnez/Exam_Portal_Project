package ExamPortal.controllers;

//import io.swagger.v3.oas.annotations.Operation;
import ExamPortal.dto.CommonApiResponse;
import ExamPortal.dto.GradeResponseDto;
import ExamPortal.entities.Grade;
import ExamPortal.resource.GradeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/grade")
@CrossOrigin(origins = "http://localhost:3000")
public class GradeController {
	
	@Autowired
	private GradeResource gradeResource;
	
	@PostMapping("/add")
	//@Operation(summary = "Api to add grade")
	public ResponseEntity<CommonApiResponse> addGrade(@RequestBody Grade grade) {
		return gradeResource.addGrade(grade);
	}
	
	@PutMapping("/update")
	//@Operation(summary = "Api to update grade")
	public ResponseEntity<CommonApiResponse> updateGrade(@RequestBody Grade grade) {
		return gradeResource.updateGrade(grade);
	}
	
	@GetMapping("/fetch/all")
	//@Operation(summary = "Api to fetch all grade")
	public ResponseEntity<GradeResponseDto> fetchAllGrade() {
		return gradeResource.fetchAllGrade();
	}
	
	@DeleteMapping("/delete")
	//@Operation(summary = "Api to delete grade")
	public ResponseEntity<CommonApiResponse> deleteGrade(@RequestParam("gradeId") int gradeId) {
		return gradeResource.deleteGrade(gradeId);
	}

}
