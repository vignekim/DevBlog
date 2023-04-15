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
        <h2 @click="info">{{user}}</h2>
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
      this.user = user
      this.account = true
    }
  },
  methods: {
    login() {
      this.$router.push({ name: 'LoginView'})
    },
    logout() {
      localStorage.removeItem('user');
      window.location.href = '/'
    },
    info() {
      this.$router.push({ name: 'SelectView', params: {userNo: 1} })
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
  @media (max-width: 1000px) {
    header {
      left: 15px;
      right: 15px;
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
  }
}
</style>
