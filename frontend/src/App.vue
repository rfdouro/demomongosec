<template>
 <router-view />
</template>

<style>
#app {
 font-family: Avenir, Helvetica, Arial, sans-serif;
 -webkit-font-smoothing: antialiased;
 -moz-osx-font-smoothing: grayscale;
 text-align: justify;
 color: #2c3e50;
 padding: 55px;
}

[v-cloak] {
 display: block;
 padding: 50px 0;
}

@keyframes spinner {
 to {
  transform: rotate(360deg);
 }
}

[v-cloak]:before {
 content: "";
 box-sizing: border-box;
 position: absolute;
 top: 50%;
 left: 50%;
 width: 20px;
 height: 20px;
 margin-top: -10px;
 margin-left: -10px;
 border-radius: 50%;
 border: 2px solid #ccc;
 border-top-color: #333;
 animation: spinner 0.6s linear infinite;
 text-indent: 100%;
 white-space: nowrap;
 overflow: hidden;
}

[v-cloak] > * {
 display: none;
}
</style>
