package com.okdevtv.okvote.controller;

import com.okdevtv.okvote.model.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.zaxxer.hikari.HikariDataSource;
import io.sentry.spring.tracing.SentrySpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Validated
public class OkvoteController {
  @Autowired
  private QuestionRepository repository;
  @Autowired
  private AnswerRepository answerRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private VoteRepository voteRepository;
  @Autowired
  private AnswerResultRepository answerResultRepository;
  @Autowired
  protected HikariDataSource dataSource;

  @GetMapping("/{qno}")
  public String index(@PathVariable(name = "qno", required = false) Long qno, Model model) {
    Question question = repository.findById(qno).get();
    model.addAttribute("id", question.getId());
    model.addAttribute("question", question.getQuestion());
    List<Answer> answers = answerRepository.findByQuestionId(qno);
    model.addAttribute("answers", answers);

    return "index";
  }

  @PostMapping("/{qno}")
  public Object index(@PathVariable("qno") Long qno,
                      @CookieValue(name = "name", required = false) String name,
                      @RequestParam("selected") Long answerId) {
    if (name == null) {
      return new RedirectView("/login?returnUrl=/" + qno);
    }
    User user = userRepository.findUserByName(name);
    Long userId = user.getId();
    Vote vote = voteRepository.findByUserIdAnswerId(userId, answerId);
    if (vote == null) {
      voteRepository.save(new Vote(userId, answerId));
    }
    return new RedirectView("/result/" + qno);
  }

  @RequestMapping("/form")
  public Object form(Model model, @CookieValue(name = "name", required = false) String name) {
    if (name == null) {
      return new RedirectView("/login?returnUrl=/form");
    }
    model.addAttribute("name", name);
    return "form";
  }

  @PostMapping("/form")
  public RedirectView form(@RequestParam @NotBlank String question,
                           @RequestParam(name = "answer") @NotEmpty List<@NotBlank String> answers,
                           @CookieValue(name = "name", required = false) String name) {
    User user = userRepository.findUserByName(name);
    if (user == null) {
      user = userRepository.save(new User(name));
    }

    Question question1 = repository.save(new Question(user.getId(), question));
    for (String answer : answers) {
      answerRepository.save(new Answer(question1.getId(), answer));
    }
    return new RedirectView("/" + question1.getId());
  }

  @RequestMapping("/login")
  public String login(@RequestParam(name = "returnUrl", defaultValue = "/list") String returnUrl,
      @RequestParam(name = "error", defaultValue = "") String error, Model model) {
    if ("name".equals(error)) {
      model.addAttribute("error", "* 3~30자의 영문 소문자만 사용할 수 있습니다.");
    }
    model.addAttribute("returnUrl", returnUrl);
    return "login";
  }

  @PostMapping("/login")
  public RedirectView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(name = "returnUrl", defaultValue = "/list") String returnUrl, @RequestParam String name) {
    String lowerName = name.toLowerCase();
    int length = lowerName.length();
    if (length < 3 || length > 30) {
      return new RedirectView("/login?returnUrl=" + returnUrl + "&error=name");
    }
    Cookie cookie = new Cookie("name", lowerName);
    cookie.setMaxAge(60 * 60 * 24);
    cookie.setPath("/");
    response.addCookie(cookie);
    HttpSession session = request.getSession();
    session.setAttribute("name", lowerName);
    User userByName = userRepository.findUserByName(lowerName);
    if (userByName == null) {
      userRepository.save(new User(lowerName));
    }
    return new RedirectView(returnUrl);
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

  @RequestMapping("/result/{qno}")
  public String result(@PathVariable("qno") Long qno, Model model) {
    Question question = repository.findById(qno).get();
    List<AnswerDto> answers = answerResultRepository.findAnswerResult(qno);
    Long total = 0L;
    for(AnswerDto answer : answers) {
      total += answer.getCnt();
    }
    Long finalTotal = (total == 0L) ? 1 : total;
    answers.forEach(answer -> {
      Long percent = answer.getCnt() * 100 / finalTotal;
      answer.setPercent(percent);
    });

    model.addAttribute("question", question);
    model.addAttribute("answers", answers);
    return "result";
  }

  @SentrySpan
  @RequestMapping("/list")
  public String list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, Model model) {
    Integer pageNo = (page > 0) ? page - 1 : 0;
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    Page<Question> questions = repository.findAll(PageRequest.of(pageNo, 5, sort));
    model.addAttribute("questions", questions);
    return "list";
  }
}
