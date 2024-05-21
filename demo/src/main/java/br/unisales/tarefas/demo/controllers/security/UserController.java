package br.unisales.tarefas.demo.controllers.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisales.tarefas.demo.annotations.ValidaAcesso;
import br.unisales.tarefas.demo.controllers.DefaultController;
import br.unisales.tarefas.demo.models.security.User;
import br.unisales.tarefas.demo.repositories.security.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/users")
@ValidaAcesso("ROLE_ADMIN")
public class UserController extends DefaultController {

 @Autowired
 UserRepository userRepository;

 @Autowired
 PasswordEncoder passwordEncoder;


 @GetMapping("")
 public ResponseEntity<List<User>> todosPorLogin() {
  return new ResponseEntity(userRepository.findAllByOrderByLoginAsc(), HttpStatus.OK);
 }

 @PostMapping("")
 public ResponseEntity<?> salva(@RequestBody User user) {
  user.setSenha(passwordEncoder.encode(user.getSenha()));
  if (user.getId() != null) {
   user = userRepository.save(user);
   return new ResponseEntity(user, HttpStatus.resolve(204));
  } else {
   user = userRepository.save(user);
   return new ResponseEntity(user, HttpStatus.CREATED);
  }
 }

}
