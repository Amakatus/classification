package app.utils;

import app.exceptions.FieldNotNumberException;
import app.models.datas.data.AbstractData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface ClassUtils {
    /**
     * Get all usable fields for an object.
     *
     * @param object
     * @return Array of fields
     */
    static Field[] getFields(Object object) {
        return object.getClass().getDeclaredFields();
    }

    /**
     * Get all usable methods for an object
     *
     * @param object
     * @return Array of methods
     */
    static Method[] getMethods(Object object) {
        return object.getClass().getDeclaredMethods();
    }

    /**
     * Return all the fields that are numbers from the given object
     *
     * @param object
     * @return
     */
    static List<Field> getNumberFields(Object object) {
        Field[] fields = getFields(object);
        ArrayList<Field> res = new ArrayList<>();
        Class<?> type;
        for (Field field : fields) {
            field.setAccessible(true);
            type = field.getType();
            if (type == double.class || type == Integer.class) {
                res.add(field);
            }
        }
        return res;
    }

    /**
     * Find a method by its name
     *
     * @param object
     * @param methodName
     * @return Method or null if no method has this name
     */
    static Method findMethodByName(Object object, String methodName) {
        Method[] methods = getMethods(object);
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getName().equalsIgnoreCase(methodName)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Find a field by its name
     *
     * @param object
     * @param fieldName
     * @return Field or null if no field has this name
     */
    static Field getFieldByName(Object object, String fieldName) {
        Field[] fields = getFields(object);
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }

    /**
     * Check if a given field name has its proper method to transform it to Double
     *
     * @param o
     * @param fieldName
     * @return true if it has, false otherwise.
     */
    static boolean hasMethodToDoubleForField(Object o, String fieldName) {
        return isToDoubleMethod(findMethodByName(o, fieldName + AbstractData.TO_DOUBLE));
    }

    /**
     * Check if the given method return a double
     *
     * @param fieldToDoubleMethod
     * @return true if it does, false otherwise.
     */
    static boolean isToDoubleMethod(Method fieldToDoubleMethod) {
        return fieldToDoubleMethod != null && fieldToDoubleMethod.getReturnType() == double.class;
    }

    /**
     * Makes use of the ToDouble proper method to compare two objects
     *
     * @param object
     * @param fieldName
     * @param other
     * @return A double representing the distance between the two objects.
     */
    // This method should not be there ?
    static double getValueFromFieldByMethod(Object object, String fieldName, Object other) {
        Method fieldToDoubleMethod = findMethodByName(object, fieldName + AbstractData.TO_DOUBLE);
        if (!isToDoubleMethod(fieldToDoubleMethod)) return Double.MAX_VALUE;
        try {
            fieldToDoubleMethod.setAccessible(true);
            return (double) fieldToDoubleMethod.invoke(object, other);
        } catch (Exception e) {
            LoggerUtils.exception(e);
        }
        return Double.MAX_VALUE;
    }

    /**
     * Try to access from a field to its value as a double.
     *
     * @param object
     * @param field
     * @return The value of this field as a double.
     * @throws FieldNotNumberException if the value of this field is not a double.
     */
    private static double getDoubleFromField(Object object, Field field) throws FieldNotNumberException {
        if (field.getType() != double.class && field.getType() != Integer.class)
            throw new FieldNotNumberException();
        try {
            field.setAccessible(true);
            return field.getDouble(object);
        } catch (Exception e) {
            LoggerUtils.exception(e);
        }
        // Should never be there.
        return Double.MIN_VALUE;
    }

    /**
     * Try to access from a field name to its value as a double.
     *
     * @param object
     * @param name
     * @return The value of this field as a double.
     * @throws FieldNotNumberException if the value of this field is not a double.
     */
    static double getDoubleFromField(Object object, String name) throws FieldNotNumberException {
        Field field = getFieldByName(object, name);
        field.setAccessible(true);
        return field == null ? Double.MIN_VALUE : getDoubleFromField(object, field);
    }

    /**
     * Return if the field can be used as a Category field.
     *
     * @param field
     * @return
     */
    static boolean canBeCategoryField(Field field) {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        return fieldType.isAssignableFrom(String.class) || fieldType.isEnum() || fieldType.isAssignableFrom(boolean.class);
    }

    /**
     * Return the value of a fieldName for a given object as an Object.
     *
     * @param object
     * @param fieldName
     * @return An Object representing the value of the fieldName
     */
    static Object getValueObjectFromField(Object object, String fieldName) {
        Field field = getFieldByName(object, fieldName);
        if (field == null) return null;
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            LoggerUtils.exception(e);
        }
        // Should never be there.
        return null;
    }
}
