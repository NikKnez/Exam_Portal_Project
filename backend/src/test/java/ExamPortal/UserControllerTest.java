package ExamPortal;

import ExamPortal.controllers.UserController;
import ExamPortal.dto.*;
import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.UserLoginRequest;
import ExamPortal.entities.UserLoginResponse;
import ExamPortal.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserResource userResource;

    @InjectMocks
    private UserController userController;

    @Test
    void registerAdmin() {
        // Given
        RegisterUserRequestDto request = new RegisterUserRequestDto();
        when(userResource.registerAdmin(request)).thenReturn(ResponseEntity.ok(new CommonApiResponse()));

        // When
        ResponseEntity<CommonApiResponse> result = userController.registerAdmin(request);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).registerAdmin(request);
    }

    @Test
    void registerUser() {
        // Given
        RegisterUserRequestDto request = new RegisterUserRequestDto();
        when(userResource.registerUser(request)).thenReturn(ResponseEntity.ok(new CommonApiResponse()));

        // When
        ResponseEntity<CommonApiResponse> result = userController.registerUser(request);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).registerUser(request);
    }

    @Test
    void login() {
        // Given
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        when(userResource.login(userLoginRequest)).thenReturn(ResponseEntity.ok(new UserLoginResponse()));

        // When
        ResponseEntity<UserLoginResponse> result = userController.login(userLoginRequest);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).login(userLoginRequest);
    }

    @Test
    void fetchAllUsersByRole_shouldThrowJsonProcessingException() throws JsonProcessingException {
        // Given
        String role = "ADMIN";
        when(userResource.getUsersByRole(role)).thenReturn(ResponseEntity.ok(new UserResponseDto()));

        // When
        ResponseEntity<UserResponseDto> result = userController.fetchAllUsersByRole(role);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).getUsersByRole(role);
    }

    @Test
    void updateUserStatus() {
        // Given
        UserStatusUpdateRequestDto request = new UserStatusUpdateRequestDto();
        when(userResource.updateUserStatus(request)).thenReturn(ResponseEntity.ok(new CommonApiResponse()));

        // When
        ResponseEntity<CommonApiResponse> result = userController.updateUserStatus(request);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).updateUserStatus(request);
    }

    @Test
    void fetchUserById() {
        // Given
        int userId = 1;
        when(userResource.getUserById(userId)).thenReturn(ResponseEntity.ok(new UserResponseDto()));

        // When
        ResponseEntity<UserResponseDto> result = userController.fetchUserById(userId);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).getUserById(userId);
    }

    @Test
    void fetchStudentsByGrade() {
        // Given
        int gradeId = 1;
        when(userResource.getStudentsByGrade(gradeId)).thenReturn(ResponseEntity.ok(new UserResponseDto()));

        // When
        ResponseEntity<UserResponseDto> result = userController.fetchStudentsByGrade(gradeId);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).getStudentsByGrade(gradeId);
    }

    @Test
    void deleteUserById() {
        // Given
        int userId = 1;
        when(userResource.deleteUserById(userId)).thenReturn(ResponseEntity.ok(new CommonApiResponse()));

        // When
        ResponseEntity<CommonApiResponse> result = userController.deleteUserById(userId);

        // Then
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userResource, times(1)).deleteUserById(userId);
    }
}

