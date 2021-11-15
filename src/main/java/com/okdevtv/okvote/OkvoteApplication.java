package com.okdevtv.okvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@SpringBootApplication
public class OkvoteApplication {

  public static void main(String[] args) {
    SpringApplication.run(OkvoteApplication.class, args);
  }

  @Bean
  public LayoutDialect layoutDialect() {
    return new LayoutDialect();
  }

}
