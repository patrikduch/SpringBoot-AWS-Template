package com.patrikduch.springboot_aws_api.controller;

import com.patrikduch.springboot_aws_api_core.model.Role;
import com.patrikduch.springboot_aws_api_core.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private RoleRepository repository;


    @GetMapping("/roles")
    public List<Role> getRoles() {
        return repository.findAll();
    }

    @GetMapping("/roles/{id}")
    public Optional<Role> getRoleById(@PathVariable int id) {
        return repository.findById(id);

    }

    @PostMapping("/role")
    public String saveRole(@RequestBody Role role) {
        repository.save(role);

        return  "Added role with id: " + role.getId();

    }

    @DeleteMapping("/roles/{id}")
    public String deleteRole(@PathVariable int id) {

        repository.deleteById(id);

        return "Role with id: " + id  + "  has been successfully remoed.";

    }
}
