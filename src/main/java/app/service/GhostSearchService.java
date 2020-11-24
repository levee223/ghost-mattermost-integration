package app.service;

import app.data.api.ghost.search.ImmutableSearchResult;
import app.data.api.ghost.search.SearchResult;
import app.data.db.ghost.Post;
import app.repository.db.ghost.SearchRepository;
import app.util.Abbreviator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class GhostSearchService {

    private static final Pattern searchQueryPattern = Pattern.compile("\"([^\"]+)\"|([^\\s]+)");

    private static final int MAX_SUMMARY_LENGTH = 200;

    @Autowired
    SearchRepository searchRepository;

    public List<SearchResult> searchPosts(final String searchQuery) {
        final var searchTexts = new ArrayList<String>();
        final var searchQueryMatcher = searchQueryPattern.matcher(searchQuery);
        while (searchQueryMatcher.find()) {
            final String searchText = StringUtils
                    .defaultString(searchQueryMatcher.group(1), searchQueryMatcher.group(2)).trim();
            if (!searchText.isEmpty()) {
                searchTexts.add(searchText);
            }
        }

        return searchPosts(searchTexts);
    }

    List<SearchResult> searchPosts(final List<String> searchTexts) {
        final var searchKeywords = new ArrayList<String>();
        final var searchAuthors = new ArrayList<String>();
        final var searchTags = new ArrayList<String>();

        final Map<String, String> authorsNameIdMap = searchRepository
                .getAuthorsWithNameIdMap(() -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        final Map<String, String> tagsNameIdMap = searchRepository
                .getTagsWithNameIdMap(() -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER));

        new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        searchTexts.forEach(searchText -> {
            final String searchAuthorId = authorsNameIdMap.get(searchText);
            if (searchAuthorId != null) {
                searchAuthors.add(searchAuthorId);
                return;
            }
            final String searchTagId = tagsNameIdMap.get(searchText);
            if (searchTagId != null) {
                searchTags.add(searchTagId);
                return;
            }
            searchKeywords.add(searchText);
        });

        final List<Post> posts = searchRepository.getPosts(searchKeywords, searchAuthors, searchTags);

        final List<SearchResult> results = posts.stream()
                .map(post -> ImmutableSearchResult.builder().title(post.title()).slug(post.slug()).summary(Abbreviator
                        .abbreviate(StringUtils.remove(post.plaintext(), '\n'), MAX_SUMMARY_LENGTH, searchKeywords))
                        .updatedAt(post.updatedAt()).build())
                .collect(Collectors.toList());
        return results;
    }

}
