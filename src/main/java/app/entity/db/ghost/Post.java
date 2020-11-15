package app.entity.db.ghost;

import app.util.ToStringBean;

import java.sql.Timestamp;

public class Post extends ToStringBean {

    public String id;
    public String title;
    public String slug;
    public String plaintext;
    public Timestamp updatedAt;

}
