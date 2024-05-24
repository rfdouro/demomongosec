<template>
 <barra-navegacao></barra-navegacao>
 <h1>Usuários</h1>

 <fieldset>
  <legend>Cadastro</legend>
  <p><label>Login</label><input type="text" v-model="user.login" /></p>
  <p><label>Senha</label><input type="password" v-model="user.senha" /></p>
  <p><button @click="salvar">Salvar</button></p>
 </fieldset>

 <table style="width: 100%" v-cloak>
  <thead>
   <tr>
    <th style="width: 15%">Id</th>
    <th style="width: 50%">Login</th>
    <th>Permissões</th>
   </tr>
  </thead>
  <tbody>
   <tr v-for="(u, i) in usuarios" v-bind:key="i" @click="abreEdit(u.id)">
    <td>{{ u.id }}</td>
    <td>{{ u.login }}</td>
    <td>{{ u.permissoes.length }}</td>
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
   this.user.permissoes = ["ROLE_USER"];
   console.log(JSON.stringify(this.user));
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
  abreEdit(id) {
   this.$router.push(`/usuarios/${id}`);
  },
 },
};
</script>

<style scoped>
table * {
 border: solid 1px black;
}

table tbody tr {
 cursor: pointer;
}

fieldset {
 border: solid 1px blue;
}

fieldset input[type="text"] {
 border: solid 1px blue;
}
fieldset input[type="password"] {
 border: solid 1px blue;
}
fieldset button {
 border: solid 1px blue;
}
</style>