package org.example.web;

import org.example.web.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String homepage(Model model, @RequestParam(required = false, defaultValue = "stranger")String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDate", new Date());
        return "index.html";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("currentDate", new Date());
        model.addAttribute("loginForm", new LoginForm());
        return "login.html";
    }

    @PostMapping("/login")
    // BindingResult Parameter must be placed behind loginForm otherwise it won't work
    // because it usually would get caught by teh GlobalExceptionHandler
    // Binding Result is a container for validation errors
    public String login(@ModelAttribute @Valid LoginForm loginForm,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "login.html";
        }
        if (loginForm.getUsername().equals(loginForm.getPassword())) {
            return "redirect:/";
        }
        model.addAttribute("currentDate", new Date());
        model.addAttribute("invalidCredentials", "true");
        return "login.html";
    }
}
