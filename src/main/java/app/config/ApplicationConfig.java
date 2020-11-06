package app.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@ConstructorBinding
@Validated
public class ApplicationConfig {

    @NotNull
    private final Ghost ghost;

    @NotNull
    private final Mattermost mattermost;

    public ApplicationConfig(final Ghost ghost, final Mattermost mattermost) {
        this.ghost = ghost;
        this.mattermost = mattermost;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public Mattermost getMattermost() {
        return mattermost;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public static class Ghost {
        @NotNull
        private final Api api;

        @NotNull
        private final OutgoingWebhook outgoingWebhook;

        public Ghost(final Api api, final OutgoingWebhook outgoingWebhook) {
            this.api = api;
            this.outgoingWebhook = outgoingWebhook;
        }

        public Api getApi() {
            return api;
        }

        public OutgoingWebhook getOutgoingWebhook() {
            return outgoingWebhook;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }

        public static class Api {
            @NotEmpty
            private final String url;

            @NotEmpty
            private final String key;

            public Api(final String url, final String key) {
                this.url = url;
                this.key = key;
            }

            public String getUrl() {
                return url;
            }

            public String getKey() {
                return key;
            }

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }
        }

        public static class OutgoingWebhook {
            @NotEmpty
            private final String authorizedkey;

            public OutgoingWebhook(final String authorizedkey) {
                this.authorizedkey = authorizedkey;
            }

            public String getAuthorizedkey() {
                return authorizedkey;
            }

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }
        }
    }

    public static class Mattermost {
        @NotNull
        private final Api api;

        public Mattermost(final Api api) {
            this.api = api;
        }

        public Api getApi() {
            return api;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }

        public static class Api {
            @NotEmpty
            private final String url;

            @NotEmpty
            private final String accessToken;

            public Api(final String url, final String accessToken) {
                this.url = url;
                this.accessToken = accessToken;
            }

            public String getUrl() {
                return url;
            }

            public String getAccessToken() {
                return accessToken;
            }

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }
        }
    }

}
