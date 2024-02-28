package ExamPortal.controllers;

//import io.swagger.v3.oas.annotations.Operation;
import ExamPortal.dto.AddCourseRequest;
import ExamPortal.dto.CommonApiResponse;
import ExamPortal.dto.CourseResponseDto;
import ExamPortal.resource.CourseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

	@Autowired
	private CourseResource courseResource;

	@PostMapping("/add")
	//@Operation(summary = "Api to add course")
	public ResponseEntity<CommonApiResponse> addCourse(@RequestBody AddCourseRequest request) {
		return courseResource.addCourse(request);
	}

	@PutMapping("/update")
	//@Operation(summary = "Api to update course")
	public ResponseEntity<CommonApiResponse> updateCourse(@RequestBody AddCourseRequest course) {
		return courseResource.updateCourse(course);
	}

	@GetMapping("/fetch/all")
	//@Operation(summary = "Api to fetch all course")
	public ResponseEntity<CourseResponseDto> fetchAllCourse() {
		return courseResource.fetchAllCourse();
	}

	@GetMapping("/fetch/all/grade-wise")
	//@Operation(summary = "Api to fetch all course by grades")
	public ResponseEntity<CourseResponseDto> fetchAllCoursebyGrade(@RequestParam("gradeId") int gradeId) {
		return courseResource.fetchAllCourseByGrade(gradeId);
	}
	
	@GetMapping("/fetch/id")
	//@Operation(summary = "Api to fetch all course by grades")
	public ResponseEntity<CourseResponseDto> fetchCourseById(@RequestParam("courseId") int courseId) {
		return courseResource.fetchCourseById(courseId);
	}

	@DeleteMapping("/delete")
	//@Operation(summary = "Api to delete course")
	public ResponseEntity<CommonApiResponse> deleteCourse(@RequestParam("courseId") int courseId) {
		return courseResource.deleteCourse(courseId);
	}
	
}
