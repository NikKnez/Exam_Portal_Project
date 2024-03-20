package ExamPortal.services;


import ExamPortal.entities.Course;
import ExamPortal.entities.Grade;

import java.util.List;

public interface CourseService {
	
	Course addCourse(Course course);

	Course updateCourse(Course course);

	Course getCourseById(int courseId);

	List<Course> getAllCoursesByStatus(String status);
	
	List<Course> getAllCoursesByGradeAndStatus(Grade grade, String status);

}
