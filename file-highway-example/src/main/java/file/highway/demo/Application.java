package file.highway.demo;


import file.highway.annotation.EnableHighway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Intro
 * @Author liutengfei
 */
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.servlet.multipart.max-file-size","100MB");
        System.setProperty("spring.servlet.multipart.max-request-size","100MB");
        SpringApplication.run(HighwayDemo.class, args);

    }

    @SpringBootApplication
    @EnableHighway
    public static class HighwayDemo {

    }


}

