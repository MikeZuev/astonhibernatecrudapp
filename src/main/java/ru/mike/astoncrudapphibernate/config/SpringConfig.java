package ru.mike.astoncrudapphibernate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("ru.mike.astoncrudapphibernate")
@EnableWebMvc
public class SpringConfig {

}
