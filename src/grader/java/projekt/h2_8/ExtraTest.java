package projekt.h2_8;

import org.junit.jupiter.api.Test;
import projekt.food.Extra;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExtraTest {

    @Test
    public void testSortByPriorityOnly() {
        final TutorConfig config = new TutorConfig();
        final List<TutorExtra> actualOrder = new ArrayList<>();
        final List<TutorExtra> extras = List.of(
            new TutorExtra("a", 2, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 9, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 4, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 5, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 3, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 0, (c, e) -> actualOrder.add(e))
        );
        Extra.writeToConfig(config, extras);
        assertArrayEquals(new int[]{0, 2, 3, 4, 5, 9},
            actualOrder.stream().mapToInt(TutorExtra::getPriority).toArray(),
            "Extras not applied in order of priority");
    }

    @Test
    public void testSortComplete() {
        final TutorConfig config = new TutorConfig();
        final List<TutorExtra> actualOrder = new ArrayList<>();
        final List<TutorExtra> extras = List.of(
            new TutorExtra("x", 0, (c, e) -> actualOrder.add(e)),
            new TutorExtra("f", 2, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 2, (c, e) -> actualOrder.add(e)),
            new TutorExtra("b", 2, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 5, (c, e) -> actualOrder.add(e)),
            new TutorExtra("a", 3, (c, e) -> actualOrder.add(e)),
            new TutorExtra("g", 0, (c, e) -> actualOrder.add(e))
        );
        Extra.writeToConfig(config, extras);
        assertArrayEquals(new int[]{0, 0, 2, 2, 2, 3, 5},
            actualOrder.stream().mapToInt(TutorExtra::getPriority).toArray(),
            "Extras not applied in order of priority and name");
        assertArrayEquals(new String[]{"g", "x", "a", "b", "f", "a", "a"},
            actualOrder.stream().map(TutorExtra::getName).toArray(),
            "Extras not applied in order of priority and name");
    }

    public static class SpecTest extends ClassSpecTestCase {
        public SpecTest() {
            spec.requireClass(ClassName.EXTRA);

            spec.requireMethod("writeToConfig(C , List<? extends Extra<? super C>>)")
                .requireModifiers(Modifier.PUBLIC | Modifier.STATIC)
                .requireType(void.class);
        }
    }
}
