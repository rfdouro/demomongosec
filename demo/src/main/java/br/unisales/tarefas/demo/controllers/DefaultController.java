package br.unisales.tarefas.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

public abstract class DefaultController {

 @Autowired
 HttpServletRequest request;
}
