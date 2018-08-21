package org.infogain.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserController {
	 @RequestMapping("/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Khushboo\", \"lastname\":\"Jain\"}," +
	           "{\"firstname\":\"Shubham\",\"lastname\":\"Kumar\"}]}";
	  }

}
