package pl.sda.register.repository;


import org.springframework.stereotype.Repository;
import pl.sda.register.exception.DuplicatedUserNameException;
import pl.sda.register.exception.UserNotFoundException;
import pl.sda.register.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class UserRepository{
    
    private Set<User> users = initializeUsers();
    
    
    private Set<User> initializeUsers(){
        return new HashSet<>(Arrays.asList(new User("login", "Captain", "Jack")));
    }
    
    
    public Set<String> findAllUserNames(){
        return users.stream().map(User::getUsername).collect(Collectors.toSet());
    }
    
    
    public User findUserByUsername(String username){
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }
    
    
    public void addUser(User user){
        users.stream().filter(usr -> usr.getUsername().equals(user.getUsername())).findAny().ifPresent(u -> {
            throw new DuplicatedUserNameException("User with username: " + user.getUsername() + " already exist");
        });
        
        users.add(user);
    }
    
    
    public Set<String> findUsersByFirstName(String firstName, boolean matchExact){
        return users.stream().filter(user -> {
            if (matchExact) {
                return user.getFirstName().equals(firstName);
            } else {
                return user.getFirstName().contains(firstName);
            }
        }).map(User::getUsername).collect(Collectors.toSet());
    }
}
