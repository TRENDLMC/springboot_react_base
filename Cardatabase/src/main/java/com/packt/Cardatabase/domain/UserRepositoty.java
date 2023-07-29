package com.packt.Cardatabase.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoty extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
