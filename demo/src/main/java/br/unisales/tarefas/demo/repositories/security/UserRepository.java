package br.unisales.tarefas.demo.repositories.security;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.unisales.tarefas.demo.models.security.User;



@Repository
public interface UserRepository extends MongoRepository<User, String> {

 @Query("{login:'?0'}")
 User findByLogin(String login);

 List<User> findAllByLoginOrderByLogin(String login);

 List<User> findAllByOrderByLoginAsc();

}
