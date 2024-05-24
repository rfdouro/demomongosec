<template>
 <barra-navegacao></barra-navegacao>
 <h1>Usuário</h1>
 <fieldset v-if="usuario != null">
  <legend>Dados do usuário</legend>
  <p>Login: {{ usuario.login }}</p>
  <p>Permissões:</p>
  <textarea v-model="usuario.permissoes"></textarea>
  <hr />
  <button @click="atualizaUsuario">Atualiza</button> | <button @click="excluiUsuario">Exclui</button>
 </fieldset>
</template>

<script>
import BarraNavegacao from "@/components/BarraNavegacao.vue";
import AuthService from "@/services/AuthService";
//import UtilService from "@/services/UtilService";

import axios from "axios";

export default {
 components: {
  BarraNavegacao,
 },
 data() {
  return {
   usuario: null,
  };
 },
 mounted() {
  this.getUsuario(this.$route.params.id);
 },
 methods: {
  getUsuario(id) {
   axios({
    method: "GET",
    url: `http://localhost:8080/users/${id}`,
   }).then((r) => {
    this.usuario = r.data;
   });
  },
  atualizaUsuario() {
   if (typeof this.usuario.permissoes == "string") {
    this.usuario.permissoes = this.usuario.permissoes.split(",");
   }
   axios({
    method: "PUT",
    url: `http://localhost:8080/users/${this.usuario.id}`,
    headers: {
     Authorization: `Bearer ${AuthService.dados.token}`,
     "Content-Type": "application/json",
    },
    data: JSON.stringify(this.usuario),
   }).then((r) => {
    console.log(r);
    this.$router.push("/usuarios")
   });
  },
  excluiUsuario() {
   axios({
    method: "DELETE",
    url: `http://localhost:8080/users/${this.usuario.id}`,
    headers: {
     Authorization: `Bearer ${AuthService.dados.token}`,
     "Content-Type": "application/json",
    },
   }).then((r) => {
    console.log(r);
    this.$router.push("/usuarios")
   });
  },
 },
};
</script>

<style>
</style>