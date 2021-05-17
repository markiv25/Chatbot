
              
             package com.example.demo;
             import java.util.concurrent.atomic.AtomicLong;
              import org.springframework.boot.SpringApplication;
              import org.springframework.boot.autoconfigure.SpringBootApplication;
              import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
              import org.springframework.web.bind.annotation.RestController;
              import java.io.*;
              import java.util.*;
              import java.nio.file.*;
              import java.util.Map.Entry;
              import com.example.demo.*;
              @SpringBootApplication
              @RestController
              public class DemoApplication {
               
                ChatBot sd = new ChatBot();     
                public String out;    
               
                  public static void main(String[] args) {
                    DemoApplication da= new DemoApplication();
                  SpringApplication.run(DemoApplication.class, args);
                   // da.call("fever,headache");
                }
                  
                  @GetMapping("/hello/{name}")
                  //call("fever,headache");
                  public greet hello(@PathVariable("name" )  String name) {
                   System.out.println(name);
                    return new greet( sd.call(name));
                  }
                 // @RequestParam(name="name", required = false, defaultValue = 
              }
            