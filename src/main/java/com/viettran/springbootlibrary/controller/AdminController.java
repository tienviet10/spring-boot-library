package com.viettran.springbootlibrary.controller;

import com.viettran.springbootlibrary.requestmodels.AddBookRequest;
import com.viettran.springbootlibrary.service.AdminService;
import com.viettran.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value = "Authorization") String token, @RequestBody AddBookRequest addBookRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Unauthorized");
        }

        adminService.postBook(addBookRequest);
    }
}
