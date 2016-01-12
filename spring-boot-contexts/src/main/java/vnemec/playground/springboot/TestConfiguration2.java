package vnemec.playground.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vnemec on 1/12/16.
 */
@Configuration
public class TestConfiguration2 {

    @Bean
    public TestBean testBean() {
        return new TestBean("TestConfiguration2");
    }

}
