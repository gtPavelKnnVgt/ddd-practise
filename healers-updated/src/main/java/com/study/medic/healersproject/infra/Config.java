package com.study.medic.healersproject.infra;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.study.medic.healersproject.domain")
@EnableJpaRepositories(basePackages =  {
        "com.study.medic.healersproject.persistence"
})
@ComponentScan(basePackages = "com.study.medic.healersproject")
public class Config {
}
