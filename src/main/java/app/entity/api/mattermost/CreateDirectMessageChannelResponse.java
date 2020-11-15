package app.entity.api.mattermost;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateDirectMessageChannelResponse extends ToStringBean {

    @JsonSetter(nulls = Nulls.FAIL)
    public String id;
    // public long createAt;
    // public long updateAt;
    // public long deleteAt;
    // public String teamId;
    // public String type;
    // public String displayName;
    // public String name;
    // public String header;
    // public String purpose;
    // public int last_postAt;
    // public int total_msgCount;
    // public long extra_updateAt;
    // public String creatorId;

}
