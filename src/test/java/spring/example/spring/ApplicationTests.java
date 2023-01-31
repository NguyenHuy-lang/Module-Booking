package spring.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.function.Function;
import spring.example.spring.User;
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		var user = User.builder().age("17").name("NguyenHuy").build();
		Function<User, String> function = user::getAge;
		System.out.println(user.getName());
		System.out.println(function.apply(user));
	}
	

}
