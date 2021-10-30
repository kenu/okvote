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

  @RequestMapping("/{qno}")
  public String index(@PathVariable(name="qno", required = false) Long qno, Model model) {
    System.out.println(qno);
    Question question = repository.findById(qno).get();
    model.addAttribute("question", question.getQuestion());

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
  public RedirectView form(@RequestParam String question, @RequestParam(name = "answer") String[] answers,
                           @CookieValue(name = "name", required = false) String name) {
    System.out.println(question);
    System.out.println(answers.length);
    User user = userRepository.findUserByName(name);

    Question question1 = repository.save(new Question(user.getId(), question));
    for (String answer : answers) {
      answerRepository.save(new Answer(question1.getId(), answer));
    }
    return new RedirectView("/" + question1.getId());
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
