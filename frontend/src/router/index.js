import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TarefasView from '../views/TarefasView.vue'
import LoginView from '../views/LoginView.vue'
import NaoAutorizadoView from '../views/NaoAutorizadoView.vue'
import UsuariosView from '../views/usuarios/UsuariosView.vue'
import UsuarioEditView from '../views/usuarios/UsuarioEditView.vue'

import LoginService from '../services/LoginService'
import AuthService from '@/services/AuthService'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/tarefas',
    name: 'tarefas',
    component: TarefasView
  },
  {
    path: '/usuarios',
    name: 'usuarios',
    component: UsuariosView
  },
  {
    path: '/usuarios/:id',
    name: 'usuariosedit',
    component: UsuarioEditView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/nao-autorizado',
    name: 'nao-autorizado',
    component: NaoAutorizadoView
  },
  {
    path: '/sobre',
    name: 'sobre',
    component: () => import('../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from) => {
  LoginService.router = router;

  console.log(`de ${from.name} para ${to.name}`);


  if (!LoginService.estaAutenticado() && to.name != 'login') {
    return { name: 'login' }
  }

  if (!AuthService.validaPermissao(to.path)) {
    router.push('/nao-autorizado')
    return false;
  }

  return true;
})

export default router
