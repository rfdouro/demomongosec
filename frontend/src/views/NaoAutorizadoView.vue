<template>
 <h1>Não autorizado</h1>
 <button @click="retornarInicio">Início</button>
</template>

<script>
export default {
 methods: {
  retornarInicio() {
   this.$router.push("/");
  },
 },
};
</script>

<style>
</style>