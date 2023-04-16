<template>
  <section v-for="row in list" :key="row.no" @click="detail(row)">
    <img :src="row.img" alt="image">
    <h1>{{row.title}}</h1>
    <div class="footer">
      <h4>{{ dateTime(row.regDate) }}</h4>
      <h4>조회수 <span>{{row.count}}</span></h4>
    </div>
    <p>{{row.desc}}</p>
  </section>
</template>

<script>
import dayjs from 'dayjs'
export default {
  name: 'HomeList',
  props: {list: []},
  methods: {
    detail(row) {
      const param = {noticeNo: row.no}
      this.$router.push({ name: 'DetailNotice', params: param })
    },
    dateTime(value) {
      return dayjs(value).format('YYYY-MM-DD')
    }
  }
}
</script>

<style lang="scss" scoped>
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
    max-height: 35px;
    overflow: hidden;
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
@media (max-width: 1300px) {
  section {
    width: calc((100% / 4) - 20px);
  }
}

@media (max-width: 1000px) {
  section {
    width: calc((100% / 3) - 20px);
  }
}

@media (max-width: 650px) {
  section {
    width: calc((100% / 2) - 20px);
  }
}
</style>