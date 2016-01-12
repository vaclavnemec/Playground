package vnemec.playground.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

public class SpringBootContextsApplicationTests {

	@Test
	public void beanOverriding() {

		ApplicationContext parent = new AnnotationConfigApplicationContext(TestConfiguration.class);

		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.register(TestConfiguration2.class);
		child.setParent(parent);
		child.refresh();

		TestBean bean = child.getBean(TestBean.class);

		Assert.assertEquals("TestConfiguration2", bean.getValue());

		Map<String, TestBean> beansOfType = child.getBeansOfType(TestBean.class);

		Assert.assertEquals(1, beansOfType.size());

		TestBean notInChild = child.getBean("notInChild", TestBean.class);

		Assert.assertEquals("notInChild", notInChild.getValue());
	}

}
