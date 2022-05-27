package projekt.h1_3;

import org.junit.jupiter.api.Test;
import projekt.rwap.ReflectedInstance;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalTest extends ClassSpecTestCase {

    TimeIntervalTest() {
        spec.requireClass(ClassName.TIME_INTERVAL);

        spec.requireConstructor("%s(%s, %s)"
                .formatted(ClassName.TIME_INTERVAL, LocalDateTime.class.getName(), LocalDateTime.class.getName()))
            .requireModifiers(Modifier.PUBLIC);

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

    @Test
    void constructorThrowsOnStartNull() {
        var e = assertThrows(NullPointerException.class, () ->
            getInstance(null, LocalDateTime.now()));
        assertEquals("start", e.getMessage());
    }

    @Test
    void constructorThrowsOnEndNull() {
        var e = assertThrows(NullPointerException.class, () ->
            getInstance(LocalDateTime.now(), null));
        assertEquals("end", e.getMessage());
    }

    private ReflectedInstance getInstance(LocalDateTime start, LocalDateTime end) throws Throwable {
        try {
            return ReflectedInstance
                .getConstructor(spec.getClassToSpec(), LocalDateTime.class, LocalDateTime.class)
                .newInstance(start, end);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
