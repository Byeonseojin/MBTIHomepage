package org.example.mbtihomepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MbtiHomepageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtiHomepageApplication.class, args);
    }

}
