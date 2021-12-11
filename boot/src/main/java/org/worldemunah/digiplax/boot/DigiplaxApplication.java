package org.worldemunah.digiplax.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.worldemunah.digiplax"})
public class DigiplaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigiplaxApplication.class, args);
    }

}
