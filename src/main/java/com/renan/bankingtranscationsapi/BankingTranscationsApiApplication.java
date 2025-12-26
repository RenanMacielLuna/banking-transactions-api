package com.renan.bankingtranscationsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class BankingTranscationsApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankingTranscationsApiApplication.class, args);
  }
}
