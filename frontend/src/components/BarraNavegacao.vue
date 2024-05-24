<template>
 <nav>
  <router-link to="/">Início</router-link> |
  <router-link to="/tarefas">Tarefas</router-link> |
  <router-link to="/usuarios">Usuários</router-link> |
  <router-link to="/sobre">Sobre</router-link>
 </nav>
 {{ logado.login }} | <button @click="desloga">Sair</button>
 <hr />
</template>

<script>
import LoginService from "@/services/LoginService";
import AuthService from "@/services/AuthService";
export default {
 methods: {
  desloga() {
   LoginService.deslogar();
   this.$router.push(`/?d=${new Date()}`);
  },
 },
 computed: {
  logado() {
   return AuthService.dados;
  },
 },
};
</script>

<style scoped>
nav {
 text-align: center;
 padding: 30px;
}

nav a {
 font-weight: bold;
 color: #2c3e50;
}

nav a.router-link-exact-active {
 color: #2b7ac4;
}
</style>