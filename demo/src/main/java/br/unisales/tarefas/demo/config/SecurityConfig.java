package br.unisales.tarefas.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.unisales.tarefas.demo.config.handlers.LoginInterceptor;
import br.unisales.tarefas.demo.models.security.User;
import br.unisales.tarefas.demo.repositories.security.UserRepository;
import br.unisales.tarefas.demo.services.UserService;
import br.unisales.tarefas.demo.util.JWTUtil;

@Configuration
@EnableWebMvc
public class SecurityConfig implements CommandLineRunner, WebMvcConfigurer {

  @Autowired
  private UserService userService;

  @Autowired
  JWTUtil jwtUtil;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private void addUsers() {
    User u = new User();
    try {
      u.setLogin("admin");
      u.setSenha(passwordEncoder().encode("1234"));
      u.getPermissoes().add("ROLE_USER");
      u.getPermissoes().add("ROLE_ADMIN");
      //userRepository.save(u);
      userService.insert(u);
    } catch (Exception ex) {
    }

    try {
      u = new User();
      u.setLogin("usuario1");
      u.setSenha(passwordEncoder().encode("1234"));
      u.getPermissoes().add("ROLE_USER");
      u.getPermissoes().add("ROLE_TAREFAS");
      //userRepository.save(u);
      userService.insert(u);
    } catch (Exception ex) {
    }

    try {
      u = new User();
      u.setLogin("usuario2");
      u.setSenha(passwordEncoder().encode("1234"));
      u.getPermissoes().add("ROLE_USER");
      //userRepository.save(u);
      userService.insert(u);
    } catch (Exception ex) {
    }

  }

  /**
   * método executado no momento da execução da aplicação
   * 
   * @param args
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    addUsers();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor(userService, jwtUtil))
        .excludePathPatterns("/error**", "/index**", "/doc**", "/auth**", "/swagger-ui**", "/v3/api-docs**")
        .addPathPatterns("/tarefas**", "/users**");
  }
}
