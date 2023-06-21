package au.com.lucidtech.jacocoexecution;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JacocoExecutionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void applicationStarts() {
        JacocoExecutionApplication.main(new String[]{});
    }
}
