package app.service;

import app.config.ApplicationConfig;
import app.entity.api.ghost.webhook.PostEvent;
import app.entity.api.mattermost.CreateDirectMessageChannelResponse;
import app.entity.api.mattermost.CreatePostResponse;
import app.entity.api.mattermost.GetMeResponse;
import app.util.JsonMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class MattermostApiService {

    private static final Logger logger = LoggerFactory.getLogger(MattermostApiService.class);

    private static final MediaType REQUEST_CONTENT_TYPE = MediaType.get("application/json");

    @Autowired
    ApplicationConfig appConfig;

    @Autowired
    OkHttpClient httpClient;

    @Autowired
    JsonMapper jsonMapper;

    public Optional<Pair<List<String>, PostEvent>> notifyUpdates(final List<String> targetUsers,
            final PostEvent postEvent) {
        final String botUserId = getMe().id;

        List<String> failedUsers = null;
        for (final String targetUser : targetUsers) {
            String dmChannelId = null;
            String message = null;
            try {
                dmChannelId = createDirectMessageChannel(botUserId, targetUser).id;
                message = postEvent.post.current.url;
                createPost(dmChannelId, message);
            } catch (Exception ex) {
                logger.error("Error occurred in notify updates. dmChannelId=" + dmChannelId, ex);
                if (failedUsers == null) {
                    failedUsers = new ArrayList<>();
                }
                failedUsers.add(targetUser);
            }
        }
        return failedUsers != null ? Optional.of(new ImmutablePair<>(failedUsers, postEvent)) : Optional.empty();
    }

    GetMeResponse getMe() {
        return get("/users/me", GetMeResponse.class);
    }

    CreateDirectMessageChannelResponse createDirectMessageChannel(final String userId1, final String userId2) {
        final byte[] requestBodyContent = jsonMapper.writeValueAsBytes(new String[] {userId1, userId2});
        if (logger.isDebugEnabled()) {
            logger.debug("createDirectMessageChannel: {}", new String(requestBodyContent, StandardCharsets.UTF_8));
        }
        final RequestBody requestBody = RequestBody.create(REQUEST_CONTENT_TYPE, requestBodyContent);
        return post("/channels/direct", requestBody, CreateDirectMessageChannelResponse.class);
    }

    CreatePostResponse createPost(final String channelId, final String message) {
        final var postData = new HashMap<String, String>();
        postData.put("channel_id", channelId);
        postData.put("message", message);

        final byte[] requestBodyContent = jsonMapper.writeValueAsBytes(postData);
        if (logger.isDebugEnabled()) {
            logger.debug("createPost: {}", new String(requestBodyContent, StandardCharsets.UTF_8));
        }
        final RequestBody requestBody = RequestBody.create(REQUEST_CONTENT_TYPE, requestBodyContent);
        return post("/posts", requestBody, CreatePostResponse.class);
    }

    <T> T get(final String path, final Class<T> mappedClass) {
        final Request request = buildRequest(path, null);
        return call(request, mappedClass);
    }

    <T> T post(final String path, final RequestBody requestBody, final Class<T> mappedClass) {
        final Request request = buildRequest(path, builder -> {
            builder.post(requestBody);
        });
        return call(request, mappedClass);
    }

    Request buildRequest(final String path, final Consumer<Request.Builder> additionalBuilder) {
        final Request.Builder builder = new Request.Builder();
        builder.url(String.format("%s/api/v4%s", appConfig.getMattermost().getApi().getUrl(), path));
        builder.header("Authorization", "Bearer " + appConfig.getMattermost().getApi().getAccessToken());
        if (additionalBuilder != null) {
            additionalBuilder.accept(builder);
        }
        return builder.build();
    }

    <T> T call(final Request request, final Class<T> mappedClass) {
        try (final var response = httpClient.newCall(request).execute()) {
            if (logger.isDebugEnabled()) {
                logger.debug("{}: {} {}", response.request(), response.code(), response.message());
            }
            final T mappedData = jsonMapper.readValue(response.body().byteStream(), mappedClass);
            return mappedData;
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

}
