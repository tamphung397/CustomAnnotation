package org.example.annotation;

import org.example.bean.Staff;
import org.example.exception.NotInLengthException;
import org.example.exception.NotInRangeException;
import org.example.exception.NotNullException;

import java.lang.reflect.Field;

public class Validator {
    public void validate(Staff staff) throws NoSuchFieldException, IllegalAccessException, NotInLengthException, NotInRangeException, NotNullException {
        Field[] field = staff.getClass().getDeclaredFields();
        for (Field f : field) {
            if (f.isAnnotationPresent(TamLength.class)) {
                TamLength annotation = staff.getClass().getDeclaredField(f.getName()).getAnnotation(TamLength.class);
                f.setAccessible(true);
                Object value = f.get(staff);
                if (!isValidLength((String) value, annotation.min(), annotation.max())) {
                    throw new NotInLengthException("Invalid length, please input between " + annotation.min() + " and " + annotation.max());
                }
            }

            if (f.isAnnotationPresent(TamRange.class)) {
                TamRange annotation = staff.getClass().getDeclaredField(f.getName()).getAnnotation(TamRange.class);
                f.setAccessible(true);
                Object value = f.get(staff);
                if (!isValidRange((int) value, annotation.min(), annotation.max())) {
                    throw new NotInRangeException("Invalid range, please input between " + annotation.min() + " and " + annotation.max());
                }
            }

            if (f.isAnnotationPresent(TamNotNull.class)) {
                f.setAccessible(true);
                Object value = f.get(staff);
                if (!isValidNotNull(value)) {
                    throw new NotNullException("Value cannot be null");
                }
            }

        }
    }

    public boolean isValidLength(String value, int min, int max) {
        int length = value.length();
        return length >= min && length <= max;
    }

    public boolean isValidRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isValidNotNull(Object value) {
        return value != null;
    }
}
