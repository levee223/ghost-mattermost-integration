package app.entity.db.ghost;

import org.immutables.value.Value;

import java.sql.Timestamp;

@Value.Immutable
public interface Post {

    String id();

    String title();

    String slug();

    String plaintext();

    Timestamp updatedAt();

}
