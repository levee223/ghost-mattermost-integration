package app.entity.api.mattermost;

import app.util.ToStringBean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreatePostResponse extends ToStringBean {

    public String id;
    // public long createAt;
    // public long updateAt;
    // public long deleteAt;
    // public long editAt;
    // public String userId;
    // public String channelId;
    // public String rootId;
    // public String parentId;
    // public String originalId;
    // public String message;
    // public String type;
    // public Map<String, String> props;
    // public String hashtag;
    // public List<String> filenames;
    // public List<String> fileIds;
    // public String pending_postId;
    // public Map<String, String> metadata;

}
