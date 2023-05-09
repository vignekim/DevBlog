<template>
  <main>
    <h1 class="text-center">Login</h1>
    <form class="form" @submit.prevent="login">
      <div class="d-left">
        <div class="form-group">
          <label for="email" class="form-label">이메일:</label>
          <input type="email" class="form-control" placeholder="이메일를 입력하세요." name="email" autocomplete="off" v-model="user.email"/>
        </div>
        <div class="form-group">
          <label for="pwd" class="form-label">비밀번호:</label>
          <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pwd" autocomplete="off" v-model="user.pwd"/>
        </div>
      </div>
      <div class="d-right">
        <button type="submit" class="btn">Login</button>
      </div>
    </form>
    <div class="d-flex">
      <div class="flex-fill">
        <button type="button" class="btn" @click="create">회원가입</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="cancel">취소</button>
      </div>
    </div>
  </main>
</template>

<script>
import useController from '@/controller/login'
export default useController
</script>

<style lang="scss" scoped>
main {
  .form {
    flex-direction: row;

    .d-left {
      width: calc(100% - 150px);
      padding: 0 1rem;

      .form-group {
        margin-top: 0.5rem;
        display: flex;
        align-items: center;

        .form-label {
          width: 70px;
        }
        .form-control {
          width: calc(100% - 70px);
        }
      }
    }
    .d-right {
      width: 150px;
      display: flex;
      align-content: center;

      .btn {
        width: calc(100% - 2rem);
        border: none;
        outline: none;
        color: #FFF;
        background: #222938;
        font-size: 1rem;
        padding: 0.5rem 0;
        margin: 0.5rem 1rem 1rem 1rem;
        border-radius: 0.375rem;
        cursor: pointer;

        &:hover {
          background: #000;
          box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.2);
        }
      }
    }
  }
}
</style>