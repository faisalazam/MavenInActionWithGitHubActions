package au.com.lucidtech.testsexecution.samples;

import au.com.lucidtech.testsexecution.controller.GreetingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleUnitTest {

    @Test
    public void shouldTestGreeting() {
        Assertions.assertTrue((new GreetingController().greeting("Faisal")).content().contains("Faisal"));
    }
    @Test
    public void shouldTestGreeting1() {
        assertTrue((new GreetingController().greeting("Faisal1")).content().contains("Faisal1"));
    }
    @Test
    public void shouldTestGreeting2() {
        assertTrue((new GreetingController().greeting("Faisal2")).content().contains("Faisal2"));
    }
    @Test
    public void shouldTestGreeting3() {
        assertTrue((new GreetingController().greeting("Faisal4")).content().contains("Faisal4"));
    }

}