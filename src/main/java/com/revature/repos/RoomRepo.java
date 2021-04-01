package com.revature.repos;

import com.revature.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/** Manager of the entity repository. Executes queries specified and implicit ones. */
@Component
@Repository
public interface RoomRepo extends CrudRepository<Room, Integer> {
}
