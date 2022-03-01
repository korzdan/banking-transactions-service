package com.springproject.fileparserwebapp.utils;

import com.springproject.fileparserwebapp.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
