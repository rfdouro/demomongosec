<template>
 <div>
  <fieldset>
   <legend>Autenticação</legend>
   <p><label>Usuário: </label><input type="text" v-model="login" /></p>
   <p><label>Senha: </label><input type="password" v-model="senha" /></p>
   <p><button @click="logar">Logar</button></p>
  </fieldset>
 </div>
</template>

<script>
import LoginService from "@/services/LoginService";

export default {
 data() {
  return {
   login: "",
   senha: "",
  };
 },
 mounted() {
  
 },
 methods: {
  logar() {
   LoginService.logar(this.login, this.senha);
  },
 },
};
</script>

<style>
</style>