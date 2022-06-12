package com.example.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResources {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id){
        User user = service.getUserById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User user = service.deleteUserById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User createdUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
