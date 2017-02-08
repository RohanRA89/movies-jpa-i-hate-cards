package com.ironyard.controller;

import com.ironyard.data.Movie;
import com.ironyard.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jasonskipper on 2/7/17.
 */
@Controller
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    @RequestMapping(path = "/secure/movie/createlist", method = RequestMethod.POST)
    public String createMovies(Model createList,
        @RequestParam String name, @RequestParam String description, @RequestParam String mpaaRating,
        @RequestParam String category, @RequestParam Double rating, @RequestParam String posterUrl) {
        Movie created = new Movie(name,description,category,mpaaRating,rating,posterUrl);
        movieRepo.save(created);
        if(created.getId()>0) {
            createList.addAttribute("succes_movie_create_msg",
                    String.format("Movie '%s' was created!", created.getName()));
        }

        return "/secure/create";
    }

    @RequestMapping(path = "/secure/movies")
    public String listMovies(Model xyz) {
        String destination = "home";


        Iterable found = movieRepo.findAll();

        // convert to lists because i like them
//        Iterator<Movie> itr = found.iterator();
//        List<Movie> data = new ArrayList();
//        while (itr.hasNext()) {
//            data.add(itr.next());
//        }

        // put list into model
        xyz.addAttribute("mList", found);

        // go to jsp
        return destination;
    }






}
