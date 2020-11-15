package app.entity.api.ghost.content;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tags extends ToStringBean {

    @JsonSetter(contentNulls = Nulls.FAIL)
    public List<Tag> tags;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Tag extends ToStringBean {
        public String slug;

        @JsonSetter(nulls = Nulls.FAIL)
        public String id;

        @JsonSetter(nulls = Nulls.FAIL)
        public String name;

        public String description;
        public String featureImage;
        public String visibility;
        public String metaTitle;
        public String metaDescription;
        public String ogImage;
        public String ogTitle;
        public String ogDescription;
        public String twitterImage;
        public String twitterTitle;
        public String twitterDescription;
        public String codeinjectionHead;
        public String codeinjectionFoot;
        public String canonicalUrl;
        public String accentColor;
        public String url;

        @JsonSetter(nulls = Nulls.FAIL)
        public Count count;

        public static class Count extends ToStringBean {
            public int posts;
        }
    }

}
