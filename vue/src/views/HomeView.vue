<template>
  <main>
    <div class="btn-group" v-if="account">
      <button type="button" class="btn" @click="notice">글작성</button>
    </div>
    <section v-for="row in list" :key="row" @click="detail(row)">
      <img src="/account.svg" alt="image">
      <h1>연습용{{row}}</h1>
      <div class="footer">
        <h4>2023-04-15</h4>
        <h4>조회수 <span>1</span></h4>
      </div>
      <p>스타일리시한 세 가지 케이스 색상. 건강과 피트니스에 대한 통찰을 제공하는 강력한 센서. 혁신적인 안전 기능. 끊김 없이 소통을 유지하는 편리한 방법. 여기에 한층 향상된 성능을 위한 더 빠른 듀얼 코어 프로세서까지. 수많은 기능들로 가득 찬 Apple Watch SE, 그 어느 때보다도 탁월한 가성비를 자랑합니다.</p>
    </section>
  </main>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {
      account: false,
      list: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
    }
  },
  created() {
    const user = localStorage.getItem('user');
    if(user) {
      this.account = true
    }
  },
  methods: {
    notice() {
      this.$router.push({ name: 'CreateNotice' })
    },
    detail(row) {
      this.$router.push({ name: 'DetailNotice', params: {noticeNo: row} })
    },
  }
}
</script>

<style lang="scss" scoped>
main {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 100%;
  min-height: 60vh;
  margin: 90px 0 20px;
  padding: 0 calc(10% + 6px);

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
  section {
    width: calc((100% / 5) - 20px);
    margin: 10px;
    border-radius: 0px 0px 16px 16px;
    transition-duration: 0.7s;
    cursor: pointer;

    img {
      width: 100%;
      height: 150px;
      background-color: #6FB232;
    }
    h1 {
      padding: 5px 10px;
    }
    .footer {
      display: flex;
      margin: 0 10px;
      align-items: center;
      justify-content: space-between;

      h4 {
        font-size: 0.9rem;
        font-weight: normal;

        span {
          color: #6FB232;
          font-weight: bold;
        }
      }
    }
    p {
      min-height: 64px;
      font-size: 15px;
      padding: 5px 10px;
      max-height: calc((15px * 3) + 18px);
      overflow: hidden;
      margin-bottom: 20px;
    }
    &:hover {
      box-shadow: 5px 5px 5px;
      transition-duration: 0.7s;
    }
  }
}
@media (max-width: 1300px) {
  main {
    section {
      width: calc((100% / 4) - 20px);
    }
  }
}

@media (max-width: 1000px) {
  main {
    padding: 0 21px;

    section {
      width: calc((100% / 3) - 20px);
    }
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

    section {
      width: calc((100% / 2) - 20px);
    }
  }
}
</style>
