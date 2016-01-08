package vnemec.playground.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vnemec on 1/8/16.
 */
@Component
@ConfigurationProperties
public class TestEnvVariables2 {

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    private Map<String, String> props = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        System.out.println();
    }


}
