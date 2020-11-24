package app.connectivity.web;

import app.config.GhostFeignConfiguration;
import app.data.api.ghost.AuthorsResponse;
import app.data.api.ghost.TagsResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

@FeignClient(name = "ghost", url = "${app.ghost.api.url}/ghost/api/v3/content",
        configuration = GhostFeignConfiguration.class)
@Validated
public interface GhostContentApiClient {

    /**
     * GET /tags/ : Browse tags
     *
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @param filter  Apply fine-grained filters to target specific data. (optional)
     * @param limit   By default, only 15 records are returned at once. (optional, default to 15)
     * @param page    By default, the first 15 records are returned. (optional, default to 1)
     * @param order   Different resources have a different default sort order. (optional)
     * @return OK (with Meta)
     */
    @GetMapping("/tags/")
    ResponseEntity<TagsResponse> getTags(@Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields,
            @Valid @RequestParam(value = "filter", required = false) String filter,
            @Valid @RequestParam(value = "limit", required = false, defaultValue = "15") String limit,
            @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Valid @RequestParam(value = "order", required = false) String order);

    /**
     * GET /tags/{id} : Read a tag by ID
     *
     * @param id      The tag ID (required)
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @return OK (without Meta)
     */
    @GetMapping("/tags/{id}")
    ResponseEntity<TagsResponse> getTagsById(@PathVariable("id") String id,
            @Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields);

    /**
     * GET /tags/slug/{slug}/ : Read a tag by slug
     *
     * @param slug    The tag slug (required)
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @return OK (without Meta)
     */
    @GetMapping("/tags/slug/{slug}/")
    ResponseEntity<TagsResponse> getTagsBySlug(@PathVariable("slug") String slug,
            @Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields);

    /**
     * GET /authors/ : Browse authors
     *
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @param filter  Apply fine-grained filters to target specific data. (optional)
     * @param limit   By default, only 15 records are returned at once. (optional, default to 15)
     * @param page    By default, the first 15 records are returned. (optional, default to 1)
     * @param order   Different resources have a different default sort order. (optional)
     * @return OK (with Meta)
     */
    @GetMapping("/authors/")
    ResponseEntity<AuthorsResponse> getAuthors(
            @Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields,
            @Valid @RequestParam(value = "filter", required = false) String filter,
            @Valid @RequestParam(value = "limit", required = false, defaultValue = "15") String limit,
            @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Valid @RequestParam(value = "order", required = false) String order);

    /**
     * GET /authors/{id} : Read an author by ID
     *
     * @param id      The author ID (required)
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @return OK (without Meta)
     */
    @GetMapping("/authors/{id}")
    ResponseEntity<AuthorsResponse> getAuthorsById(@PathVariable("id") String id,
            @Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields);

    /**
     * GET /authors/slug/{slug}/ : Read an author by slug
     *
     * @param slug    The author slug (required)
     * @param include Tells the API to return additional data related to the resource you have requested. (optional)
     * @param fields  Limit the fields returned in the response object. (optional)
     * @return OK (without Meta)
     */
    @GetMapping("/authors/slug/{slug}/")
    ResponseEntity<AuthorsResponse> getAuthorsBySlug(@PathVariable("slug") String slug,
            @Valid @RequestParam(value = "include", required = false) List<String> include,
            @Valid @RequestParam(value = "fields", required = false) List<String> fields);

}
