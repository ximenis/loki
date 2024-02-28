package tech.esphero.loki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import tech.esphero.loki.config.Properties;

@EnableCaching
@EnableConfigurationProperties({Properties.class})
@SpringBootApplication
public class LokiApp {

	public static void main(String[] args) {
		SpringApplication.run(LokiApp.class, args);
	}

}
