package com.lc.hello.auto;

import com.lc.hello.beans.HelloProperties;
import com.lc.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)//默认HelloProperties放在容器中
public class HelloServiceAutoConfiguration {

	@ConditionalOnMissingBean(HelloService.class)
	@Bean
	public HelloService helloService() {
		return new HelloService();
	}
}
