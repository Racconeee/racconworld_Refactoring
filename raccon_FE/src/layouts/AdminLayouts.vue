<template>
  <q-layout
    view="hhh lpr fff"
    container
    style="height: 100vh"
    class="shadow-2 rounded-borders"
  >
    <q-header elevated :class="$q.dark.isActive ? 'bg-primary' : 'bg-black'">
      <q-toolbar class="bg-teal-6">
        <q-btn flat @click="drawer = !drawer" round dense icon="menu" />
        <q-toolbar-title>관리자 페이지</q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="drawer"
      class="bg-cyan-1"
      show-if-above
      :width="200"
      :breakpoint="500"
    >
      <q-scroll-area class="fit">
        <q-list padding class="menu-list">
          <q-item class="bg-indigo-1" clickable v-ripple to="/admin/home">
            <q-item-section avatar>
              <q-icon name="home" />
            </q-item-section>
            <q-item-section> Main </q-item-section>
          </q-item>

          <q-item class="bg-teal-12" active clickable v-ripple to="/admin/info">
            <q-item-section avatar>
              <q-icon name="app_registration" />
            </q-item-section>
            <q-item-section> Info </q-item-section>
          </q-item>

          <q-item
            class="bg-teal-12"
            active
            clickable
            v-ripple
            to="/admin/create"
          >
            <q-item-section avatar>
              <q-icon name="app_registration" />
            </q-item-section>
            <q-item-section> Create </q-item-section>
          </q-item>

          <q-item class="bg-cyan-3" clickable v-ripple to="/admin/traffic">
            <q-item-section avatar>
              <q-icon name="bolt" />
            </q-item-section>
            <q-item-section>Ttraffic </q-item-section>
          </q-item>

          <q-item class="bg-light-green-2" clickable v-ripple to="/home">
            <q-item-section avatar>
              <q-icon name="link" />
            </q-item-section>
            <q-item-section> Home 이동 </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <q-page padding>
        <router-view />
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref } from "vue";

const drawer = ref(false);
</script>

<style lang="sass" scoped></style>
