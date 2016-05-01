package com.kickstarter.controllers;

import com.kickstarter.controllers.tools.CustomJsonResult;
import com.kickstarter.logic.domain.User;
import com.kickstarter.logic.services.IAuthenticationService;
import com.kickstarter.logic.services.IUserService;
import com.kickstarter.models.UserLoginModel;
import com.kickstarter.models.UserRegistrationModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource(name = "authenticationService")
    private IAuthenticationService authenticationService;

    @Resource(name = "userService")
    private IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody CustomJsonResult Register(@RequestBody UserRegistrationModel model, HttpServletRequest request) {

        User user = new User();
        user.setUsername(model.getUserName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());

        return CustomJsonResult.TryGetJsonResult(() -> {
            User registeredUser = userService.registerUser(user);
            request.getSession().invalidate();
            authenticationService.authenticate(
                    registeredUser.getUsername(), registeredUser.getPassword(), request);

            return registeredUser;
        });
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody CustomJsonResult Login(@RequestBody UserLoginModel model, HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() -> {
            User user = userService.loginUser(model.getUsername(), model.getPassword());
            request.getSession().invalidate();
            authenticationService.authenticate(
                    user.getUsername(),  user.getPassword(), request);

            return user;
        });
    }


    @RequestMapping(value = "/logoff", method = RequestMethod.POST)
    public @ResponseBody CustomJsonResult Logoff(HttpServletRequest request) {
        return CustomJsonResult.TryExecute(()->
            request.getSession().invalidate()
        );
    }
}
