package com.lucidtech.usercrudwithjwt.samples;

import com.lucidtech.usercrudwithjwt.controller.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
//In this test, the full Spring application context is started but without the server.
// We can narrow the tests to only the web layer by using @WebMvcTest
//In this test, Spring Boot instantiates only the web layer rather than the whole context.
// In an application with multiple controllers, you can even ask for only one to be instantiated by using,
// for example, @WebMvcTest(GreetingController.class).
class StartSpringContextWithSpecificClassAndWithWebLayerOnlyButWithoutServerTest {
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private GreetingService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
//        when(service.greet()).thenReturn("Hello, Mock");
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("World")));
    }
}