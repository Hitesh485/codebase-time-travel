package com.codebase.time_traveller;

import com.codebase.time_traveller.git.GitHistoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class
Runner {

    @Bean
    CommandLineRunner run(GitHistoryService service) {

        return args -> service.printLastCommits(5);
    }
}
