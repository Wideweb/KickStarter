package com.kickstarter.logic.services;

import com.kickstarter.logic.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface IUserService extends UserDetailsService {
    User registerUser(User user) throws Exception;
    User loginUser(String userName, String password) throws Exception;
    User getUserByName(String userName);
}
