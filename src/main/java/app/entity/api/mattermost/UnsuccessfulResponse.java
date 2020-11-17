package app.entity.api.mattermost;

import app.util.ToStringBean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UnsuccessfulResponse extends ToStringBean {

    public String statusCode;
    public String id;
    public String message;
    public String requestId;

}
