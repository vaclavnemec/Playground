package vnemec.playground.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by vnemec on 12/22/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootPropertiesApplication.class)
public class PropertySourcesLoaderTest {

    @Autowired
    ResourceLoader resourceLoader;

    ConfigurableEnvironment environment = new StandardEnvironment();

    PropertySourcesLoader loader;

    @Before
    public void setUp() {
        loader = new PropertySourcesLoader();
    }

    @Test
    public void propertySourcesLoaderTest_SimpleProperties() throws IOException {
        Resource app = resourceLoader.getResource("app.properties");

        loader.load(app);

        MutablePropertySources propertySources = loader.getPropertySources();

        for (PropertySource<?> source : propertySources) {
            environment.getPropertySources().addFirst(source);
        }

        Assert.assertEquals("app properties", environment.getProperty("message1"));
        Assert.assertEquals("app properties", environment.getProperty("message2"));
        Assert.assertNull(environment.getProperty("message3"));
    }

    @Test
    public void propertySourceLoaderTest_PropertiesOrder() throws IOException {
        Resource some = resourceLoader.getResource("classpath:/meta/some.properties");
        Resource app = resourceLoader.getResource("classpath:/app.properties");

        loader.load(some);
        loader.load(app);

        MutablePropertySources propertySources = loader.getPropertySources();

        for (PropertySource<?> source : propertySources) {
            environment.getPropertySources().addFirst(source);
        }

        Assert.assertEquals("app properties", environment.getProperty("message1"));
        Assert.assertEquals("app properties", environment.getProperty("message2"));
        Assert.assertEquals("meta some properties", environment.getProperty("message3"));
    }

    @Test
    public void propertySourceLoaderTest_PropertiesProfiles() throws IOException {
        environment.addActiveProfile("prod");

        Resource prod = resourceLoader.getResource("classpath:/app-prod.properties");
        Resource app = resourceLoader.getResource("classpath:/app.properties");

        loader.load(prod, "prod");
        loader.load(app, "prod");

        MutablePropertySources propertySources = loader.getPropertySources();

        for (PropertySource<?> source : propertySources) {
            environment.getPropertySources().addFirst(source);
        }

        // :) null, it is not working as I expected.. you need to load properties file without any profile specified..
        Assert.assertNull(environment.getProperty("message1"));
        Assert.assertNull(environment.getProperty("message2"));
        Assert.assertNull(environment.getProperty("message3"));
    }

    @Test
    public void testYmlProfiles() throws IOException {
        environment.addActiveProfile("development");

        Resource yml = resourceLoader.getResource("classpath:/other.yml");

        loader.load(yml, "development");

        MutablePropertySources propertySources = loader.getPropertySources();

        for (PropertySource<?> source : propertySources) {
            environment.getPropertySources().addFirst(source);
        }

        // :) null, it is not working as I expected.. you need to load properties file without any profile specified..
        Assert.assertEquals("127.0.0.1", environment.getProperty("server.address"));
    }

}
