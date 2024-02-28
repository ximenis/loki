package tech.esphero.loki.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ignoreUnknownFields = true)
@Getter
@Setter
public class Properties {

    private final Loki loki = new Loki();

    @Getter
    @Setter
    public static class Loki {
        @Getter
        @Setter
        private String errorType;

        private final Cache cache = new Cache();

        private final Redis redis = new Redis();

        @Getter
        @Setter
        public static class Redis{

            private String host;
            private int port;
        }

        @Getter
        @Setter
        public static class Cache{

            private long ttl;

        }
    }

}
