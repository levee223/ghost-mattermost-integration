package app.entity.api.ghost;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableTags.class)
public interface Tags {

    @JsonProperty("tags")
    List<Tag> tags();

}
