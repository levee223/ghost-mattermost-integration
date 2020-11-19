package app.entity.api.ghost.content;

import app.util.ToStringBean;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.Collections;
import java.util.List;

public class Errors extends ToStringBean {

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    public List<Error> errors= Collections.emptyList();

    public static class Error extends ToStringBean {
        public String message;
        public String errorType;
    }

}
