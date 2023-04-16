<template>
  <main>
    <div class="user-image">
      <img :src="user.img" alt="사용자" class="user" @click="imgeChange">
    </div>
    <form class="form">
      <div class="form-group">
        <label for="name" class="form-label">이름:</label>
        <input type="text" class="form-control" placeholder="이름을 입력하세요." name="name" readonly="readonly" :value="user.name"/>
      </div>
      <div class="form-group">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" placeholder="이메일를 입력하세요." name="email" readonly="readonly" :value="user.email"/>
      </div>
      <div class="form-group">
        <label for="pwd" class="form-label">비밀번호:</label>
        <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pwd" required :value="user.pwd"/>
      </div>
    </form>
    <div class="d-flex">
      <div class="flex-fill">
        <button type="button" class="btn" @click="save">저장</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="del">탈퇴</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="cancel">취소</button>
      </div>
    </div>
  </main>
</template>

<script>
import useController from '@/controller/user/edit'
export default useController
</script>

<style lang="scss" scoped>
main {
  .d-flex {
    .flex-fill {
      width: calc(100% / 3);
    }
  }
}
</style>