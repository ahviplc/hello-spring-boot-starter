# hello-spring-boot-starter

> 一个Spring Boot的自定义starter项目

> https://gitee.com/ahviplc/hello-spring-boot-starter

# 私有github maven仓库

`可在这里找到公共使用我的方法说明`

GitHub - ahviplc/maven-repository: LC的私人maven仓库.
> https://github.com/ahviplc/maven-repository

> https://github.com/ahviplc/maven-repository#引入hello-spring-boot-starter包

# 一些链接

```markdown
Spring Boot 2 学习笔记（2 / 2）_巨輪的博客-CSDN博客
https://blog.csdn.net/u011863024/article/details/113667946

【尚硅谷】SpringBoot2零基础入门教程（spring boot2干货满满）_哔哩哔哩_bilibili
https://www.bilibili.com/video/BV19K4y1L7MT?vd_source=1a3881036e38f83afc581040124b1056

Spring Boot 2 学习笔记（1 / 2）_巨輪的博客-CSDN博客
https://blog.csdn.net/u011863024/article/details/113667634

Spring Boot 2 学习笔记（2 / 2）_巨輪的博客-CSDN博客
https://blog.csdn.net/u011863024/article/details/113667946

vulcan: vulcan:一个 Spring Boot 多模块项目
https://gitee.com/ahviplc/vulcan

MySpringBootTemplate: 一个SpringBoot开发模板项目,内置了优雅的处理全局异常 - 这是单项目版本
https://gitee.com/ahviplc/MySpringBootTemplate

6-里面有新增json-可以在配置自有属性的时候有提示- SpringBoot自定义Starter_JAVA葵花宝典的博客-CSDN博客
https://blog.csdn.net/b644ROfP20z37485O35M/article/details/89037542

Let's make SpringBoot app start faster - DEV Community
https://dev.to/bufferings/lets-make-springboot-app-start-faster-k9m
```

# 如何使用

## 先安装到本地maven仓库

`用maven插件，将两工程install到本地maven仓库`

> maven clean install

## 引入使用

`使用下面的 Spring Boot 多模块项目模板或者自己手动新建一个 Spring Boot 项目`

vulcan: vulcan:一个 Spring Boot 多模块项目
> https://gitee.com/ahviplc/vulcan

在`vulcan-web/pom.xml` 中 加入其依赖

```xml
 <!-- 引入`hello-spring-boot-starter`依赖 -->
<dependency>
    <groupId>com.lc</groupId>
    <artifactId>hello-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

在 `vulcan-web/src/main/resources/application.properties` 中加入自定义属性

以 `@ConfigurationProperties("lc.hello")` 开头

```
lc.hello.prefix=hello
lc.hello.suffix=6666
```

## 测试效果

> vulcan-web/src/main/java/com/lc/vulcan/web/controller/myRestController.java

`注入服务 使用服务 即可`

```java
package com.lc.vulcan.web.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import com.lc.hello.service.HelloService;
import com.lc.vulcan.beans.myAspect.annotation.SysLog;
import com.lc.vulcan.beans.myException.myEntity.ResultBody;
import com.lc.vulcan.common.constants.VulcanConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller 控制层
 * <p>
 * 一些共用的接口
 */
@RestController
@RequestMapping(value = "/api")
public class myRestController {

	@Autowired
	HelloService helloService;

	// 这种写法也可赋值到AAA
	@Value("${lc.hello.prefix}")
	String AAA;

	/**
	 * ping pong 测试接口
	 *
	 * @return
	 */
	@SysLog(value = "我是ping接口切面日志")
	@RequestMapping(value = "/ping", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
	public ResultBody ping() {
		Console.log("{} ping ... pong ...", DateUtil.now());
		// 故意创造一个异常
		// Integer.valueOf("ssss");
		System.out.println(AAA);
		return ResultBody.success(VulcanConstants.PONG + helloService.sayHello("lc"));
	}
}
```

## Done

# Enjoy...
