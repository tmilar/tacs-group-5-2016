package org.utn.marvellator.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static java.util.Objects.isNull;

@ControllerAdvice
public class CurrentUserControllerAdvice {
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
				if (authentication == null)
					return null;
				Object a = authentication.getPrincipal();
				return (UserDetails)a;
    }
}