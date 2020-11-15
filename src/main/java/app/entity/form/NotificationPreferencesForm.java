package app.entity.form;

import app.entity.db.UserPreferences;
import app.util.ToStringBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NotificationPreferencesForm extends ToStringBean {

    private boolean all;
    private List<String> tags = Collections.emptyList();
    private List<String> authors = Collections.emptyList();

    public NotificationPreferencesForm() {}

    public NotificationPreferencesForm(Optional<UserPreferences.Value> userPreferences) {
        userPreferences.ifPresent(value -> {
            all = value.all;
            tags = value.tags;
            authors = value.authors;
        });
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

}
