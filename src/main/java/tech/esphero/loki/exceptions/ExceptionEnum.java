package tech.esphero.loki.exceptions;

import br.com.twsoftware.alfred.object.Objeto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import tech.esphero.loki.config.Properties;
import tech.esphero.loki.enums.ErrorCode;
import tech.esphero.loki.util.BeanManager;
import tech.esphero.loki.util.I18n;

import static tech.esphero.loki.enums.ErrorCode.*;

import java.util.Objects;
import java.util.ResourceBundle;

import static org.springframework.http.HttpStatus.*;

@Slf4j
public enum ExceptionEnum {

    /**
     * Global Messages
     */
    GLOBAL_ERROR(BAD_REQUEST, GlobalException.class, E00001),

    /**
     * Tenant Messages
     */
    TENANT_NOT_FOUND(BAD_REQUEST, TenantNotFoundException.class, E10000),

    /**
     * ApiKey Messages
     */
    UNAUTHORIZED_API_KEY(BAD_REQUEST, UnauthorizedException.class, E20000),

    ;

    @Getter
    private HttpStatus status;

    @Getter
    private Class<? extends RuntimeException> klass;

    @Getter
    private ErrorCode errorCode;

    private Properties properties;

    ExceptionEnum(HttpStatus httpStatus, Class<? extends RuntimeException> exceptionKlass, ErrorCode errorCode) {

        this.status = httpStatus;
        this.klass = exceptionKlass;
        this.errorCode = errorCode;

    }

    public Properties getProperties() {

        if (properties == null) {
            properties = (Properties) BeanManager.obterBean(Properties.class);
        }

        return properties;
    }

    public String getKeyLowerCase() {
        return this.name().toLowerCase();
    }

    public String getMessage(String... args) {

        ResourceBundle bundle = ResourceBundle.getBundle("messages", I18n.getLocale());
        String baseString = bundle.getString(getKeyLowerCase());
        return replaceVariables(baseString, args);

    }

    public String replaceVariables(String baseString, String... replaceTokens) {

        if (Objeto.isBlank(replaceTokens)) return baseString;

        String returnMessage = baseString;
        for (int i = 0; i < replaceTokens.length; i++) {
            returnMessage = returnMessage.replace(String.format("{%d}", i), replaceTokens[i]);
        }

        return returnMessage;

    }

    public static void checkThrow(boolean expression, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        if (expression) {
            exceptionEnum.launch();
        }

    }

    public static void checkThrow(boolean expression, ExceptionEnum exceptionEnum, String... args) throws ExceptionGeneric {

        if (expression) {
            exceptionEnum.launch(args);
        }

    }

    public static void checkThrowIsBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        checkThrow(Objeto.isBlank(object), exceptionEnum);

    }

    public static void checkThrowNotBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {

        checkThrow(Objeto.notBlank(object), exceptionEnum);

    }

    public void launch(String... args) {

        throw getException(args);

    }

    public ExceptionGeneric getException(String... args) {

        String msg = getMessage(args);
        ExceptionGeneric eg = null;

        try {

            eg = (ExceptionGeneric) klass.getConstructor(String.class, String.class, HttpStatus.class, ErrorCode.class).newInstance(getProperties().getLoki().getErrorType(), msg, status, errorCode);

        } catch (Exception e) {
            log.error("Error trying instance internal exception using reflection.", e);
        }

        if (Objects.isNull(eg)) {
            eg = new ExceptionGeneric(getProperties().getLoki().getErrorType(), msg, INTERNAL_SERVER_ERROR, E00001);
        }

        return eg;

    }

}
