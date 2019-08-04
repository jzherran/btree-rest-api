package com.btree.btreerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BtreeRestApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BtreeRestApiApplication.class, args);
  }
}
