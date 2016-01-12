package vnemec.playground.springboot;

import org.springframework.stereotype.Component;

/**
 * Created by vnemec on 1/12/16.
 */
public class TestBean {

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public TestBean(String value) {
        this.value = value;
    }
}
