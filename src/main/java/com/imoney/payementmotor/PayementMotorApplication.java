package com.imoney.payementmotor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EntityScan(basePackageClasses = {
		PayementMotorApplication.class,
		Jsr310JpaConverters.class
})
@EnableConfigurationProperties
public class PayementMotorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayementMotorApplication.class, args);
	}

}
