package com.company;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.Product;

@RestController
public class APITesting {
    private static Map<String, Product> productRepo = new HashMap<>();


    @Autowired
    private  Services topServices;

    @RequestMapping("/")
    public String sayHello() {
        return topServices.helloWorld();
     }
    @RequestMapping("/{id}")
    public String sayHello(@PathVariable String id) {

        return topServices.helloWorld(id);
    }
    @RequestMapping(value = "/files",method =RequestMethod.GET)
    public ResponseEntity<Object> ListFile()
    {
        return topServices.listFiles();
    }
    @RequestMapping("/folder/{id}")
    public ResponseEntity<Object> createDirectory(@PathVariable String id)
    {
        return topServices.createDirectory(id);
    }
    @RequestMapping(value = "/createFile/{id1:.+}",method =RequestMethod.POST)
    public ResponseEntity<Object> createFile(@PathVariable("id1") String filename)
    {

        return topServices.createFile(filename);
    }
   @RequestMapping(value = "/createFile/{id1:.+}/{id2:.+}",method =RequestMethod.PUT)
    public ResponseEntity<Object> createAndWriteToFile(@PathVariable("id1") String filename,@PathVariable("id2") String content)
    {
        return topServices.createFileAndWriteToFile(filename,content);
    }
    @RequestMapping("/read/{id1:.+}")
    public ResponseEntity<Object> readFile(@PathVariable("id1") String filename)
    {
        return topServices.readFile(filename);
    }
    @RequestMapping(value = "/deleteFile/{id1:.+}",method =RequestMethod.DELETE)
    public ResponseEntity<Object> deleteFile(@PathVariable("id1") String filename)
    {
        return topServices.deleteFile(filename);
    }
}