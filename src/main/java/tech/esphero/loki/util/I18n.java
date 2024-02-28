package tech.esphero.loki.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;
import java.util.Objects;

@Slf4j
public class I18n {

    public static final String LOCALE_PARAM = "lang";

    public static final String LOCALE_DEFAULT = "pt_BR";

    public static Locale getLocale() {

        Locale locale = null;
        HttpServletRequest request = null;

        try {

            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            if (request != null) {

                String localeHeader = request.getHeader(LOCALE_PARAM);
                String localeParam = request.getParameter(LOCALE_PARAM);

                if (Objects.nonNull(localeHeader)) {
                    locale = StringUtils.parseLocaleString(localeHeader);
                } else if (Objects.nonNull(localeParam)) {
                    locale = StringUtils.parseLocaleString(localeParam);
                }

            }

        } catch (Exception e) {
            log.trace("Problem to read servlet request attributes to capture locate. Setting pt_BR as locate default.", e);
        }

        if(locale == null) {
            locale = StringUtils.parseLocaleString(LOCALE_DEFAULT);
        }

        return locale;

    }

}
