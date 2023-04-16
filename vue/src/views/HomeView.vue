<template>
  <main>
    <div class="btn-group" v-if="account">
      <button type="button" class="btn" @click="notice">글작성</button>
    </div>
    <div class="list" v-if="state == 1">
      <HomeList :list="list"/>
    </div>
    <div class="block" v-if="state == 2">
      <h1>목록이 없습니다.</h1>
    </div>
  </main>
</template>

<script>
import useController from '@/controller/home'
export default useController
</script>

<style lang="scss" scoped>
main {
  justify-content: flex-start;
  align-items: flex-start;
  min-height: 40vh;

  .btn-group {
    width: calc(100% - 20px);
    margin: 0 10px;

    .btn {
      width: 100px;
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
  .block {
    display: flex;
    width: 100%;
    min-height: 40vh;
    justify-content: center;
    align-items: center;
  }
  .list {
    display: flex;
    width: 100%;
    min-height: 40vh;
  }
}
@media (max-width: 1000px) {
  main {
    padding: 0 21px;
  }
}
@media (max-width: 650px) {
  main {
    padding: 0 16px;

    .btn-group {
      .btn {
        width: 100%;
      }
    }
  }
}
</style>

