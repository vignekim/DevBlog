<template>
  <main>
    <h1 class="text-center">Login</h1>
    <form @submit.prevent="login">
      <div class="d-left">
        <div class="form-group">
          <label for="email" class="form-label">이메일:</label>
          <input type="email" class="form-control" placeholder="이메일를 입력하세요." name="email" readonly="readonly"/>
        </div>
        <div class="form-group">
          <label for="pwd" class="form-label">비밀번호:</label>
          <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." name="pwd" readonly="readonly"/>
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
export default {
  name: 'UserView',
  methods: {
    login() {
      localStorage.setItem('user', '폴더');
      window.location.href = '/'
    },
    create() {
      this.$router.push({ name: 'CreateView'})
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
  justify-content: center;
  align-content: center;
  flex-wrap: wrap;
  min-height: 60vh;
  margin: 90px 0 20px;
  padding: 0 calc(10% + 16px);

  .text-center {
    text-align: center;
    width: 100%;
  }
  form {
    width: 100%;
    display: flex;
    max-width: 1000px;

    .d-left {
      width: calc(100% - 150px);
      padding: 0 1rem;

      .form-group {
        margin-top: 0.5rem;
        display: flex;
        align-items: center;

        .form-label {
          font-weight: bold;
          margin-bottom: 0.5rem;
          width: 70px;
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
          width: calc(100% - 70px);
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
  .d-flex {
    width: 100%;
    max-width: 1000px;
    display: flex;
    flex-wrap: wrap;

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