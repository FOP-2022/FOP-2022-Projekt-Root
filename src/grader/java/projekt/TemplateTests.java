package projekt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import static projekt.utils.TutorAssertions.*;

/**
 * Sanity checks for {@link projekt.food.Extra}, {@link projekt.food.Food}, {@link projekt.food.FoodType} and
 * their subclasses / interfaces.
 */
@SuppressWarnings("NewClassNamingConvention")
public final class TemplateTests {

    private TemplateTests() {}

    /**
     * Sanity checks for {@link projekt.food.Extra}.
     */
    @DisplayName("projekt.food.Extra sanity check")
    public static class ExtraSanityCheck extends TestClass {

        public static final String METHOD_GET_NAME_SIGNATURE = "getName()";
        public static final String METHOD_GET_PRIORITY_SIGNATURE = "getPriority()";
        public static final String METHOD_APPLY_SIGNATURE = "apply(C)";

        /**
         * Initializes a new {@link ExtraSanityCheck} object.
         */
        public ExtraSanityCheck() {
            super(
                "projekt.food.Extra",
                Collections.emptyMap(),
                Map.of(
                    METHOD_GET_NAME_SIGNATURE, predicateFromSignature(METHOD_GET_NAME_SIGNATURE),
                    METHOD_GET_PRIORITY_SIGNATURE, predicateFromSignature(METHOD_GET_PRIORITY_SIGNATURE),
                    METHOD_APPLY_SIGNATURE, predicateFromSignature(METHOD_APPLY_SIGNATURE)
                )
            );
        }

        @Test
        @DisplayName("Interface and methods")
        public void testDefinition() {
            assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
            assertClassTypeParameters(clazz, new String[] {"C"}, new String[][] {{"projekt.food.Food$Config"}});

            assertClassHasMethod(clazz, METHOD_GET_NAME_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_NAME_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(String.class));
            assertClassHasMethod(clazz, METHOD_GET_PRIORITY_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_PRIORITY_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(int.class));
            assertClassHasMethod(clazz, METHOD_APPLY_SIGNATURE);
            assertMethod(getMethod(METHOD_APPLY_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(void.class));
        }
    }

    /**
     * Sanity checks for {@link projekt.food.Food}.
     */
    @DisplayName("projekt.food.Food sanity check")
    public static class FoodSanityCheck extends TestClass {

        public static final String METHOD_GET_PRICE_SIGNATURE = "getPrice()";
        public static final String METHOD_GET_WEIGHT_SIGNATURE = "getWeight()";
        public static final String METHOD_GET_FOOD_VARIANT_SIGNATURE = "getFoodVariant()";
        public static final String METHOD_GET_EXTRAS_SIGNATURE = "getExtras()";

        /**
         * Initializes a new {@link FoodSanityCheck} object.
         */
        public FoodSanityCheck() {
            super(
                "projekt.food.Food",
                Collections.emptyMap(),
                Map.of(
                    METHOD_GET_PRICE_SIGNATURE, predicateFromSignature(METHOD_GET_PRICE_SIGNATURE),
                    METHOD_GET_WEIGHT_SIGNATURE, predicateFromSignature(METHOD_GET_WEIGHT_SIGNATURE),
                    METHOD_GET_FOOD_VARIANT_SIGNATURE, predicateFromSignature(METHOD_GET_FOOD_VARIANT_SIGNATURE),
                    METHOD_GET_EXTRAS_SIGNATURE, predicateFromSignature(METHOD_GET_EXTRAS_SIGNATURE)
                )
            );
        }

        @Test
        @DisplayName("Interface and methods")
        public void testDefinition() {
            assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
            assertClassNotGeneric(clazz);

            assertClassHasMethod(clazz, METHOD_GET_PRICE_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_PRICE_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(BigDecimal.class));
            assertClassHasMethod(clazz, METHOD_GET_WEIGHT_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_WEIGHT_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(double.class));
            assertClassHasMethod(clazz, METHOD_GET_FOOD_VARIANT_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_FOOD_VARIANT_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType("%s<?, ?>".formatted(new VariantSanityCheck().getTestedClass().getName())));
            assertClassHasMethod(clazz, METHOD_GET_EXTRAS_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_EXTRAS_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType("%s<? extends %s<?>>"
                    .formatted(List.class.getName(), new ExtraSanityCheck().getTestedClass().getName())));
        }

        /**
         * Sanity checks for {@link projekt.food.Food.Config}.
         */
        @DisplayName("projekt.food.Food$Config sanity check")
        public static class ConfigSanityCheck extends TestClass {

            public static final String METHOD_PRICE_SIGNATURE = "price(%s<%s>)"
                .formatted(UnaryOperator.class.getName(), BigDecimal.class.getName());
            public static final String METHOD_GET_PRICE_MUTATOR_SIGNATURE = "getPriceMutator()";
            public static final String METHOD_WEIGHT_SIGNATURE = "weight(%s)".formatted(DoubleUnaryOperator.class.getName());
            public static final String METHOD_GET_WEIGHT_MUTATOR_SIGNATURE = "getWeightMutator()";

            /**
             * Initializes a new {@link ConfigSanityCheck} object.
             */
            public ConfigSanityCheck() {
                super(
                    "projekt.food.Food$Config",
                    Collections.emptyMap(),
                    Map.of(
                        METHOD_PRICE_SIGNATURE, predicateFromSignature(METHOD_PRICE_SIGNATURE),
                        METHOD_GET_PRICE_MUTATOR_SIGNATURE, predicateFromSignature(METHOD_GET_PRICE_MUTATOR_SIGNATURE),
                        METHOD_WEIGHT_SIGNATURE, predicateFromSignature(METHOD_WEIGHT_SIGNATURE),
                        METHOD_GET_WEIGHT_MUTATOR_SIGNATURE, predicateFromSignature(METHOD_GET_WEIGHT_MUTATOR_SIGNATURE)
                    )
                );
            }

            @Test
            @DisplayName("Interface and methods")
            public void testDefinition() {
                assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
                assertClassNotGeneric(clazz);

                assertClassHasMethod(clazz, METHOD_PRICE_SIGNATURE);
                assertMethod(getMethod(METHOD_PRICE_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(void.class));
                assertClassHasMethod(clazz, METHOD_GET_PRICE_MUTATOR_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_PRICE_MUTATOR_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType("%s<%s>".formatted(UnaryOperator.class.getName(), BigDecimal.class.getName())));
                assertClassHasMethod(clazz, METHOD_WEIGHT_SIGNATURE);
                assertMethod(getMethod(METHOD_WEIGHT_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(void.class));
                assertClassHasMethod(clazz, METHOD_GET_WEIGHT_MUTATOR_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_WEIGHT_MUTATOR_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(DoubleUnaryOperator.class));
            }
        }

        /**
         * Sanity checks for {@link projekt.food.Food.Variant}.
         */
        public static class VariantSanityCheck extends TestClass {

            public static final String METHOD_GET_NAME_SIGNATURE = "getName()";
            public static final String METHOD_GET_FOOD_TYPE_SIGNATURE = "getFoodType()";
            public static final String METHOD_GET_BASE_PRICE_SIGNATURE = "getBasePrice()";
            public static final String METHOD_GET_BASE_WEIGHT_SIGNATURE = "getBaseWeight()";
            public static final String METHOD_CREATE_EMPTY_CONFIG = "createEmptyConfig()";
            public static final String METHOD_CREATE_SIGNATURE = "create(%s<? extends %s<? super C>>)"
                .formatted(List.class.getName(), new ExtraSanityCheck().getTestedClass().getName());

            /**
             * Initializes a new {@link VariantSanityCheck} object.
             */
            public VariantSanityCheck() {
                super(
                    "projekt.food.Food$Variant",
                    Collections.emptyMap(),
                    Map.of(
                        METHOD_GET_NAME_SIGNATURE, predicateFromSignature(METHOD_GET_NAME_SIGNATURE),
                        METHOD_GET_FOOD_TYPE_SIGNATURE, predicateFromSignature(METHOD_GET_FOOD_TYPE_SIGNATURE),
                        METHOD_GET_BASE_PRICE_SIGNATURE, predicateFromSignature(METHOD_GET_BASE_PRICE_SIGNATURE),
                        METHOD_GET_BASE_WEIGHT_SIGNATURE, predicateFromSignature(METHOD_GET_BASE_WEIGHT_SIGNATURE),
                        METHOD_CREATE_EMPTY_CONFIG, predicateFromSignature(METHOD_CREATE_EMPTY_CONFIG),
                        METHOD_CREATE_SIGNATURE, predicateFromSignature(METHOD_CREATE_SIGNATURE)
                    )
                );
            }

            @Test
            @DisplayName("Interface and methods")
            public void testDefinition() {
                assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.ABSTRACT);
                assertClassTypeParameters(clazz, new String[] {"F", "C"}, new String[][] {
                    {new FoodSanityCheck().getTestedClass().getName()},
                    {new ConfigSanityCheck().getTestedClass().getName()}
                });

                assertClassHasMethod(clazz, METHOD_GET_NAME_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_NAME_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(String.class));
                assertClassHasMethod(clazz, METHOD_GET_FOOD_TYPE_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_FOOD_TYPE_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType("%s<F, C>".formatted(new FoodTypeSanityCheck().getTestedClass().getName())));
                assertClassHasMethod(clazz, METHOD_GET_BASE_PRICE_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_BASE_PRICE_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(BigDecimal.class));
                assertClassHasMethod(clazz, METHOD_GET_BASE_WEIGHT_SIGNATURE);
                assertMethod(getMethod(METHOD_GET_BASE_WEIGHT_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType(double.class));
                assertClassHasMethod(clazz, METHOD_CREATE_EMPTY_CONFIG);
                assertMethod(getMethod(METHOD_CREATE_EMPTY_CONFIG), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType("C"));
                assertClassHasMethod(clazz, METHOD_CREATE_SIGNATURE);
                assertMethod(getMethod(METHOD_CREATE_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                    TypeUtils.hasType("F"));
            }
        }
    }

    /**
     * Sanity checks for {@link projekt.food.FoodType}.
     */
    @DisplayName("projekt.food.FoodType sanity check")
    public static class FoodTypeSanityCheck extends TestClass {

        public static final String METHOD_GET_NAME_SIGNATURE = "getName()";
        public static final String METHOD_GET_COMPATIBLE_EXTRAS_SIGNATURE = "getCompatibleExtras()";
        public static final String METHOD_ADD_FOOD_VARIANT_SIGNATURE = "addFoodVariant(%s<F, C>)"
            .formatted(new FoodSanityCheck.VariantSanityCheck().getTestedClass().getName());
        public static final String METHOD_GET_FOOD_VARIANTS = "getFoodVariants()";

        /**
         * Initializes a new {@link FoodTypeSanityCheck} object.
         */
        public FoodTypeSanityCheck() {
            super(
                "projekt.food.FoodType",
                Collections.emptyMap(),
                Map.of(
                    METHOD_GET_NAME_SIGNATURE, predicateFromSignature(METHOD_GET_NAME_SIGNATURE),
                    METHOD_GET_COMPATIBLE_EXTRAS_SIGNATURE, predicateFromSignature(METHOD_GET_COMPATIBLE_EXTRAS_SIGNATURE),
                    METHOD_ADD_FOOD_VARIANT_SIGNATURE, predicateFromSignature(METHOD_ADD_FOOD_VARIANT_SIGNATURE),
                    METHOD_GET_FOOD_VARIANTS, predicateFromSignature(METHOD_GET_FOOD_VARIANTS)
                )
            );
        }

        @Test
        @DisplayName("Interface and methods")
        public void testDefinition() {
            assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
            assertClassTypeParameters(clazz, new String[] {"F", "C"}, new String[][] {
                {new FoodSanityCheck().getTestedClass().getName()},
                {new FoodSanityCheck.ConfigSanityCheck().getTestedClass().getName()}
            });

            assertClassHasMethod(clazz, METHOD_GET_NAME_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_NAME_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(String.class));
            assertClassHasMethod(clazz, METHOD_GET_COMPATIBLE_EXTRAS_SIGNATURE);
            assertMethod(getMethod(METHOD_GET_COMPATIBLE_EXTRAS_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType("%s<? extends %s<? super C>>"
                    .formatted(List.class.getName(), new ExtraSanityCheck().getTestedClass().getName())));
            assertClassHasMethod(clazz, METHOD_ADD_FOOD_VARIANT_SIGNATURE);
            assertMethod(getMethod(METHOD_ADD_FOOD_VARIANT_SIGNATURE), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType(void.class));
            assertClassHasMethod(clazz, METHOD_GET_FOOD_VARIANTS);
            assertMethod(getMethod(METHOD_GET_FOOD_VARIANTS), Modifier.PUBLIC | Modifier.ABSTRACT,
                TypeUtils.hasType("%s<? extends %s<F, C>>"
                    .formatted(List.class.getName(), new FoodSanityCheck.VariantSanityCheck().getTestedClass().getName())));
        }
    }
}
