package app.controller.api.ghost;

import app.config.ApplicationConfig;
import app.entity.api.ghost.webhook.PostEvent;
import app.repository.db.UsersRepository;
import app.service.MattermostApiService;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping("/api/ghost/webhook")
public class GhostWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(GhostWebhookController.class);

    private static final String RESPONSE_OK = "OK";

    @Autowired
    ApplicationConfig appConfig;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    MattermostApiService mattermostApiService;

    ConcurrentLinkedQueue<Pair<List<String>, PostEvent>> retryQueue = new ConcurrentLinkedQueue<>();

    @PostMapping("/post.published")
    public String postPublished(@RequestBody final PostEvent postEvent) {
        logger.debug("Ghost post.published: {}", postEvent);

        List<String> targetUsers = null;
        try {
            targetUsers = usersRepository.getUserIds(preferences -> {
                return preferences.all
                        || postEvent.post.current.tags.stream().anyMatch(tag -> preferences.tags.contains(tag.id))
                        || postEvent.post.current.authors.stream()
                                .anyMatch(author -> preferences.authors.contains(author.id));
            });
            logger.debug("Ghost post.published targetUsers={}", targetUsers);

            mattermostApiService.notifyUpdates(targetUsers, postEvent).ifPresent(retryQueue::add);
        } catch (Exception ex) {
            logger.error("Error occurred. postEvent=" + postEvent, ex);
            retryQueue.add(new ImmutablePair<>(targetUsers, postEvent));
        }

        return RESPONSE_OK;
    }

    @ModelAttribute(name = "key", binding = false)
    void validateKey(@RequestParam final String key) {
        if (key == null || !appConfig.getGhost().getOutgoingWebhook().getAuthorizedkey().equals(key)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    ResponseEntity<String> handleHttpStatusException(final HttpStatusCodeException exception) {
        return new ResponseEntity<>(exception.getStatusCode());
    }

    @Scheduled(initialDelay = 10 * 60 * 1000, fixedDelay = 10 * 60 * 1000)
    void retryNotifyUpdates() {
        for (;;) {
            final Pair<List<String>, PostEvent> retryEvent = retryQueue.poll();
            if (retryEvent == null) {
                break;
            }
            if (retryEvent.getLeft() == null) {
                postPublished(retryEvent.getRight());
            } else {
                mattermostApiService.notifyUpdates(retryEvent.getLeft(), retryEvent.getRight());
            }
        }
    }

}
