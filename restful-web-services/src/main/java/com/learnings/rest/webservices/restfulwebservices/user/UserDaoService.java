package com.learnings.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //JPA/Hibernate >Database
    //UserDaoService > Static list

    private static List<User> users = new ArrayList<>();
    private static int count = 0;

    static {
        users.add(new User(++count, "Akash", LocalDate.now().minusYears(25)));
        users.add(new User(++count, "Arvind", LocalDate.now().minusYears(26)));
        users.add(new User(++count, "Prathamesh", LocalDate.now().minusYears(24)));
    }


    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        //return users.stream().filter(x->x.getId().equals(id)).;
        // Predicate<? super User> predicate = user1 -> user1.getId().equals(id);

        return users.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
