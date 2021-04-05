package com.meraj.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meraj.springboot.model.Support;

@Repository
public interface SupportRepository extends MongoRepository<Support,Long> {

}
