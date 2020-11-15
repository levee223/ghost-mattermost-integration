package app.entity.db;

import app.entity.form.NotificationPreferencesForm;
import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.Collections;
import java.util.List;

public class UserPreferences extends ToStringBean {

    public String id;
    public String value;

    public static class Value extends ToStringBean {
        public boolean all;

        @JsonSetter(nulls = Nulls.AS_EMPTY)
        public List<String> tags = Collections.emptyList();

        @JsonSetter(nulls = Nulls.AS_EMPTY)
        public List<String> authors = Collections.emptyList();

        public Value() {}

        public Value(NotificationPreferencesForm form) {
            all = form.isAll();
            tags = form.getTags();
            authors = form.getAuthors();
        }
    }

}
