<template>
  <div>
    <PopupView :show="show" :user="user" @click="active"/>
    <header>
      <div class="nav nav-left">
        <img src="/account.svg" alt="logo" @click="active()" class="logo" v-if="account">
      </div>
      <div class="nav">
        <a href="/">
          <h1>DEV<span>BLOG</span></h1>
        </a>
      </div>
      <div class="nav nav-right" v-if="account">
        <h2 @click="info">{{user.name}}</h2>
        <img src="/logout.svg" alt="logout" class="img" @click="logout">
      </div>
      <div class="nav nav-right" v-else>
        <img src="/login.svg" alt="login" class="img" @click="login">
      </div>
    </header>
    <router-view :class="{breakView: show == 1}"/>
    <FooterView/>
  </div>
</template>

<script>
import PopupView from '@/components/PopupView.vue'
import FooterView from '@/components/FooterView.vue'
import { decode } from '@/util/Base64'
export default {
  name: 'App',
  components: {
    PopupView, FooterView
  },
  data() {
    return {
      show: 0,
      account: false,
      user: ''
    }
  },
  created() {
    const user = localStorage.getItem('user');
    if(user) {
      this.user = decode(user)
      this.account = true
    }
  },
  methods: {
    login() {
      this.$router.push({ name: 'LoginView'})
    },
    logout() {
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      window.location.href = '/'
    },
    info() {
      this.$router.push({ name: 'SelectView', params: {userNo: this.user.no} })
    },
    active() {
      if(this.show === 2 || this.show === 0) this.show = 1
      else this.show = 2
    }
  }
}
</script>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Fira Sans', sans-serif;
  &::selection {
    background: transparentize(#42B883, 0.5);
  }
}
body {
  background-color: blanchedalmond;

  a {
    text-decoration: none;
  }
  header {
    position: fixed;
    top: 10px;
    left: 10%;
    right: 10%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    border-radius: 10px;
    padding: 10px 16px;
    margin: 10px 16px;
    background-color: #37251f;
    box-shadow: 0px 0px 6px rgba(0, 0, 0, 0.1);
    z-index: 2;

    .nav {
      width: calc(100% / 3);

      .logo {
        display: none;
        background-color: #6FB232;
        width: 28px;
        height: 28px;
        border-radius: 14px;
      }
      h1 {
        color: #FFF;
        font-size: 28px;
        text-align: center;

        span {
          color: #6FB232;
        }
      }
      h2 {
        color: #FFF;
        font-size: 23px;
        display: inline-block;
      }
      img {
        width: 28px;
        height: 28px;
        margin: 0 10px;
        border-radius: 4px;
        cursor: pointer;
      }
      .img {
        background-color: #6FB232;
      }
    }
    .nav-left {
      display: flex;
      justify-content: left;
    }
    .nav-right {
      text-align: right;
      display: flex;
      flex-flow: row wrap;
      justify-content: right;
      align-items: center;

      h2 {
        cursor: pointer;
      }
    }
  }
  .breakView {
    position: fixed !important;
    overflow: hidden;
  }
  main {
    position: relative;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-content: center;
    min-height: 60vh;
    margin: 90px 0 20px;
    padding: 0 calc(10% + 16px);

    .text-center {
      text-align: center;
      width: 100%;
    }
    .displayNone {
      display: none;
    }
    .user-image {
      text-align: center;
      width: 100%;

      .user {
        width: 150px;
        height: 150px;
        border-radius: 75px;
        background-color: #6FB232;
        cursor: pointer;

        &:hover {
          box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.2);
        }
      }
    }
    .form {
      display: flex;
      flex-direction: column;
      width: 100%;
      max-width: 1000px;

      .form-group {
        margin: 1rem 0;
        width: 100%;

        .form-label {
          display: block;
          font-weight: bold;
          margin-bottom: 0.5rem;
        }
        input {
          display: block;
          appearance: none;
          border: none;
          outline: none;
          background: none;
          color: #212529;
        }
        .form-control {
          width: 100%;
          color: #000;
          font-size: 20px;
          background-color: #FFF;
          padding: 10px;
          border-radius: 0.375rem;
          margin-bottom: 15px;
          transition: 0.4s;
          &::placeholder {
            color: #a6acb0;
          }
          &:focus {
            box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.2);
          }
        }
      }
    }
    .container {
      width: 100%;

      .title {
        width: 100%;
        margin: 10px 0;
        padding: 10px 0;
        text-align: center;
        font-size: 23px;
        color: #000;
        border: none;
        border-radius: 3px;

        &:focus {
          outline: none;
        }
      }
      .desc {
        width: 100%;
        margin: 10px 0;
        padding: 10px;
        font-size: 18px;
        border: none;
        border-radius: 3px;
        resize: none;

        &:focus {
          outline: none;
        }
      }
    }
    #editorjs {
      width: 100%;
      min-height: 40vh;
      z-index: 1;

      textarea {
        resize: none;
      }
      .cdx-attaches--with-file, .cdx-input, .tc-cell, .tc-row, .tc-table {
        border-color: #1e1e1e !important;
      }
      .ce-block__content {
        max-width: 100% !important;
        padding: 0 20px;
      }
      .ce-toolbar__content {
        max-width: 100% !important;
      }
      .codex-editor__redactor {
        padding-bottom: 0 !important;
        margin-right: 0 !important;
      }
      .image-tool--withBackground {
        .image-tool__image {
          background-color: blanchedalmond !important;
        }
      }
    }
    .d-flex {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-evenly;
      width: 100%;
      max-width: 1000px;

      .flex-fill {
        width: calc(100% / 2);
        padding: 0.5rem;

        .btn {
          width: 100%;
          border: none;
          outline: none;
          color: #FFF;
          background: #0d6efd;
          font-size: 1rem;
          padding: 0.5rem 0;
          border-radius: 0.375rem;
          cursor: pointer;

          &:hover {
            background: #1062de;
            box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.2);
          }
        }
      }
    }
  }
  @media (max-width: 1000px) {
    header {
      left: 15px;
      right: 15px;
    }
    main {
      padding: 0 32px;
    }
  }
  @media (max-width: 650px) {
    header {
      left: 10px;
      right: 10px;

      .logo {
        display: inline-block !important;
      }
      .nav:nth-child(1) {
        width: 20%;
      }
      .nav:nth-child(2) {
        width: 60%;
      }
      .nav:nth-child(3) {
        width: 20%;

        h2 {
          display: none;
        }
      }
    }
    main {
      padding: 0 26px;
    }
  }
}
</style>
