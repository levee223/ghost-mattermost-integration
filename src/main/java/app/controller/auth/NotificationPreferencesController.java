package app.controller.auth;

import app.entity.api.ghost.content.Authors;
import app.entity.api.ghost.content.Tags;
import app.entity.db.UserPreferences;
import app.entity.form.NotificationPreferencesForm;
import app.repository.db.UsersRepository;
import app.service.GhostApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth/notification-preferences")
public class NotificationPreferencesController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationPreferencesController.class);

    @Autowired
    GhostApiService ghostApiService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public String get(@AuthenticationPrincipal final OAuth2User principal, final Model model) {
        final var tags = ghostApiService.getTags();
        final var authors = ghostApiService.getAuthors();

        final Optional<UserPreferences.Value> userPrefeerences = usersRepository
                .getPreferences(principal.getAttribute("id"));
        final var form = new NotificationPreferencesForm(userPrefeerences);

        model.addAttribute("username", principal.getAttribute("username"));
        model.addAttribute("tags", tags.tags);
        model.addAttribute("authors", authors.authors);
        model.addAttribute("form", form);

        if (logger.isDebugEnabled()) {
            logger.debug("{}#get model={}", NotificationPreferencesController.class.getSimpleName(), model);
        }

        return "auth/notification-preferences";
    }

    @PostMapping
    public String post(@AuthenticationPrincipal final OAuth2User principal,
            @Validated final NotificationPreferencesForm form, final Model model) {
        final var tags = ghostApiService.getTags();
        final var authors = ghostApiService.getAuthors();

        syncFormWithGhostData(form, tags, authors);
        usersRepository.upsertPreferences(principal.getAttribute("id"), new UserPreferences.Value(form));

        model.addAttribute("username", principal.getAttribute("username"));
        model.addAttribute("tags", tags.tags);
        model.addAttribute("authors", authors.authors);
        model.addAttribute("form", form);
        model.addAttribute("updated", Boolean.TRUE);

        if (logger.isDebugEnabled()) {
            logger.debug("{}#post model={}", NotificationPreferencesController.class.getSimpleName(), model);
        }

        return "auth/notification-preferences";
    }

    @ModelAttribute(name = "returnUrl")
    String bindReturnUrl(@RequestParam final String returnUrl) {
        return returnUrl;
    }

    void syncFormWithGhostData(final NotificationPreferencesForm form, final Tags tags, final Authors authors) {
        form.setTags(form.getTags().stream().filter(tag -> {
            return tags.tags.stream().anyMatch(tagMaster -> tag.equals(tagMaster.id));
        }).collect(Collectors.toList()));

        form.setAuthors(form.getAuthors().stream().filter(author -> {
            return authors.authors.stream().anyMatch(authorMaster -> author.equals(authorMaster.id));
        }).collect(Collectors.toList()));
    }

}
