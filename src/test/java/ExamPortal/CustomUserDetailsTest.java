package ExamPortal;

import ExamPortal.config.CustomUserDetails;
import ExamPortal.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    @Test
    void userDetailsCreation() {
        // Arrange
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER,ROLE_ADMIN");

        // Act
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // Assert
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertEquals(2, authorities.size());

        List<String> expectedRoles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        for (GrantedAuthority authority : authorities) {
            assertTrue(expectedRoles.contains(authority.getAuthority()));
        }

        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }
}

