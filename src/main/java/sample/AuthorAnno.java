package sample;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PACKAGE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorAnno {
    String name() default "Xue Yuhao";
    String studentNumber() default "171250641";
    int revision() default 1;
}