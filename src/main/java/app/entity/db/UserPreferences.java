package app.entity.db;

import app.entity.form.NotificationPreferencesForm;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;

public class UserPreferences {

    public String id;
    public String value;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public static class Value {
        public boolean all;

        @JsonSetter(nulls = Nulls.AS_EMPTY)
        public List<String> tags = Collections.emptyList();

        @JsonSetter(nulls = Nulls.AS_EMPTY)
        public List<String> authors = Collections.emptyList();

        public Value() {
        }

        public Value(NotificationPreferencesForm form) {
            all = form.isAll();
            tags = form.getTags();
            authors = form.getAuthors();
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }
    }

}
