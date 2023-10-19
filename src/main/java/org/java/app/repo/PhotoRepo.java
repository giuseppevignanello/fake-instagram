package org.java.app.repo;


import java.util.List;

import org.java.app.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoRepo extends JpaRepository <Photo, Integer> {
	public List<Photo> findByTitleContainingOrDescriptionContaining(String title, String description);

	public List<Photo> findByTitleContaining(String filter);
}
