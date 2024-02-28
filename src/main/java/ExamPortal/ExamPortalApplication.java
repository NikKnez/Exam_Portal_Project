package ExamPortal;

import ExamPortal.entities.User;
import ExamPortal.services.UserService;
import ExamPortal.utility.Constants.ActiveStatus;
import ExamPortal.utility.Constants.UserRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(ExamPortalApplication.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User admin = this.userService.getUserByEmailIdAndRoleAndStatus("demo.admin@demo.com",
				UserRole.ROLE_ADMIN.value(), ActiveStatus.ACTIVE.value());

		if (admin == null) {

			LOG.info("Admin not found in system, so adding default admin");

			User user = new User();
			user.setEmailId("admin@demo.com");
			user.setPassword(passwordEncoder.encode("admin123"));
			user.setRole(UserRole.ROLE_ADMIN.value());
			user.setStatus(ActiveStatus.ACTIVE.value());


			this.userService.addUser(user);

		}

	}

}
