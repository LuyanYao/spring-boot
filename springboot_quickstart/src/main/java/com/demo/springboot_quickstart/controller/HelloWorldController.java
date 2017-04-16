package com.demo.springboot_quickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by luyan on 2017/4/16.
 */
@RestController
@RequestMapping("/helloWorld")
public class HelloWorldController{

	@RequestMapping(value = "/{name}",method = RequestMethod.GET)
	public String Hello(@PathVariable("name") String name){
		return "Hello  , "+ name;
	}

}
