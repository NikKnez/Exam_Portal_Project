package ExamPortal.services.impl;

import ExamPortal.repositories.UserRepository;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmailAndStatus(String emailId, String status) {
		return userRepository.findByEmailIdAndStatus(emailId, status);
	}

	@Override
	public User getUserByEmailid(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	@Override
	public User findByRoleAndStatusIn(String emailId, List<String> status) {
		return userRepository.findByRoleAndStatusIn(emailId, status);
	}

	@Override
	public List<User> getUserByRole(String role) {
		return userRepository.findByRole(role);
	}

	@Override
	public User getUserById(int userId) {

		Optional<User> optionalUser = this.userRepository.findById(userId);

        return optionalUser.orElse(null);

	}
	
	@Override
	public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return this.userRepository.findByEmailIdAndRoleAndStatus(emailId, role, status);
	}

	@Override
	public List<User> updateAllUser(List<User> users) {
		return this.userRepository.saveAll(users);
	}

	@Override
	public List<User> getUserByRoleAndStatus(String role, String status) {
		return this.userRepository.findByRoleAndStatus(role, status);
	}

	@Override
	public List<User> getUsersByRoleAndGradeAndStatus(String role, Grade grade, String status) {
		return this.userRepository.findByRoleAndGradeAndStatus(role, grade, status);
	}
	
}
