package app.entity.api.ghost.webhook;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Collections;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostEvent extends ToStringBean {

    @JsonSetter(nulls = Nulls.FAIL)
    public Post post;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Post extends ToStringBean {
        @JsonSetter(nulls = Nulls.FAIL)
        public Current current;

        public Previous previous;

        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Current extends ToStringBean {
            public String id;

            @JsonSetter(nulls = Nulls.FAIL)
            public String title;

            public String slug;
            public String plaintext;
            public String publishedAt;

            @JsonSetter(nulls = Nulls.AS_EMPTY)
            public List<Author> authors = Collections.emptyList();

            @JsonSetter(nulls = Nulls.AS_EMPTY)
            public List<Tag> tags = Collections.emptyList();

            @JsonSetter(nulls = Nulls.FAIL)
            public String url;

            public String excerpt;
            public int readingTime;

            @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
            public static class Author extends ToStringBean {
                @JsonSetter(nulls = Nulls.FAIL)
                public String id;

                @JsonSetter(nulls = Nulls.FAIL)
                public String name;

                public String slug;
                public String email;
                public String url;
            }

            @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
            public static class Tag extends ToStringBean {
                @JsonSetter(nulls = Nulls.FAIL)
                public String id;
                @JsonSetter(nulls = Nulls.FAIL)
                public String name;

                public String slug;
                public String url;
            }
        }

        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Previous extends ToStringBean {
            public String status;
            public String updatedAt;
            public String publishedAt;
        }
    }

}
