package app.entity.api.mattermost;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreatePostResponse {

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
