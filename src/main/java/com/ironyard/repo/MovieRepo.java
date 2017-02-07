package com.ironyard.repo;

import com.ironyard.data.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jasonskipper on 2/6/17.
 */
public interface MovieRepo extends CrudRepository<Movie, Long> {
    public Movie findByInputFromForm(String name, String description, String category, String mpaaRating, double rating, String posterUrl );
}


