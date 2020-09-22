/*
package com.digitalwallet.ussd.controller;

import com.digitalwallet.ussd.domain.User;
import com.digitalwallet.ussd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    final
    private AdminService adminService;

    public AdminController(@Autowired AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/get_user_by_first_name")
    public User getUserByFirstName(@RequestParam("firstName") String firstName) {
        return adminService.findByFirstName(firstName);
    }

    @PostMapping("/create_user")
    public User createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return adminService.createUser(firstName, lastName);
    }
}
*/