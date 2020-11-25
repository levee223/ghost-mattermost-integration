package app.service;

import app.connectivity.web.ghost.GhostContentApiClient;
import app.data.connectivity.web.ghost.content.response.AuthorsResponse;
import app.data.connectivity.web.ghost.content.response.TagsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GhostApiService {

    @Autowired
    GhostContentApiClient apiClient;

    public TagsResponse getTags() {
        return apiClient
                .getTags(Collections.singletonList("count.posts"), null, null, "all", null, "count.posts%20desc")
                .getBody();
    }

    public AuthorsResponse getAuthors() {
        return apiClient
                .getAuthors(Collections.singletonList("count.posts"), null, null, "all", null, "count.posts%20desc")
                .getBody();
    }

}
