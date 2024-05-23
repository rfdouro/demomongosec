package br.unisales.tarefas.demo.controllers.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisales.tarefas.demo.annotations.ValidaAcesso;
import br.unisales.tarefas.demo.controllers.DefaultController;
import br.unisales.tarefas.demo.models.security.User;
import br.unisales.tarefas.demo.repositories.security.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

 @GetMapping("/{id}")
 public ResponseEntity<?> getMethodName(@PathVariable(value = "id", required = true) String id) {
  User user = userRepository.findById(id).get();
  if (user != null)
   return new ResponseEntity(user, HttpStatus.OK);
  else
   return new ResponseEntity(null, HttpStatus.NOT_FOUND);
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

 @PutMapping("/{id}")
 public ResponseEntity<?> atualiza(@PathVariable String id, @RequestBody User user) {
  Optional<User> ouser = userRepository.findById(id);
  if (ouser.isPresent()) {
   User _user = ouser.get();
   _user.setPermissoes(user.getPermissoes());
   userRepository.save(_user);
   return new ResponseEntity(_user, HttpStatus.OK);
  } else {
   return new ResponseEntity("Usuário não existe com o id " + id, HttpStatus.NOT_FOUND);
  }
 }

}
