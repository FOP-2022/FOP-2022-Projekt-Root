package projekt.h1_2;

import kotlin.Pair;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

class LocationsProvider implements ArgumentsProvider {

    private static final List<Pair<Object[], Object[]>> LOCATIONS = List.of();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return LOCATIONS.stream()
            .map(locationPair -> Arguments.of(new Object(), new Object())); // TODO: create new Location objects
    }
}
