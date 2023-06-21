package au.com.lucidtech.testsexecution;

import au.com.lucidtech.testsexecution.TestsExecutionApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestsExecutionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void applicationStarts() {
        TestsExecutionApplication.main(new String[]{});
    }
}
