package com.example.jet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping()
    public String index() {
        return "Server is running on port: " + serverPort;
    }
}
