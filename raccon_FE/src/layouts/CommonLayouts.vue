<template>
  <q-layout view="hhh lpr fff" style="" class="bg-teal-2">
    <q-header
      bordered
      class="bg-white text-grey-9"
      style="max-width: 800px; margin: 0 auto"
    >
      <q-toolbar>
        <q-btn flat dense to="/home">
          <q-avatar rounded class="q-ma-sm q-mr-md">
            <img src="/images/logo.png" />
          </q-avatar>
          <q-toolbar-title class="text-weight-bolder h1">
            Racconworld
          </q-toolbar-title>
        </q-btn>
      </q-toolbar>
      <q-separator color="gray"></q-separator>
    </q-header>

    <q-page-container class="bg-light-blue-1">
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup></script>
