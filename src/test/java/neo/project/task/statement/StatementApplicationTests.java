package neo.project.task.statement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatementApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testConstructor() {
		new StatementApplication();
	}

	@Test
	void testMainMethod() {
		String[] args = {};
		StatementApplication.main(args);
	}
}
