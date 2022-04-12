package my.springboot_mvcjpa_231.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("/user").setViewName("user");
//        registry.addViewController("/admin").setViewName("allUser");
    }
}
