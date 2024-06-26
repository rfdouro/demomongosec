package br.unisales.tarefas.demo.models.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("usuarios")
public class User {

 @Id
 private String id;
 @Indexed(unique = true)
 private String login;
 private String senha;

 private List<String> permissoes = new ArrayList<>();

}
