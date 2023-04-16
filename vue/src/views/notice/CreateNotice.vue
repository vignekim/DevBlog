<template>
  <main>
    <div class="container">
      <input name="title" type="text" class="title" placeholder="제목을 입력하세요" autocomplete="off" v-model="notice.title"/>
    </div>
    <div class="container">
      <textarea name="desc" class="desc" cols="30" rows="10" placeholder="설명을 입력하세요" v-model="notice.desc"></textarea>
    </div>
    <div id="editorjs"></div>
    <div class="d-flex">
      <div class="flex-fill">
        <button type="button" class="btn" @click="save">작성</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="cancel">취소</button>
      </div>
    </div>
  </main>
</template>

<script>
import useController from '@/controller/notice/create'
export default useController
</script>

<style lang="scss" scoped>
main {
  min-height: 80vh;

  .d-flex {
    max-width: none;
  }
}
@media (max-width: 1000px) {}
@media (max-width: 650px) {
  main {
    padding: 0 16px;
  }
}
</style>