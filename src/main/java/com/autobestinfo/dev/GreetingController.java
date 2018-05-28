package com.autobestinfo.dev;

import java.util.concurrent.atomic.AtomicLong;

import com.autobestinfo.dev.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    UsersRepository usersRepository;


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

//    @RequestMapping("users/create")
//    public User creating() {
//        User user = new User();
//        user.setFirstName("Tester");
//        usersRepository.insert(user);
//        return user;
//    }



}
