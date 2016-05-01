package com.kickstarter.logic.services;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticationService {
    void authenticate(String userName, String password, HttpServletRequest request);
}
