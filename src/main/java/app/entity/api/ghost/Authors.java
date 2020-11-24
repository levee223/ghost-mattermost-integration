package app.entity.api.ghost;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableAuthors.class)
public interface Authors {

    @JsonProperty("authors")
    List<Author> authors();

}
