package br.unisales.tarefas.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisales.tarefas.demo.annotations.ValidaAcesso;
import br.unisales.tarefas.demo.models.domain.Tarefa;
import br.unisales.tarefas.demo.repositories.TarefaRepository;

@CrossOrigin
@RestController
@RequestMapping("/tarefas")
@ValidaAcesso("ROLE_TAREFAS")
public class TarefaController extends DefaultController{
 @Autowired
 private TarefaRepository tarefaRepository;

 @PostMapping
 @ValidaAcesso({ "ROLE_USER" })
 public ResponseEntity<?> insere(@RequestBody Tarefa tarefa) {
  if (tarefa != null) {
   tarefa = tarefaRepository.save(tarefa);
   return new ResponseEntity<>(tarefa, HttpStatus.OK);
  }
  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
 }

 @GetMapping
 public ResponseEntity<?> todas() {
  List<Tarefa> lt = tarefaRepository.findAll();
  return new ResponseEntity<>(lt, HttpStatus.OK);
 }

}
