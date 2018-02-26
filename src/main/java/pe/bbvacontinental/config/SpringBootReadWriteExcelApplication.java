package pe.bbvacontinental.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"pe.bbvacontinental"})
public class SpringBootReadWriteExcelApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(SpringBootReadWriteExcelApplication.class, args);
	}
}
