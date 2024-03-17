package demo.hexagon.infrastructure.annotation;

import java.lang.annotation.*;

@Target({ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DrivenAdapter {
}