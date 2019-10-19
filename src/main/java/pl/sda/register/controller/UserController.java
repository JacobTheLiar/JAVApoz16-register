package pl.sda.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.register.model.User;
import pl.sda.register.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView usersListView(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) boolean matchExact) {
        ModelAndView modelAndView = new ModelAndView("users");
        
        if (firstName == null || firstName.trim().isEmpty()) {
            modelAndView.addObject("users", userService.findAllUserNames());
        } else {
            modelAndView.addObject("users", userService.findUsersByFirstName(firstName, matchExact));
        }
        return modelAndView;
    }

    @GetMapping("/users/{username}")
    public ModelAndView userDetailsView(@PathVariable String username) {
        ModelAndView modelAndView = new ModelAndView("userDetails");
        modelAndView.addObject("user", userService.findUserByUserName(username));
        return modelAndView;
    }
    
    @GetMapping("/user/add")
    public ModelAndView createUserView() {
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/user/search")
    public ModelAndView searchUserView() {
        ModelAndView modelAndView = new ModelAndView("userSearch");
        //modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
}
