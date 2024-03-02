package ExamPortal.controllers;

import ExamPortal.dto.*;
import ExamPortal.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "localhost:3000")
public class UserController {

	@Autowired
	private UserResource userResource;

	// RegisterUserRequestDto, we will set only email, password & role from UI
	@PostMapping("/admin/register")
	// Api to register Admin
	public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
		return userResource.registerAdmin(request);
	}

	// for student and teacher register
	@PostMapping("/register")
	// Api to register customer or seller user
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
		return this.userResource.registerUser(request);
	}

	@PostMapping("login")
	// Api to log in any User
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		return userResource.login(userLoginRequest);
	}


	@GetMapping("/fetch/role-wise")
	// Api to get Users By Role
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
			throws JsonProcessingException {
		return userResource.getUsersByRole(role);
	}

	@PutMapping("update/status")
	// Api to update the user status
	public ResponseEntity<CommonApiResponse> updateUserStatus(@RequestBody UserStatusUpdateRequestDto request) {
		return userResource.updateUserStatus(request);
	}

	@GetMapping("/fetch/user-id")
	// Api to get User Detail By User Id
	public ResponseEntity<UserResponseDto> fetchUserById(@RequestParam("userId") int userId) {
		return userResource.getUserById(userId);
	}

	@GetMapping("/fetch/student/grade-wise")
	// Api to get Students by grade wise
	public ResponseEntity<UserResponseDto> fetchStudentsByGrade(@RequestParam("gradeId") int gradeId) {
		return userResource.getStudentsByGrade(gradeId);
	}

	@DeleteMapping("/delete/user-id")
	// Api to delete the user by ID
	public ResponseEntity<CommonApiResponse> deleteUserById(@RequestParam("userId") int userId) {
		return userResource.deleteUserById(userId);
	}

}
