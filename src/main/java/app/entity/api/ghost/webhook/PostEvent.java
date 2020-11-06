package app.entity.api.ghost.webhook;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostEvent {

    @JsonSetter(nulls = Nulls.FAIL)
    public Post post;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Post {
        @JsonSetter(nulls = Nulls.FAIL)
        public Current current;

        public Previous previous;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }

        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Current {
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

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }

            @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
            public static class Author {
                @JsonSetter(nulls = Nulls.FAIL)
                public String id;

                @JsonSetter(nulls = Nulls.FAIL)
                public String name;

                public String slug;
                public String email;
                public String url;

                @Override
                public String toString() {
                    return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
                }
            }

            @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
            public static class Tag {
                @JsonSetter(nulls = Nulls.FAIL)
                public String id;
                @JsonSetter(nulls = Nulls.FAIL)
                public String name;

                public String slug;
                public String url;

                @Override
                public String toString() {
                    return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
                }
            }
        }

        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Previous {
            public String status;
            public String updatedAt;
            public String publishedAt;

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }
        }
    }

}
