package org.java.app.repo;


import org.java.app.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoRepo extends JpaRepository <Photo, Integer> {

}
