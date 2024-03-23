package cn.hzcu.timeback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class TimebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimebackApplication.class, args);
	}

}
