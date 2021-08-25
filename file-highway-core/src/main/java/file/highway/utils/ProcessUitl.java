package file.highway.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Intro
 * @Author liutengfei
 */
public interface ProcessUitl {
    static <T> List<T> processOrder(ApplicationContext applicationContext, Class<T> cls){
        Map<String, T> processMap = applicationContext.getBeansOfType(cls);
        List<T> exports = processMap
                .values()
                .stream()
                .collect(Collectors.toList());
        AnnotationAwareOrderComparator.sort(exports);
        return exports;
    }
}
