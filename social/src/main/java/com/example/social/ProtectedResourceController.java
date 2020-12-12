package com.example.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProtectedResourceController {

    @GetMapping
    public String index() {
        return "index";
    }

}
