<template>
 <nav>
  <router-link to="/">Início</router-link> |
  <router-link to="/tarefas">Tarefas</router-link> |
  <router-link to="/usuarios">Usuários</router-link> |
  <router-link to="/sobre">Sobre</router-link>
 </nav>
 {{ logado }}
</template>

<script>
import AuthService from "@/services/AuthService";
export default {
 computed: {
  logado() {
   return AuthService.dados.login;
  },
 },
};
</script>

<style>
</style>