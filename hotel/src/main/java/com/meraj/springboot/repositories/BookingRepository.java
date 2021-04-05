package com.meraj.springboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meraj.springboot.model.BookingDetails;

@Repository
public interface BookingRepository extends MongoRepository<BookingDetails,Long> {

}
