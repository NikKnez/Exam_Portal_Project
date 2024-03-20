package ExamPortal.resource;

import ExamPortal.entities.CommonApiResponse;
import ExamPortal.dto.GradeResponseDto;
import ExamPortal.entities.Grade;
import ExamPortal.exception.GradeSaveFailedException;
import ExamPortal.services.GradeService;
import ExamPortal.utility.Constants.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GradeResource {

	private final Logger LOG = LoggerFactory.getLogger(GradeResource.class);

	@Autowired
	private GradeService gradeService;
	
	public ResponseEntity<CommonApiResponse> addGrade(Grade grade) {
		
		LOG.info("Request received for add grade");

		CommonApiResponse response = new CommonApiResponse();

		if (grade == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		grade.setStatus(ActiveStatus.ACTIVE.value());

		Grade savedGrade = this.gradeService.addGrade(grade);

		if (savedGrade == null) {
			throw new GradeSaveFailedException("Failed to add grade");
		}

		response.setResponseMessage("Grade Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}
	
    public ResponseEntity<CommonApiResponse> updateGrade(Grade grade) {
		
		LOG.info("Request received for add grade");

		CommonApiResponse response = new CommonApiResponse();

		if (grade == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (grade.getId() == 0) {
			response.setResponseMessage("missing grade Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		grade.setStatus(ActiveStatus.ACTIVE.value());
		Grade savedGrade = this.gradeService.updateGrade(grade);

		if (savedGrade == null) {
			throw new GradeSaveFailedException("Failed to update grade");
		}

		response.setResponseMessage("Grade Updated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<GradeResponseDto> fetchAllGrade() {

		LOG.info("Request received for fetching all grades");

		GradeResponseDto response = new GradeResponseDto();

		List<Grade> grades = new ArrayList<>();

		grades = this.gradeService.getAllGradesByStatus(ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(grades)) {
			response.setResponseMessage("No Grades found");
			response.setSuccess(false);

			return new ResponseEntity<GradeResponseDto>(response, HttpStatus.OK);
		}

		response.setGrades(grades);
		response.setResponseMessage("Grade fetched successful");
		response.setSuccess(true);

		return new ResponseEntity<GradeResponseDto>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> deleteGrade(int gradeId) {

		LOG.info("Request received for deleting grade");

		CommonApiResponse response = new CommonApiResponse();

		if (gradeId == 0) {
			response.setResponseMessage("missing grade Id");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Grade grade = this.gradeService.getGradeById(gradeId);

		if (grade == null) {
			response.setResponseMessage("grade not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		grade.setStatus(ActiveStatus.DEACTIVATED.value());
		Grade updatedGrade = this.gradeService.updateGrade(grade);

		if (updatedGrade == null) {
			throw new GradeSaveFailedException("Failed to delete the Grade");
		}

		response.setResponseMessage("Grade Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

}
