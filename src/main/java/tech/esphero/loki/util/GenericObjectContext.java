package tech.esphero.loki.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import tech.esphero.loki.exceptions.ExceptionEnum;
import tech.esphero.loki.exceptions.ExceptionGeneric;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class GenericObjectContext {

    public void copyNonNullProperties(Object source, Object destination) {

        BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));

    }

    private String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] myArray = new String[emptyNames.size()];
        emptyNames.toArray(myArray);
        return myArray;

    }

    public void checkThrow(boolean expression, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        ExceptionEnum.checkThrow(expression, exceptionEnum);

    }

    public void checkThrow(boolean expression, ExceptionEnum exceptionEnum, String... args) throws ExceptionGeneric {

        ExceptionEnum.checkThrow(expression, exceptionEnum, args);

    }

    public void checkThrowIsBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        ExceptionEnum.checkThrowIsBlank(object, exceptionEnum);

    }

    public void checkThrowNotBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        ExceptionEnum.checkThrowNotBlank(object, exceptionEnum);

    }

}
