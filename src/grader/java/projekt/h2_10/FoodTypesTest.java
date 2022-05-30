package projekt.h2_10;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.food.Extras;
import projekt.food.FoodType;
import projekt.food.FoodTypes;
import projekt.food.IceCream;
import projekt.food.Pasta;
import projekt.food.Pizza;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("projekt")
public class FoodTypesTest {

    @Test
    public void pizzaTest() {
        final FoodType<Pizza, Pizza.Config> pizza = FoodTypes.PIZZA;
        assertEquals("Pizza", pizza.getName(), "FoodTypes.PIZZA has incorrect name");
        // reference equality
        assertTrue(pizza.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_HAM),
            "FoodTypes.PIZZA is missing compatible extra Extras.EXTRA_HAM");
        assertTrue(pizza.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_OLIVES),
            "FoodTypes.PIZZA is missing compatible extra Extras.EXTRA_OLIVES");
        assertTrue(pizza.getCompatibleExtras().stream().anyMatch(it -> it == Extras.SPICY_SAUCE),
            "FoodTypes.PIZZA is missing compatible extra Extras.SPICY_SAUCE");
        assertTrue(pizza.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_SAUCE),
            "FoodTypes.PIZZA is missing compatible extra Extras.EXTRA_SAUCE");
        assertTrue(pizza.getCompatibleExtras().stream().anyMatch(it -> it == Extras.NO_SAUCE),
            "FoodTypes.PIZZA is missing compatible extra Extras.NO_SAUCE");
    }

    @Test
    public void pastaTest() {
        final FoodType<Pasta, Pasta.Config> pasta = FoodTypes.PASTA;
        assertEquals("Pasta", pasta.getName(), "FoodTypes.PASTA has incorrect name");
        // reference equality
        assertTrue(pasta.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_THICK),
            "FoodTypes.PASTA is missing compatible extra Extras.EXTRA_THICK");
        assertTrue(pasta.getCompatibleExtras().stream().anyMatch(it -> it == Extras.SPICY_SAUCE),
            "FoodTypes.PASTA is missing compatible extra Extras.SPICY_SAUCE");
        assertTrue(pasta.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_SAUCE),
            "FoodTypes.PASTA is missing compatible extra Extras.EXTRA_SAUCE");
        assertTrue(pasta.getCompatibleExtras().stream().anyMatch(it -> it == Extras.NO_SAUCE),
            "FoodTypes.PASTA is missing compatible extra Extras.NO_SAUCE");
    }

    @Test
    public void iceCreamTest() {
        final FoodType<IceCream, IceCream.Config> iceCream = FoodTypes.ICE_CREAM;
        assertEquals("Ice Cream", iceCream.getName());
        // reference equality
        assertTrue(iceCream.getCompatibleExtras().stream().anyMatch(it -> it == Extras.RAINBOW_SPRINKLES),
            "FoodTypes.ICE_CREAM is missing compatible extra Extras.RAINBOW_SPRINKLES");
        assertTrue(iceCream.getCompatibleExtras().stream().anyMatch(it -> it == Extras.EXTRA_SCOOP),
            "FoodTypes.ICE_CREAM is missing compatible extra Extras.EXTRA_SCOOP");
    }

    @Test
    public void allTest() {
        assertTrue(FoodTypes.ALL.containsKey(FoodTypes.PIZZA.getName()),
            "FoodTypes.ALL does not contain entry with key FoodTypes.PIZZA.getName()");
        assertTrue(FoodTypes.ALL.containsKey(FoodTypes.PASTA.getName()),
            "FoodTypes.ALL does not contain entry with key FoodTypes.PASTA.getName()");
        assertTrue(FoodTypes.ALL.containsKey(FoodTypes.ICE_CREAM.getName()),
            "FoodTypes.ALL does not contain entry with key FoodTypes.ICE_CREAM.getName()");
    }

    @Test
    public void pizzaVariantsTest() {
        final FoodType<Pizza, Pizza.Config> pizza = FoodTypes.PIZZA;
        assertTrue(pizza.getFoodVariants().stream().anyMatch(it -> it == Pizza.MARGHERITA),
            "FoodTypes.PIZZA is missing food variant Pizza.MARGHERITA");
        assertTrue(pizza.getFoodVariants().stream().anyMatch(it -> it == Pizza.HAWAII),
            "FoodTypes.PIZZA is missing food variant Pizza.HAWAII");
        assertTrue(pizza.getFoodVariants().stream().anyMatch(it -> it == Pizza.RUCOLA),
            "FoodTypes.PIZZA is missing food variant Pizza.RUCOLA");
        assertTrue(pizza.getFoodVariants().stream().anyMatch(it -> it == Pizza.BBQ),
            "FoodTypes.PIZZA is missing food variant Pizza.BBQ");
    }

    @Test
    public void pastaVariantsTest() {
        final FoodType<Pasta, Pasta.Config> pasta = FoodTypes.PASTA;
        assertTrue(pasta.getFoodVariants().stream().anyMatch(it -> it == Pasta.SPAGHETTI),
            "FoodTypes.PASTA is missing food variant Pasta.SPAGHETTI");
        assertTrue(pasta.getFoodVariants().stream().anyMatch(it -> it == Pasta.RIGATONI),
            "FoodTypes.PASTA is missing food variant Pasta.RIGATONI");
        assertTrue(pasta.getFoodVariants().stream().anyMatch(it -> it == Pasta.RAVIOLI),
            "FoodTypes.PASTA is missing food variant Pasta.RAVIOLI");
        assertTrue(pasta.getFoodVariants().stream().anyMatch(it -> it == Pasta.FUSILLI),
            "FoodTypes.PASTA is missing food variant Pasta.FUSILLI");
    }

    @Test
    public void iceCreamVariantsTest() {
        final FoodType<IceCream, IceCream.Config> iceCream = FoodTypes.ICE_CREAM;
        assertTrue(iceCream.getFoodVariants().stream().anyMatch(it -> it == IceCream.VANILLA),
            "FoodTypes.ICE_CREAM is missing food variant IceCream.VANILLA");
        assertTrue(iceCream.getFoodVariants().stream().anyMatch(it -> it == IceCream.STRAWBERRY),
            "FoodTypes.ICE_CREAM is missing food variant IceCream.STRAWBERRY");
        assertTrue(iceCream.getFoodVariants().stream().anyMatch(it -> it == IceCream.CHOCOLATE),
            "FoodTypes.ICE_CREAM is missing food variant IceCream.CHOCOLATE");
        assertTrue(iceCream.getFoodVariants().stream().anyMatch(it -> it == IceCream.STRACCIATELLA),
            "FoodTypes.ICE_CREAM is missing food variant IceCream.STRACCIATELLA");
    }
}
