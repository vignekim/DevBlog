import router from '@/router'
import axios from 'axios'
import EditorJS from '@editorjs/editorjs'
import { useRead } from '@/editor'
import { decode, encode } from '@/util/Base64'

const data = () => {
  const resultData = {}
  resultData.notice = {title: '', desc: ''}
  resultData.change = {txt: '수정', active: true},
  resultData.editor = {}
  return resultData
}

const database = (noticeNo) => {
  return new Promise((resolve, reject) => {
    const url = process.env.VUE_APP_BASEURL + '/Notice/findById'
    const params = {no: noticeNo}
    axios.post(url, params)
      .then((data) => resolve(data))
      .catch((err) => reject(err))
  })
}

const methods = {
  editorChange() {
    if(this.editor.readOnly.isEnabled) {
      this.editor.readOnly.toggle()
      this.change = {txt: '저장', active: false}
    } else {
      this.editor.save().then((data) => {
        if(data.blocks.length > 0) {
          this.editorJson = encode(data)
          this.editor.readOnly.toggle()
          this.change = {txt: '수정', active: true}
        }
      }).catch((error) => console.log(error))
    }
  },
  del() {
    this.cancel()
  },
  cancel() {
    router.push({ name: 'HomeView' })
  }
}

const useController = {
  name: 'DetailNotice',
  components: {},
  props: {},
  methods,
  data() { return data() },
  setup() {},
  created() {
    database(this.$route.params.noticeNo)
      .then((result) => {
        if(result.data.state) {
          this.notice = result.data
          useRead.data = decode(result.data.content)
          this.editor = new EditorJS(useRead)
        }
        else this.cancel()
      })
      .catch((err) => this.cancel())
  },
  mounted() {},
  unmounted() {}
}

export default useController
