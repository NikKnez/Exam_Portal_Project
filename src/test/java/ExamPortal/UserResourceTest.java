package ExamPortal;

import ExamPortal.dto.*;
import ExamPortal.entities.*;
import ExamPortal.resource.UserResource;
import ExamPortal.services.AddressService;
import ExamPortal.services.GradeService;
import ExamPortal.services.UserService;
import ExamPortal.utility.Constants.UserRole;
import ExamPortal.utility.Constants.ActiveStatus;
import ExamPortal.utility.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    private UserService userService;

    @Mock
    private AddressService addressService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private UserResource userResource;

    @Test
    void registerAdmin_successfulRegistration() {
        // Arrange
        RegisterUserRequestDto registerRequest = new RegisterUserRequestDto();
        registerRequest.setEmailId("admin@example.com");
        registerRequest.setPassword("password");

        when(userService.getUserByEmailAndStatus("admin@example.com", ActiveStatus.ACTIVE.value())).thenReturn(null);
        when(userService.addUser(any(User.class))).thenReturn(new User());

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = userResource.registerAdmin(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isSuccess());
    }

    @Test
    void registerUser_successfulRegistration() {
        // Arrange
        RegisterUserRequestDto registerRequest = new RegisterUserRequestDto();
        registerRequest.setEmailId("user@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRole(UserRole.ROLE_STUDENT.value());
        registerRequest.setGradeId(1);

        Grade grade = new Grade();
        grade.setId(1);

        when(userService.getUserByEmailAndStatus("user@example.com", ActiveStatus.ACTIVE.value())).thenReturn(null);
        when(gradeService.getGradeById(1)).thenReturn(grade);
        when(userService.addUser(any(User.class))).thenReturn(new User());
        when(addressService.addAddress(any(Address.class))).thenReturn(new Address());

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = userResource.registerUser(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isSuccess());
    }

    @Test
    void login_successfulLogin() {
        // Arrange
        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmailId("user@example.com");
        loginRequest.setPassword("password");
        loginRequest.setRole(UserRole.ROLE_STUDENT.value());

        User user = new User();
        user.setEmailId("user@example.com");
        user.setPassword("encodedPassword");
        user.setRole(UserRole.ROLE_STUDENT.value());
        user.setStatus(ActiveStatus.ACTIVE.value());

        when(userService.getUserByEmailIdAndRoleAndStatus(anyString(), anyString(), anyString())).thenReturn(user);
        when(jwtUtils.generateToken(anyString())).thenReturn("jwtToken");

        // Act
        ResponseEntity<UserLoginResponse> responseEntity = userResource.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertNotNull(responseEntity.getBody().getJwtToken());
    }

    @Test
    void getUsersByRole_successfulFetch() {
        // Arrange
        String role = UserRole.ROLE_STUDENT.value();

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmailId("user@example.com");
        users.add(user);

        when(userService.getUserByRoleAndStatus(role, ActiveStatus.ACTIVE.value())).thenReturn(users);

        // Act
        ResponseEntity<UserResponseDto> responseEntity = userResource.getUsersByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertFalse(responseEntity.getBody().getUsers().isEmpty());
    }

    @Test
    void updateUserStatus_successfulUpdate() {
        // Arrange
        UserStatusUpdateRequestDto request = new UserStatusUpdateRequestDto();
        request.setUserId(1);
        request.setStatus(ActiveStatus.DEACTIVATED.value());

        User user = new User();
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = userResource.updateUserStatus(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
    }

    @Test
    void getUserById_successfulFetch() {
        // Arrange
        int userId = 1;

        User user = new User();
        user.setId(userId);
        user.setEmailId("user@example.com");

        when(userService.getUserById(userId)).thenReturn(user);

        // Act
        ResponseEntity<UserResponseDto> responseEntity = userResource.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertFalse(responseEntity.getBody().getUsers().isEmpty());
    }

    @Test
    void getStudentsByGrade_successfulFetch() {
        // Arrange
        int gradeId = 1;

        Grade grade = new Grade();
        grade.setId(gradeId);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setRole(UserRole.ROLE_STUDENT.value());
        users.add(user);

        when(gradeService.getGradeById(gradeId)).thenReturn(grade);
        when(userService.getUsersByRoleAndGradeAndStatus(UserRole.ROLE_STUDENT.value(), grade, ActiveStatus.ACTIVE.value())).thenReturn(users);

        // Act
        ResponseEntity<UserResponseDto> responseEntity = userResource.getStudentsByGrade(gradeId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isSuccess());
        assertFalse(responseEntity.getBody().getUsers().isEmpty());
    }

    @Test
    void deleteUserById_successfulDeletion() {
        // Arrange
        int userId = 1;

        User user = new User();
        user.setId(userId);

        when(userService.getUserById(userId)).thenReturn(user);
        when(userService.updateUser(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = userResource.deleteUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isSuccess());
    }


}

