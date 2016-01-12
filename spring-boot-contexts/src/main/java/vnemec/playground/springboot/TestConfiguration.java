package vnemec.playground.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vnemec on 1/12/16.
 */
@Configuration
public class TestConfiguration {

    @Bean
    public TestBean bean() {
        return new TestBean("TestConfiguration");
    }

    @Bean(name = "notInChild")
    public TestBean bean2() {
        return new TestBean("notInChild");
    }

}
