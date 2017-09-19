package com.lebron.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lebron.springboot.httpclient.HttpAPIService;
import com.lebron.springboot.httpclient.HttpResult;

@RestController
public class TestController {

    @Autowired
    private HttpAPIService httpAPIService;
    
    @RequestMapping("/get")
    public ResponseEntity<String> test() throws Exception{
        HttpResult doGet = httpAPIService.doGet("http://www.baidu.com");
        return ResponseEntity.status(doGet.getCode()).body(doGet.getBody());
    }
}
