package com.springbootstrap1.controller;
import com.springbootstrap1.model.User;
import com.springbootstrap1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping()
public class AdminController {

    @Autowired
    private UserService userService;

   @GetMapping("/admin")
   public String pageAllUsers(@AuthenticationPrincipal UserDetails userDetails, Model model){
       String email = userDetails.getUsername();
       User user = userService.getUserByUserEmail(email);
       model.addAttribute("user",user);
       model.addAttribute("users", userService.getAllUsers());
       model.addAttribute("roles",userService.getAllRoles());
       return "users";
   }

   @GetMapping("/edit/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
       model.addAttribute("user", userService.getUserById(id));
       return "users";
   }

   @PostMapping("/edit/{id}")
   public String editUser(@PathVariable("id") Long id,@ModelAttribute("user") User user) {
       user.setRole(userService.getRoleByName("ROLE_USER"));
       userService.updateUser(id, user);
       return "redirect:/admin";
   }

   @GetMapping("/new")
   public String addUser(Model model){
       model.addAttribute("newUser", new User());
       model.addAttribute("roles", userService.getAllRoles());
       return "new";
   }

   @PostMapping("/add")
   public String addNewUser(@ModelAttribute("newUser") User user,
                            @RequestParam(value = "checked", required = false) String[] checked){
       user.setRoles(userService.getRolesByRoleNames(checked));
       userService.saveUser(user);
       return "redirect:/admin";
   }

   @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        userService.delete(id);
       return "redirect:/admin";
   }
}
