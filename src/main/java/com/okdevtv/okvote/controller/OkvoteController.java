package com.okdevtv.okvote.controller;

import com.okdevtv.okvote.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
  public Object form(Model model, @CookieValue(name = "name", required = false) String name) {
    if (name == null) {
      return new RedirectView("/login");
    }
    model.addAttribute("name", name);
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
  @PostMapping("/login")
  public RedirectView loginProcess(HttpServletRequest request, HttpServletResponse response, @RequestParam String name) {
    String lowerName = name.toLowerCase();
    Cookie cookie = new Cookie("name", lowerName);
    cookie.setMaxAge(60 * 60 * 24);
    cookie.setPath("/");
    response.addCookie(cookie);
    HttpSession session = request.getSession();
    session.setAttribute("name", lowerName);
    User userByName = userRepository.findUserByName(lowerName);
    if (userByName == null) {
      User user = userRepository.save(new User(lowerName));
    }
    return new RedirectView("/form");
  }

  @GetMapping("/logout")
  public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
    Cookie cookie = new Cookie("name", null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
    request.getSession().removeAttribute("name");
    return new RedirectView("/");
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
