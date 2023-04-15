<template>
  <main>
    <h1 class="text-center">회원가입</h1>
    <form>
      <div class="form-group">
        <label for="name" class="form-label">이름:</label>
        <input type="text" class="form-control" placeholder="이름을 입력하세요." name="name" required/>
      </div>
      <div class="form-group">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" placeholder="이메일를 입력하세요." name="email" required/>
      </div>
      <div class="form-group">
        <label for="pwd" class="form-label">비밀번호:</label>
        <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pwd" required/>
      </div>
    </form>
    <div class="d-flex">
      <div class="flex-fill">
        <button type="button" class="btn" @click="save">가입</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="cancel">취소</button>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: 'CreateView',
  methods: {
    save() {
      this.$router.push({ name: 'SelectView', params: {userNo: 1} })
    },
    cancel() {
      this.$router.push({ name: 'home' })
    }
  }
}
</script>

<style lang="scss" scoped>
main {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
  margin: 90px 0 20px;
  padding: 0 calc(10% + 16px);

  .text-center {
    text-align: center;
    width: 100%;
  }
  form {
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
  .d-flex {
    display: flex;
    flex-wrap: wrap;
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
@media (max-width: 650px) {
  main {
    padding: 0 26px;
  }
}
</style>