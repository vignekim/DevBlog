<template>
  <div class="popup" :class="{active: show == 1, nonActive: show == 2}">
    <div id="box" class="body-flex">
      <h1><span @click="info">{{user.name}}</span>님 안녕하세요</h1>
      <h2>멋진 하루 입니다.</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PopupView',
  props: {
    show: Number,
    user: Object
  },
  methods: {
    info() {
      this.$emit('active')
      this.$router.push({ name: 'SelectView', params: {userNo: 1} })
    }
  }
}
</script>

<style lang="scss" scoped>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 0;
  background-color: #FFF;
  z-index: 1;
  overflow: hidden;

  #box {
    height: 100vh;
  }
  .body-flex {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    span {
      cursor: pointer;
      color: #6FB232;
    }
  }
}
.active {
  animation-name: slide-in;
  animation-duration: 2s;
  animation-fill-mode: both;
}
.nonActive {
  animation-name: slide-out;
  animation-duration: 2s;
  animation-fill-mode: both;
}
@keyframes slide-in {
  from {height: 0vh; opacity: 0.3;}
  to {height: 100vh; opacity: 1;}
}
@keyframes slide-out {
  to {height: 0vh; opacity: 0.3;}
  from {height: 100vh; opacity: 1;}
}
</style>