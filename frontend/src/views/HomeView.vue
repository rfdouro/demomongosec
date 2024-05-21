<template>
 <div class="home">
  <barra-navegacao></barra-navegacao>
  <HelloWorld msg="Olá"></HelloWorld>
  <button @click="desloga">Sair</button>
 </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import LoginService from "@/services/LoginService";
import BarraNavegacao from '@/components/BarraNavegacao.vue';

export default {
 name: "HomeView",
 components: {
  HelloWorld, BarraNavegacao
 },
 methods: {
  desloga() {
   LoginService.deslogar();
   this.$router.push(`/?d=${new Date()}`);
  },
 },
};
</script>
