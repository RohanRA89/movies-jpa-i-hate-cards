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

//    @Autowired
//    MovieRepo createdMovieRepo;

    @RequestMapping(path = "/secure/movies")
    public String listMovies(Model xyz) {
        String destination = "home";


        Iterable found = movieRepo.findAll();

        // convert to lists because i like them
        Iterator<Movie> itr = found.iterator();
        List<Movie> data = new ArrayList();
        while (itr.hasNext()) {
            data.add(itr.next());
        }

        // put list into model
        xyz.addAttribute("mList", data);

        // go to jsp
        return destination;
    }



    @RequestMapping(path = "/secure/createlist", method = RequestMethod.POST)
    public String createMovies(HttpSession session, Model createListError, @RequestParam(name = "movieName") String name,
   @RequestParam(name = "movieDescription") String description, @RequestParam(name = "movieMpaaRating") String mpaaRating,
   @RequestParam(name = "movieCategory") String category, @RequestParam(name = "movieStarRating") double rating,
   @RequestParam(name = "moviePoster") String posterUrl) {
        Movie createdList = movieRepo.findByInputFromForm(name, description, category, mpaaRating, rating, posterUrl);
        String destinationView = "home";
        if (createdList == null) {
            // no movie list found, list save must fail
            destinationView = "create";
            createListError.addAttribute("message", "User/Pass combination not found.");
        } else {
            session.setAttribute("userList", createdList);
            destinationView = "redirect:/secure/home";
        }
        return destinationView;
    }
}
