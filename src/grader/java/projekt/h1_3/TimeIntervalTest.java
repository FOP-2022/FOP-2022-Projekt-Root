package projekt.h1_3;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDateTime;

class TimeIntervalTest extends ClassSpecTestCase {

    TimeIntervalTest() {
        spec.requireClass(ClassName.TIME_INTERVAL);

        spec.requireField("start")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(LocalDateTime.class);

        spec.requireField("end")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(LocalDateTime.class);

        spec.requireMethod("getStart()")
            .requireModifiers(Modifier.PUBLIC)
            .requireType(LocalDateTime.class);

        spec.requireMethod("getEnd()")
            .requireModifiers(Modifier.PUBLIC)
            .requireType(LocalDateTime.class);

        spec.requireMethod("getDuration()")
            .requireModifiers(Modifier.PUBLIC)
            .requireType(Duration.class);
    }
}
