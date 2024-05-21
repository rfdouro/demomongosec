package br.unisales.tarefas.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unisales.tarefas.demo.models.domain.Tarefa;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {

}
