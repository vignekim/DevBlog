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
import EditorJS from '@editorjs/editorjs'
import tools from '@/editor/tools'
export default {
  name: 'CreateNotice',
  data() {
    return {
      notice: {title: '', desc: ''}
    }
  },
  setup() {
    const config = {
      holder: 'editorjs',
      data: {blocks: []},
      tools: tools
    }
    const editor = new EditorJS(config);
    return { editor }
  },
  methods: {
    save() {
      this.$router.push({ name: 'DetailNotice', params: {noticeNo: 1} })
    },
    cancel() {
      this.$router.push({ name: 'home' })
    }
  }
}
</script>

<style lang="scss">
main {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  min-height: 80vh;
  margin: 90px 0 20px;
  padding: 0 calc(10% + 16px);

  .container {
    width: 100%;

    .title {
      width: 80%;
      margin: 10px 10%;
      padding: 10px 0;
      text-align: center;
      font-size: 23px;
      border: none;
      border-radius: 3px;

      &:focus {
        outline: none;
      }
    }
    .desc {
      width: 80%;
      margin: 10px 10%;
      padding: 10px;
      font-size: 18px;
      border: none;
      border-radius: 3px;
      resize: none;

      &:focus {
        outline: none;
      }
    }
  }
  h1 {
    color: #000;
  }
  .displayNone {
    display: none;
  }
  #editorjs {
    margin: 20px 10%;
    width: 80%;
    min-height: 40vh;

    textarea {
      resize: none;
    }
    .cdx-attaches--with-file, .cdx-input, .tc-cell, .tc-row, .tc-table {
      border-color: #1e1e1e !important;
    }
    .ce-block__content {
      max-width: 100% !important;
      padding: 0 20px;
    }
    .ce-toolbar__content {
      max-width: 100% !important;
    }
    .codex-editor__redactor {
      padding-bottom: 0 !important;
      margin-right: 0 !important;
    }
    .image-tool--withBackground {
      .image-tool__image {
        background-color: blanchedalmond !important;
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
@media (max-width: 1000px) {
  main {
    .title, .desc {
      width: calc(100% - 40px);
      margin: 10px 20px;
    }
    #editorjs {
      width: calc(100% - 40px);
      margin: 10px 20px;
    }
  }
}
@media (max-width: 650px) {
  main {
    padding: 0 16px;

    .title, .desc {
      width: calc(100% - 20px);
      margin: 10px;
    }
    #editorjs {
      width: calc(100% - 20px);
      margin: 10px 10px 50px 10px;
    }
  }
}
</style>