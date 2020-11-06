package app.entity.api.ghost.content;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tags {

    @JsonSetter(contentNulls = Nulls.FAIL)
    public List<Tag> tags;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Tag {
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

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }

        public static class Count {
            public int posts;

            @Override
            public String toString() {
                return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
            }
        }
    }

}
