package ru.kata.spring.boot_security.demo.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String userList( Model model) {
        model.addAttribute("list", userService.getAllUsers());
        return "admin";
    }

    @PostMapping (value = "/admin/delete")
    public String deleteUser(@RequestParam(name = "del") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit")
    public String edit (@RequestParam(name = "edit") String username, Model model) {
        User user = (User) userService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("listRole", userService.getAllRoles());
        return "edit";
    }

    @PostMapping(value = "/admin/edit")
    public String saveEdit (@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/addUser")
    public String addUser(Model model) {
        model.addAttribute("listRole", userService.getAllRoles());
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "/admin/addUser")
    public String saveAddUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
}