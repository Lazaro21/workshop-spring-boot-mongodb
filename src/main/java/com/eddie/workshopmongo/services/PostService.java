package com.eddie.workshopmongo.services;

import com.eddie.workshopmongo.domain.Post;
import com.eddie.workshopmongo.domain.User;
import com.eddie.workshopmongo.dto.UserDTO;
import com.eddie.workshopmongo.repository.PostRepository;
import com.eddie.workshopmongo.repository.UserRepository;
import com.eddie.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }
}
