package au.com.lucidtech.testsexecution.samples;

import au.com.lucidtech.testsexecution.controller.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//The @SpringBootTest annotation tells Spring Boot to look for a main
// configuration class (one with @SpringBootApplication, for instance) and use that to start a Spring application context.
class StartFullSpringContextWithoutServerIT {
    @Autowired
    private GreetingController controller;

    @Test
    //The first thing we can do is write a simple sanity check test that will fail if the application context cannot start.
    public void contextLoads() {
    }

    @Test
    //To convince ourselves that the context is creating our controller, we could add an assertion
    public void controllerIsNotNull() {
        assertThat(controller).isNotNull();
    }
}