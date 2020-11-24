package app.config;

import app.util.ConfigurationPropertiesValueStyle;

import org.immutables.value.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Value.Immutable
@Value.Enclosing
@ConfigurationPropertiesValueStyle
public abstract class ApplicationConfig {

    @ConstructorBinding
    public ApplicationConfig() {}

    public abstract ImmutableApplicationConfig.Ghost ghost();

    public abstract ImmutableApplicationConfig.Mattermost mattermost();

    @Value.Immutable
    public static abstract class Ghost {
        @ConstructorBinding
        public Ghost() {}

        public abstract ImmutableApplicationConfig.GhostApi api();

        public abstract ImmutableApplicationConfig.GhostOutgoingWebhook outgoingWebhook();
    }

    @Value.Immutable
    public static abstract class GhostApi {
        @ConstructorBinding
        public GhostApi() {}

        public abstract String url();

        public abstract String key();
    }

    @Value.Immutable
    public static abstract class GhostOutgoingWebhook {
        @ConstructorBinding
        public GhostOutgoingWebhook() {}

        public abstract String authorizedkey();
    }

    @Value.Immutable
    public static abstract class Mattermost {
        @ConstructorBinding
        public Mattermost() {}

        public abstract ImmutableApplicationConfig.MattermostApi api();
    }

    @Value.Immutable
    public static abstract class MattermostApi {
        @ConstructorBinding
        public MattermostApi() {}

        public abstract String url();

        public abstract String accessToken();
    }

}
