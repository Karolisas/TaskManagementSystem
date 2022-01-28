package lt.karolis.demo.TaskManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan (basePackages = "lt.karolis.crm")
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

}
