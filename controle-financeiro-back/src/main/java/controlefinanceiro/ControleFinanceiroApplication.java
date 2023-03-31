package controlefinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "controlefinanceiro", exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "controlefinanceiro.model")
public class ControleFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleFinanceiroApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder getPassWordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder;
    }

}
