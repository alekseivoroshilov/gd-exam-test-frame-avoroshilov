package helpers;

import io.appium.java_client.AppiumDriver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public class ReflectionUtils {
    private static final String PAGE_TO_INITIALIZE = "pageWhereInitialize";
    private static final String CLASS_CONTAINER = "classFieldsContainer";
    private static final String OPTION = "option";

    public static <R, T extends R> void invokeFields(String methodName, T pageWhereInitialize, Class<R> classFieldsContainer,
                                                     Predicate<String> option, AppiumDriver currentDriver) {
        Consumer<? super Field> invokeField = field -> {
                try {
                    Field parentField = classFieldsContainer.getDeclaredField(field.getName());

                    Class<?> fieldClazz = field.getType();
                    Method getInstance = fieldClazz.getMethod(methodName);

                    parentField.setAccessible(true);
                    parentField.set(pageWhereInitialize, getInstance.invoke(fieldClazz));
                } catch (ReflectiveOperationException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
        };

        /*getTestHandler(currentDriver).setPageToClearFields(PAGE_TO_INITIALIZE, pageWhereInitialize)
                .setPageToClearFields(CLASS_CONTAINER, classFieldsContainer)
                .setPageToClearFields(OPTION, option);
        */
        stream(classFieldsContainer.getDeclaredFields())
                .parallel()
                .filter(f -> option.test(f.getType()
                        .getSimpleName()))
                .forEach(invokeField);
    }
}
