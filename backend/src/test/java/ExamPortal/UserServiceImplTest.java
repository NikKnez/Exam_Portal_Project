package ExamPortal;

import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.repositories.UserRepository;
import ExamPortal.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser() {
        // Given
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // When
        User result = userService.addUser(user);

        // Then
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        // Given
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // When
        User result = userService.updateUser(user);

        // Then
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserByEmailAndStatus() {
        // Given
        String emailId = "test@example.com";
        String status = "ACTIVE";
        User expectedUser = new User();
        when(userRepository.findByEmailIdAndStatus(emailId, status)).thenReturn(expectedUser);

        // When
        User result = userService.getUserByEmailAndStatus(emailId, status);

        // Then
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByEmailIdAndStatus(emailId, status);
    }

    @Test
    void getUserByEmailid() {
        // Given
        String emailId = "test@example.com";
        User expectedUser = new User();
        when(userRepository.findByEmailId(emailId)).thenReturn(expectedUser);

        // When
        User result = userService.getUserByEmailid(emailId);

        // Then
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByEmailId(emailId);
    }

    @Test
    void findByRoleAndStatusIn() {
        // Given
        String emailId = "test@example.com";
        List<String> statusList = new ArrayList<>();
        User expectedUser = new User();
        when(userRepository.findByRoleAndStatusIn(emailId, statusList)).thenReturn(expectedUser);

        // When
        User result = userService.findByRoleAndStatusIn(emailId, statusList);

        // Then
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByRoleAndStatusIn(emailId, statusList);
    }

    @Test
    void getUserByRole() {
        // Given
        String role = "ADMIN";
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findByRole(role)).thenReturn(expectedUsers);

        // When
        List<User> result = userService.getUserByRole(role);

        // Then
        assertEquals(expectedUsers, result);
        verify(userRepository, times(1)).findByRole(role);
    }

    @Test
    void getUserById() {
        // Given
        int userId = 1;
        Optional<User> optionalUser = Optional.of(new User());
        when(userRepository.findById(userId)).thenReturn(optionalUser);

        // When
        User result = userService.getUserById(userId);

        // Then
        assertTrue(optionalUser.isPresent());
        assertEquals(optionalUser.get(), result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserByEmailIdAndRoleAndStatus() {
        // Given
        String emailId = "test@example.com";
        String role = "ADMIN";
        String status = "ACTIVE";
        User expectedUser = new User();
        when(userRepository.findByEmailIdAndRoleAndStatus(emailId, role, status)).thenReturn(expectedUser);

        // When
        User result = userService.getUserByEmailIdAndRoleAndStatus(emailId, role, status);

        // Then
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByEmailIdAndRoleAndStatus(emailId, role, status);
    }

    @Test
    void updateAllUser() {
        // Given
        List<User> users = new ArrayList<>();
        when(userRepository.saveAll(users)).thenReturn(users);

        // When
        List<User> result = userService.updateAllUser(users);

        // Then
        assertEquals(users, result);
        verify(userRepository, times(1)).saveAll(users);
    }

    @Test
    void getUserByRoleAndStatus() {
        // Given
        String role = "STUDENT";
        String status = "ACTIVE";
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findByRoleAndStatus(role, status)).thenReturn(expectedUsers);

        // When
        List<User> result = userService.getUserByRoleAndStatus(role, status);

        // Then
        assertEquals(expectedUsers, result);
        verify(userRepository, times(1)).findByRoleAndStatus(role, status);
    }

    @Test
    void getUsersByRoleAndGradeAndStatus() {
        // Given
        String role = "STUDENT";
        Grade grade = new Grade();
        String status = "ACTIVE";
        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findByRoleAndGradeAndStatus(role, grade, status)).thenReturn(expectedUsers);

        // When
        List<User> result = userService.getUsersByRoleAndGradeAndStatus(role, grade, status);

        // Then
        assertEquals(expectedUsers, result);
        verify(userRepository, times(1)).findByRoleAndGradeAndStatus(role, grade, status);
    }
}

