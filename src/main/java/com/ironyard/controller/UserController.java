package com.ironyard.controller;

import com.ironyard.data.MovieUser;
import com.ironyard.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jasonskipper on 2/6/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(path = "/secure/user/delete", method = RequestMethod.GET)
    public String deleteUsers(Model deleteUser, @RequestParam Long id){
        userRepo.delete(id);
        deleteUser.addAttribute("success_delete_msg","Movie successfully deleted");
        return "forward:/secure/users";
    }

    @RequestMapping(path = "/secure/user/select", method = RequestMethod.GET)
    public String selectUser(Model selectUser, @RequestParam Long id){
        MovieUser editUser = userRepo.findOne(id);
        selectUser.addAttribute("editUser",editUser);
        return "/secure/users";
    }


    @RequestMapping(path="/open/usercreation", method = RequestMethod.GET)
    public String createUser(HttpSession createdUser, Model createdUserData, @RequestParam String username,
         @RequestParam String password, @RequestParam String displayName){
        MovieUser created = new MovieUser();
        created.setUsername(username);
        created.setPassword(password);
        created.setDisplayName(displayName);
        userRepo.save(created);
        String loginDestination = "home";
        if(created == null){
            loginDestination = "login";
            createdUserData.addAttribute("accountCreateMessage", "Your account was not created. Make sure to enter information in to all fields");
        }else{
            createdUser.setAttribute("user", created);
            loginDestination = "redirect:/secure/movies";
        }
        return loginDestination;
    }

    @RequestMapping(path = "/open/authenticate", method = RequestMethod.POST)
    public String login(HttpSession session, Model data, @RequestParam(name = "username") String usr,
        @RequestParam String password){
        MovieUser found = userRepo.findByUsernameAndPassword(usr, password);
        String destinationView = "home";
        if(found == null){
            // no user found, login must fail
            destinationView = "login";
            data.addAttribute("message", "User/Pass combination not found.");
        }else{
            session.setAttribute ("user", found);
            destinationView = "redirect:/secure/movies";
        }
        return destinationView;
    }
    @RequestMapping(path = "/secure/users")
    public String listMovies(Model xyz) {
        String destination = "users";


        Iterable found = userRepo.findAll();

        // convert to lists because i like them
//        Iterator<Movie> itr = found.iterator();
//        List<Movie> data = new ArrayList();
//        while (itr.hasNext()) {
//            data.add(itr.next());
//        }

        // put list into model
        xyz.addAttribute("uList", found);

        // go to jsp
        return destination;
    }
}
