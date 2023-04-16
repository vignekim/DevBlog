<template>
  <main>
    <h1>페이지를 찾을 수 없습니다.</h1>
  </main>
</template>

<style lang="scss" scoped>
main {
  min-height: 80vh;

  h1 {
    font-size: 38px;
  }
}
@media (max-width: 1000px) {
  main {
    h1 {
      font-size: 28px;
    }
  }
}
@media (max-width: 650px) {
  main {
    h1 {
      font-size: 21px;
    }
  }
}
</style>