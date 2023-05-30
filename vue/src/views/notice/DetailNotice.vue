<template>
  <main>
    <div class="container">
      <input name="title" type="text" class="title" :class="{displayNone: change.active}" placeholder="제목을 입력하세요" autocomplete="off" v-model="notice.title"/>
      <h1 class="title" :class="{displayNone: !change.active}">{{notice.title}}</h1>
    </div>
    <div class="container">
      <textarea name="desc" class="desc" :class="{displayNone: change.active}" id="" cols="30" rows="10" placeholder="설명을 입력하세요" v-model="notice.desc"></textarea>
      <p class="title" :class="{displayNone: !change.active}">{{notice.desc}}</p>
    </div>
    <div id="editorjs"></div>
    <div class="d-flex">
      <div class="flex-fill">
        <button type="button" class="btn" @click="editorChange" :disabled="change.auth">{{change.txt}}</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="del" :disabled="change.auth">삭제</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="cancel">취소</button>
      </div>
    </div>
  </main>
</template>

<script>
import useController from '@/controller/notice/detail'
export default useController
</script>

<style lang="scss" scoped>
main {
  min-height: 80vh;

  .d-flex {
    max-width: none;

    .flex-fill {
      width: calc(100% / 3);
    }
  }
}
@media (max-width: 1000px) {}
@media (max-width: 650px) {
  main {
    padding: 0 16px;
  }
}
</style>