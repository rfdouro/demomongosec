<template>
 <barra-navegacao></barra-navegacao>
 <h1>Usuários</h1>

 <fieldset>
  <legend>Cadastro</legend>
  <p><label>Login</label><input type="text" v-model="user.login" /></p>
  <p><label>Senha</label><input type="password" v-model="user.senha" /></p>
  <p><button @click="salvar">Salvar</button></p>
 </fieldset>

 <table style="width: 100%">
  <thead>
   <tr>
    <th style="width: 15%">Id</th>
    <th>Login</th>
   </tr>
  </thead>
  <tbody>
   <tr v-for="(u, i) in usuarios" v-bind:key="i">
    <td>{{ u.id }}</td>
    <td>{{ u.login }}</td>
   </tr>
  </tbody>
 </table>
</template>

<script>
import BarraNavegacao from "@/components/BarraNavegacao.vue";
import AuthService from "@/services/AuthService";

export default {
 components: {
  BarraNavegacao,
 },
 data() {
  return {
   usuarios: [],
   user: {
    login: null,
    senha: null,
   },
  };
 },
 mounted() {
  console.log(AuthService.dados.token);
  this.getUsuarios();
 },
 methods: {
  async getUsuarios() {
   try {
    let r = await fetch("http://localhost:8080/users", {
     method: "GET",
     headers: { Authorization: `Bearer ${AuthService.dados.token}` },
    });
    r.json().then((j) => {
     this.usuarios = j;
    });
   } catch (ex) {
    console.log("ERRO", ex);
   }
  },
  async salvar() {
   if (this.user.login != null && this.user.senha != null) {
    fetch("http://localhost:8080/users", {
     method: "POST",
     body: JSON.stringify(this.user),
     headers: {
      Authorization: `Bearer ${AuthService.dados.token}`,
      "Content-Type": "application/json",
     },
    })
     .then((r) => {
      if (r.status != 200 || r.status != 201) {
       r.json().then((j) => {
        console.log("ERRO", j);
       });
      }
      this.getUsuarios();
     })
     .catch((e) => {
      console.log("ERRO", e);
     });
   }
  },
 },
};
</script>

<style scoped>
table * {
 border: solid 1px black;
}
</style>