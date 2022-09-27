package ovh.rootkovskiy.TimaCore.Utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {

    public static <T> T getFieldContent(final Object object, final String field) throws NullPointerException {
        return getFieldContent(object.getClass(), object, field);
    }

    public static <T> T getFieldContent(Class<?> clazz, final Object object, final String field) throws NullPointerException {
        final String originalName = object.getClass().getSimpleName();

        do {
            for (final Field f : clazz.getDeclaredFields()) {
                if (f.getName().equals(field)) {
                    return (T) getFieldContent(object, f);
                }
            }
        } while (!(clazz = clazz.getSuperclass()).isAssignableFrom(Object.class));

        throw new NullPointerException("No such field " + field + " in " + originalName + " or its superclasses");
    }

    public static Object getFieldContent(final Object object, final Field field) throws NullPointerException {
        field.setAccessible(true);

        try {
            return field.get(object);
        } catch (IllegalAccessException ex) {
            throw new NullPointerException("Could not find field " + field.getName() + " in class " + (object != null ? object : field).getClass().getSimpleName());
        }
    }

    public static Object getHandle(final Player player) {
        try {
            return player.getClass().getMethod("getHandle").invoke(player);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new RuntimeException("Error in getting handle of a player");
        }
    }

}
