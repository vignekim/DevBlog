import EditorJS from '@editorjs/editorjs'
import Paragraph from '@editorjs/paragraph'
import Header from '@editorjs/header'
import NestedList from '@editorjs/nested-list';
import Table from '@editorjs/table'
import Attaches from '@editorjs/attaches'
import Image from '@editorjs/image'
import Checklist from '@editorjs/checklist'
import Quote from '@editorjs/quote'
import Marker from '@editorjs/marker'
import CodeTool from '@editorjs/code'
import InlineCode from '@editorjs/inline-code'
import Delimiter from '@editorjs/delimiter'
import axios from 'axios'

const fileUpload = (file) => {
  const formData = new FormData()
  formData.append("file", file)
  return new Promise((resolve, reject) => {
      axios.post(process.env.VUE_APP_BASEURL + '/FileUpload/Editor', formData)
      .then((result) => resolve(result.data))
      .catch((error) => reject(error.data))
  })
}

const tools = {
  paragraph: {
    class: Paragraph,
    config: {placeholder: '내용을 입력하세요.'},
    inlineToolbar: true
  },
  header: {
    class: Header,
    inlineToolbar: ['marker', 'link'],
    config: {placeholder: '제목을 입력하세요.'},
    shortcut: 'CMD+SHIFT+H'
  },
  list: {
    class: NestedList,
    inlineToolbar: true,
    config: {defaultStyle: 'unordered'},
    shortcut: 'CMD+SHIFT+L'
  },
  table: {
    class: Table,
    inlineToolbar: true,
    shortcut: 'CMD+ALT+T'
  },
  attaches: {
    class: Attaches,
    config: {
      uploader: {
        uploadByFile(file) {
          return fileUpload(file)
        }
      }
    }
  },
  image: {
    class: Image,
    config: {
      uploader: {
          uploadByFile(file){
            return fileUpload(file)
          }
      },
      field: 'image',
      types: 'image/*'
    }
  },
  checklist: {
    class: Checklist,
    inlineToolbar: true,
  },
  quote: {
    class: Quote,
    inlineToolbar: true,
    config: {
      quotePlaceholder: 'Enter a quote',
      captionPlaceholder: 'Quote\'s author',
    },
    shortcut: 'CMD+SHIFT+O'
  },
  marker: {
    class:  Marker,
    shortcut: 'CMD+SHIFT+M'
  },
  code: {
    class:  CodeTool,
    shortcut: 'CMD+SHIFT+C'
  },
  inlineCode: {
    class: InlineCode,
    shortcut: 'CMD+SHIFT+C'
  },
  delimiter: Delimiter
}

export const useRead = {
  holder: 'editorjs',
  readOnly: true,
  tools: tools
}

export const useWrite = {
  holder: 'editorjs',
  readOnly: false,
  tools: tools
}

