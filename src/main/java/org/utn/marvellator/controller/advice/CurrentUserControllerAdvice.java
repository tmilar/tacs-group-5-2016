package org.utn.marvellator.controller.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static java.util.Objects.isNull;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute
    public void getCurrentUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", auth.getPrincipal());
    }
}