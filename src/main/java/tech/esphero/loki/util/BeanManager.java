package tech.esphero.loki.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope("singleton")
public class BeanManager implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext actx) throws BeansException {

        BeanManager.inicializar(actx);
    }

    private static void inicializar(ApplicationContext actx) {

        BeanManager.applicationContext = actx;
    }

    public static Object obterBean(String nomeBean) {

        Object bean = null;
        try {

            bean = BeanManager.applicationContext.getBean(nomeBean);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return bean;
    }

    public static Object obterBean(Class<?> klass) {

        Object bean = null;
        try {

            bean = BeanManager.applicationContext.getBean(klass);

        } catch (Exception be) {
            log.error(be.getMessage(), be);
        }

        return bean;
    }

}
