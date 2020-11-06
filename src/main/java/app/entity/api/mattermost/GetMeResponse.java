package app.entity.api.mattermost;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetMeResponse {

    @JsonSetter(nulls = Nulls.FAIL)
    public String id;
    // public long createAt;
    // public long updateAt;
    // public long deleteAt;
    // public String username;
    // public String authData;
    // public String authService;
    // public String email;
    // public String nickname;
    // public String firstName;
    // public String lastName;
    // public String position;
    // public String roles;
    // public boolean allowMarketing;
    // public Map<String, String> notifyProps;
    // public long lastPasswordUpdate;
    // public String locale;
    // public Timezone timezone;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Timezone {
        public String automaticTimezone;
        public String manualTimezone;
        public boolean useAutomaticTimezone;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }
    }

}
