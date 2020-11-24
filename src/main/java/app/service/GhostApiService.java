package app.service;

import app.connectivity.web.GhostContentApiClient;
import app.data.api.ghost.AuthorsResponse;
import app.data.api.ghost.TagsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GhostApiService {

    @Autowired
    GhostContentApiClient apiClient;

    public TagsResponse getTags() {
        return apiClient.getTags(Arrays.asList("count.posts"), null, null, "all", null, "count.posts%20desc").getBody();
    }

    public AuthorsResponse getAuthors() {
        return apiClient.getAuthors(Arrays.asList("count.posts"), null, null, "all", null, "count.posts%20desc")
                .getBody();
    }

}
