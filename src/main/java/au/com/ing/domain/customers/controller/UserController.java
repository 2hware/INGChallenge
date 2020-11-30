package au.com.ing.domain.customers.controller;

import au.com.ing.domain.customers.model.GetUserDetailsRequest;
import au.com.ing.domain.customers.model.User;
import au.com.ing.domain.customers.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api")
public class UserController {

@Autowired
UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @HystrixCommand(fallbackMethod = "userDetailsFallback")
    @GetMapping("/userdetails")
    public ResponseEntity<User> getUserDetails(@Valid @RequestBody GetUserDetailsRequest getUserDetailsRequest) throws Exception{
        LOGGER.info("Start getUserDetails");
        tryToFail("getUserDetails");
        Optional<User> user =  userService.findByID(getUserDetailsRequest.getUserID());
        if (user.isEmpty()) {
            LOGGER.info("End getUserDetails");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("End getUserDetails");
            return ResponseEntity.ok(user.get());
        }
    }

    public ResponseEntity<User> userDetailsFallback(GetUserDetailsRequest getUserDetailsRequest) {
        return ResponseEntity.ok(new User());
    }

    @HystrixCommand(fallbackMethod = "updateUserDetailsFallback")
    @PutMapping("/updateuserdetails")
    public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user) throws Exception{
        User updatedUser = userService.save(user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    public ResponseEntity<User> updateUserDetailsFallback(User user) {
        return ResponseEntity.ok(new User());
    }

    /**
     * tryToFail method is to throw Exception Randamly (80% success rate) , it just trying to simulate backend failure to demonstrate circuit breaker.
     * @param serviceName
     * @throws Exception
     */
    private void tryToFail(String serviceName) throws Exception {
        if (Math.random() > 0.8 ) {
            Thread.sleep(2000);
            throw new Exception(serviceName+" failure");

        }
    }
}
