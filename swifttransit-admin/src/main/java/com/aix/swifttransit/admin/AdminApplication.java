package com.aix.swifttransit.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = {"com.aix.swifttransit"})
@Slf4j
public class AdminApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(AdminApplication.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();

        String contextPath = env.getProperty("server.servlet.context-path", "");
        String port = env.getProperty("server.port");
        String applicationName = env.getProperty("spring.application.name");
        String externalAddress = InetAddress.getLocalHost().getHostAddress();

        log.info("""
                        ----------------------------------------------------------
                        Application '{}' is running! Access URLs:
                        Local:      http://localhost:{}{}
                        External:   http://{}:{}{}
                        Doc:        http://{}:{}{}/doc.html
                        ----------------------------------------------------------
                        """,
                applicationName, port, contextPath,
                externalAddress, port, contextPath,
                externalAddress, port, contextPath);
    }
}
