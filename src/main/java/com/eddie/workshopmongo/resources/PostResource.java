package com.eddie.workshopmongo.resources;

import com.eddie.workshopmongo.domain.Post;
import com.eddie.workshopmongo.domain.User;
import com.eddie.workshopmongo.dto.UserDTO;
import com.eddie.workshopmongo.resources.util.URL;
import com.eddie.workshopmongo.services.PostService;
import com.eddie.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(value="/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }



}
