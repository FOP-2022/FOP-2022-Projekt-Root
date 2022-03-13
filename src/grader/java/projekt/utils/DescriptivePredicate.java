package projekt.utils;

import java.util.function.Predicate;

/**
 * This class represents a predicate with a description. It can be used anywhere a normal predicate can be used instead, but it
 * offers a method, {@link DescriptivePredicate#getDescription()}, to get a description of what this predicate is supposed to
 * match.
 *
 * @param <T> the type this predicate is applicable to
 */
@SuppressWarnings("ClassCanBeRecord")
public class DescriptivePredicate<T> implements Predicate<T> {

    private final Predicate<T> predicate;
    private final String description;

    /**
     * Constructs a new {@link DescriptivePredicate} with description {@code description} and using {@code predicate} to delegate
     * calls to {@link DescriptivePredicate#test(Object)}.
     *
     * @param predicate   the actual predicate whose functional method will be called
     * @param description the description of this predicate
     */
    public DescriptivePredicate(Predicate<T> predicate, String description) {
        this.predicate = predicate;
        this.description = description;
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    /**
     * Returns the description, prepended by "Predicate description: ".
     *
     * @return the description
     */
    public String getDescription() {
        return "Predicate description: " + description;
    }
}
