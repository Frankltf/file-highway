package file.highway.annotation;

import file.highway.spring.HighwayComponentScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Intro
 * @Author liutengfei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(HighwayComponentScanRegistrar.class)
public @interface EnableHighway {
}
