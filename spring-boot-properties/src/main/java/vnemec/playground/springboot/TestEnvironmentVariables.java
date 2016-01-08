package vnemec.playground.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by vnemec on 1/7/16.
 */
@Component
public class TestEnvironmentVariables implements CommandLineRunner {

    @Value("${env.test}") // ENV_TEST
    String env1;

    @Value("${env.testTest}") // ENV_TESTTEST
    String env2;

    @Value("${env.test-test}") // ENV_TEST-TEST
    String env3;

    @Value("${env.test_test}") // ENV_TEST_TEST
    String env4;

    @Value("${env.testTestTest}") // ENV_TESTTESTTEST
    String env5;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(env1);
        System.out.println(env2);
        System.out.println(env3);
        System.out.println(env4);
        System.out.println(env5);
    }
}
