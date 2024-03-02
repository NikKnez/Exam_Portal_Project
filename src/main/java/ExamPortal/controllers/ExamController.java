package ExamPortal.controllers;

import ExamPortal.dto.AddExamRequest;
import ExamPortal.dto.CommonApiResponse;
import ExamPortal.dto.ExamResponseDto;
import ExamPortal.resource.ExamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exam")
@CrossOrigin(origins = "http://localhost:3000")
public class ExamController {

	@Autowired
	private ExamResource exmaResource;

	@PostMapping("/add")
	// Api to add exam
	public ResponseEntity<ExamResponseDto> addCourse(@RequestBody AddExamRequest request) {
		return exmaResource.addExam(request);
	}

	@GetMapping("/fetch/all")
	// Api to fetch all exams
	public ResponseEntity<ExamResponseDto> fetchAllExams() {
		return exmaResource.fetchAllExams();
	}

	@GetMapping("/fetch/all/grade-wise")
	// Api to fetch all exams by grades
	public ResponseEntity<ExamResponseDto> fetchAllExambyGrade(@RequestParam("gradeId") int gradeId) {
		return exmaResource.fetchAllExamByGrade(gradeId);
	}

	@GetMapping("/fetch/all/course-wise")
	// Api to fetch all exams by course
	public ResponseEntity<ExamResponseDto> fetchAllExambyCourse(@RequestParam("courseId") int courseId) {
		return exmaResource.fetchAllExambyCourse(courseId);
	}

	@GetMapping("/fetch/exam-id")
	// Api to fetch Exam using ID
	public ResponseEntity<ExamResponseDto> fetchExamById(@RequestParam("examId") int examId) {
		return exmaResource.fetchExamById(examId);
	}

	@DeleteMapping("/delete")
	// Api to delete exam
	public ResponseEntity<CommonApiResponse> deleteExam(@RequestParam("examId") int examId) {
		return exmaResource.deleteExam(examId);
	}

	@GetMapping("/fetch/upcoming/grade-wise")
	// Api to fetch all upcoming exams by grade
	public ResponseEntity<ExamResponseDto> fetchUpcomingExamsByGrade(@RequestParam("gradeId") int gradeId,
			@RequestParam("role") String role) {
		return exmaResource.fetchUpcomingExamsByGrade(gradeId, role);
	}

	@GetMapping("/fetch/previous/grade-wise")
	// Api to fetch all previous exams by grade
	public ResponseEntity<ExamResponseDto> fetchPreviousExamsByGrade(@RequestParam("gradeId") int gradeId,
			@RequestParam("role") String role) {
		return exmaResource.fetchPreviousExamsByGrade(gradeId, role);
	}

	@GetMapping("/fetch/grade-wise/ongoing")
	// Api to fetch all ongoing exams by grade
	public ResponseEntity<ExamResponseDto> fetchOngoingExamsByGrade(@RequestParam("gradeId") int gradeId,
			@RequestParam("studentId") int studentId, @RequestParam("role") String role) {
		return exmaResource.fetchOngoingExamsByGrade(gradeId, studentId, role);
	}

}
