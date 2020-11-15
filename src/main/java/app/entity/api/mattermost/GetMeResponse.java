package app.entity.api.mattermost;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetMeResponse extends ToStringBean {

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

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Timezone extends ToStringBean {
        public String automaticTimezone;
        public String manualTimezone;
        public boolean useAutomaticTimezone;
    }

}
