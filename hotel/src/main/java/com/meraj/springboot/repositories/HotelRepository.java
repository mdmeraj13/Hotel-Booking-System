package com.meraj.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meraj.springboot.model.Hotel;

@Repository
public interface HotelRepository  extends MongoRepository<Hotel,Long>{

	List<Hotel> findByLocation(String location);

	List<Hotel> findByLocationAndStatus(String location, boolean b);

}
