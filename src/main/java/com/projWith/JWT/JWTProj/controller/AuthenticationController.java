package com.projWith.JWT.JWTProj.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projWith.JWT.JWTProj.model.User;
import com.projWith.JWT.JWTProj.service.MyUserDetailsService;
import com.projWith.JWT.JWTProj.util.JwtUtil;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestParam String username, @RequestParam String password, Model model, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("--------------"+jwt+"------------------");
        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        System.out.println("--------------"+cookie+"----------------");
        response.addCookie(cookie);

        model.addAttribute("username", username);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDetailsService.save(user);
        model.addAttribute("username", username);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(@CookieValue(value = "token", defaultValue = "") String token, Model model) {
        if (token.isEmpty()) {
            return "redirect:/login";
        }
        String username = jwtUtil.extractUsername(token);
        model.addAttribute("username", username);
        return "home";
    }

    
}
