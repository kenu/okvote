package com.okdevtv.okvote.controller;

import com.okdevtv.okvote.model.Answer;
import com.okdevtv.okvote.model.AnswerRepository;
import com.okdevtv.okvote.model.Question;
import com.okdevtv.okvote.model.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OkvoteController {
  @Autowired
  private QuestionRepository repository;
  @Autowired
  private AnswerRepository answerRepository;

  @RequestMapping("/")
  public String index() {
    Question question = repository.save(new Question("question"));
    Answer answer1 = answerRepository.save(new Answer(question.getId(), "answer1"));
    Answer answer2 = answerRepository.save(new Answer(question.getId(), "answer2"));
    System.out.println(question.getId() + "  " + answer1.getId() + "  " + answer2.getId());
    return "index";
  }
  @RequestMapping("/form")
  public String form() {
    return "form";
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
    Iterable<Question> all = repository.findAll();
    all.forEach(item -> System.out.println(item.getQuestion()));
    return "list";
  }
}
