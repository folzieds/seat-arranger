package com.phos.seatarrangement;

import com.phos.seatarrangement.core.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SeatArrangementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatArrangementApplication.class, args);
    }

}
