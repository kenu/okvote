package com.okdevtv.okvote.controller;

import com.okdevtv.okvote.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OkvoteController {
  @Autowired
  private QuestionRepository repository;
  @Autowired
  private AnswerRepository answerRepository;
  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/")
  public String index() {
    Question question = repository.save(new Question("question"));
    Answer answer1 = answerRepository.save(new Answer(question.getId(), "answer1"));
    Answer answer2 = answerRepository.save(new Answer(question.getId(), "answer2"));
    System.out.println(question.getId() + ";;  " + answer1.getId() + ";;  " + answer2.getId());
    return "index";
  }
  @RequestMapping("/form")
  public String form() {
    return "form";
  }
  @PostMapping("/form")
  public String form(@RequestParam String question) {
    System.out.println(question);
    return "form";
  }
  @RequestMapping("/login")
  public String login() {
    return "login";
  }
  @RequestMapping(method = RequestMethod.POST, path="/login")
  public String loginProcess(@RequestParam String name) {
    User user = userRepository.save(new User(name));
    System.out.println(user.toString());
    return "login";
  }
  @RequestMapping("/result")
  public String result() {
    return "result";
  }
  @RequestMapping("/list")
  public String list() {
    Iterable<Question> all = repository.findAll();
    all.forEach(item -> System.out.println(item.getQuestion()));
    return "list";
  }
}
