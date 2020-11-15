package app.entity.api.ghost.content;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Authors extends ToStringBean {

    @JsonSetter(contentNulls = Nulls.FAIL)
    public List<Author> authors;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Author extends ToStringBean {
        public String slug;

        @JsonSetter(nulls = Nulls.FAIL)
        public String id;

        @JsonSetter(nulls = Nulls.FAIL)
        public String name;

        public String profileImage;
        public String coverImage;
        public String bio;
        public String website;
        public String location;
        public String facebook;
        public String twitter;
        public String metaTitle;
        public String metaDescription;
        public String url;

        @JsonSetter(nulls = Nulls.FAIL)
        public Count count;

        public static class Count extends ToStringBean {
            public int posts;
        }
    }

}
