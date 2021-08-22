package com.wanggl.h2sql;

import cn.hutool.core.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableJpaAuditing
@SpringBootApplication
public class H2sqlApplication {

    private static final Logger logger = LoggerFactory.getLogger(H2sqlApplication.class);

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws FileNotFoundException {
        FileUtil.mkdir(new File("./log"));
        PrintStream ps = new PrintStream("./log/progress.txt");
        ps.print(1);
        ps.flush();
        applicationContext = SpringApplication.run(H2sqlApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        logStartup(env);
        ps.close();
    }

    public static void shutdown() {
        applicationContext.close();
    }

    private static void logStartup(Environment env) {
        String appName = env.getProperty("spring.application.name");
        if (null == appName) {
            appName = "";
        }
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (null == contextPath || "".equals(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("The host name could not be determined, using `localhost` as fallback");
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "URL: \t{}://{}:{}{}\n\t" +
                        "Doc: \t{}://{}:{}{}/doc.html\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                appName,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());
        System.out.println("启动成功！首页访问地址：http://localhost:" + serverPort);
    }

}
