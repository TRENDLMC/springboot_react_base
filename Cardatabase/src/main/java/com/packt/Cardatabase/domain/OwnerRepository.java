package com.packt.Cardatabase.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@RepositoryRestResource(path = "vehicles")
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
