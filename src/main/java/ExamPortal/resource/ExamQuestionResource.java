package ExamPortal.resource;

import ExamPortal.dto.AddQuestionDto;
import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.entities.Exam;
import ExamPortal.entities.Question;
import ExamPortal.services.*;
import ExamPortal.utility.Constants.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class ExamQuestionResource {

	private final Logger LOG = LoggerFactory.getLogger(ExamResource.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private ExamQuestionService examQuestionService;

	@Autowired
	private ExamService examService;

	public ResponseEntity<QuestionsResponseDto> addExamQuestion(AddQuestionDto request) {

		LOG.info("Request received for add exam question");

		QuestionsResponseDto response = new QuestionsResponseDto();

		if (request == null || request.getExamId() == 0) {
			response.setResponseMessage("missing input or bad request");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Exam exam = this.examService.getExamById(request.getExamId());

		if (exam == null) {
			response.setResponseMessage("Exam not found");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Question> questions = exam.getQuestions();

		if (!AddQuestionDto.validate(request)) {
			response.setQuestions(questions);
			response.setResponseMessage("missing input or bad request");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Question question = AddQuestionDto.toQuestionEntity(request);

		if (question == null) {
			response.setQuestions(questions);
			response.setResponseMessage("Question not found");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		question.setCorrectAnswer(request.getCorrectAnswer());
		question.setStatus(ActiveStatus.ACTIVE.value());
		question.setExams(Arrays.asList(exam));

		Question updateQuestion = this.examQuestionService.addExamQuestion(question);

		if (updateQuestion == null) {
			response.setQuestions(questions);
			response.setResponseMessage("Failed to add exam question");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		questions.add(updateQuestion);
		
		for (Question q : questions) {
			q.setCorrectAns(q.getCorrectAnswer());
			q.setAnswer(5);
		}
		
		response.setQuestions(questions);
		response.setResponseMessage("Exam Question Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<QuestionsResponseDto> deleteExamQuestion(int questionId) {

		LOG.info("Request received for deleting the Question using Id");

		QuestionsResponseDto response = new QuestionsResponseDto();

		if (questionId == 0) {
			response.setResponseMessage("missing input or bad request");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		Question question = this.examQuestionService.getExamQuestionById(questionId);

		if (question == null) {
			response.setResponseMessage("Question not found");
			response.setSuccess(false);

			return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.BAD_REQUEST);
		}
		
		// Remove the question from associated exams
		for (Exam exam : question.getExams()) {
		    exam.getQuestions().remove(question);
		}

		this.examQuestionService.deleteQuestion(question);
		
		response.setResponseMessage("Exam Questions Deleted Successful");
		response.setSuccess(true);

		return new ResponseEntity<QuestionsResponseDto>(response, HttpStatus.OK);

	}

}
