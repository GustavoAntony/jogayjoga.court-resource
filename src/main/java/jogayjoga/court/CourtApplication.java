package jogayjoga.court;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//caso erro tirar aqui o base packages
@EnableFeignClients(basePackages = {"jogayjoga.sport"})
@EnableDiscoveryClient
@SpringBootApplication
public class CourtApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourtApplication.class, args);
    }
    
}
