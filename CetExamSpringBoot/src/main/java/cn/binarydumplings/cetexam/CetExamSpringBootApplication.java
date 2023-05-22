package cn.binarydumplings.cetexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan(basePackages = "cn.binarydumplings.cetexam")
public class CetExamSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CetExamSpringBootApplication.class, args);
	}

}
