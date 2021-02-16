package com.kenjohn.posapi.repositories;

import com.kenjohn.posapi.models.DAOUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
