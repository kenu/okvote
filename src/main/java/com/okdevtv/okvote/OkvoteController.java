package com.okdevtv.okvote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OkvoteController {
  @RequestMapping("/")
  public String index() {
    return "index";
  }
  @RequestMapping("/login")
  public String login() {
    return "login";
  }
  @RequestMapping("/result")
  public String result() {
    return "result";
  }
  @RequestMapping("/list")
  public String list() {
    return "list";
  }
}
