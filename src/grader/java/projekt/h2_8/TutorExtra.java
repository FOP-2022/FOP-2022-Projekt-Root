package projekt.h2_8;

import projekt.food.Extra;

import java.util.Objects;
import java.util.function.BiConsumer;

public class TutorExtra implements Extra<TutorConfig> {

    private final String name;
    private final int priority;
    private final BiConsumer<TutorConfig, TutorExtra> applier;

    public TutorExtra(final String name, final int priority, final BiConsumer<TutorConfig, TutorExtra> applier) {
        this.name = name;
        this.priority = priority;
        this.applier = applier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void apply(final TutorConfig config) {
        applier.accept(config, this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TutorExtra that = (TutorExtra) o;
        return priority == that.priority
            && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }
}
