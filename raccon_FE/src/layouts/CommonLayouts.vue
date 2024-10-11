<template>
  <q-layout view="hhh lpr fff" style="" class="bg-teal-2">
    <q-header bordered class="bg-teal-2 text-grey-9 no-border">
      <q-toolbar>
        <q-btn flat dense to="/home">
          <q-avatar class="q-ma-sm q-mr-md">
            <img src="/images/logo.png" />
          </q-avatar>
          <q-toolbar-title class="text-weight-bolder h1">
            피카츄
          </q-toolbar-title>
        </q-btn>
      </q-toolbar>
    </q-header>

    <q-page-container class="bg-teal-2">
      <q-separator color="red" inset></q-separator>

      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup></script>
