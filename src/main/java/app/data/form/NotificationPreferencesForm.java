package app.data.form;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface NotificationPreferencesForm {

    boolean all();

    List<String> tags();

    List<String> authors();

}
