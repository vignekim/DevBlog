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
        <button type="button" class="btn" @click="editorChange">{{change.txt}}</button>
      </div>
      <div class="flex-fill">
        <button type="button" class="btn" @click="del">삭제</button>
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
import data from '@/editor/data'
export default {
  name: 'CreateNotice',
  data() {
    return {
      notice: {title: '연습용', desc: 'Editor.js를 이용한 Notice 화면입니다.'},
      change: {txt: '수정', active: true}
    }
  },
  setup() {
    const config = {
      holder: 'editorjs',
      data: {blocks: []},
      readOnly: true,
      tools: tools,
      data: data
    }
    const editor = new EditorJS(config);
    return { editor }
  },
  methods: {
    editorChange() {
      console.log(this.editor.readOnly.isEnabled)
      console.log(this.change)
      if(this.editor.readOnly.isEnabled) {
        this.editor.readOnly.toggle()
        this.change = {txt: '저장', active: false}
      } else {
        this.editor.save().then((data) => {
          if(data.blocks.length > 0) {
            this.editor.readOnly.toggle()
            this.change = {txt: '수정', active: true}
          }
        }).catch((error) => console.log(error))
      }
      console.log(this.editor.readOnly.isEnabled)
      console.log(this.change)
    },
    del() {
      this.cancel()
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
    z-index: 1;

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
    margin: 20px 10%;
    width: 80%;

    .flex-fill {
      width: calc(100% / 3);
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
    .d-flex {
      width: calc(100% - 40px);
      margin: 0 20px;
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
    .d-flex {
      width: calc(100% - 40px);
      margin: 0 20px;
    }
  }
}
</style>