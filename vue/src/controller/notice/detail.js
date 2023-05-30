import router from '@/router'
import axios from 'axios'
import EditorJS from '@editorjs/editorjs'
import { useRead } from '@/editor'
import { decode, encode } from '@/util/Base64'
import useAxios from '@/util/UseAxios'

const data = () => {
  const resultData = {}
  resultData.notice = {title: '', desc: ''}
  resultData.change = {txt: '수정', active: true, auth: true},
  resultData.editor = {}
  return resultData
}

const database = (noticeNo) => {
  return new Promise((resolve, reject) => {
    const url = process.env.VUE_APP_BASEURL + '/Notice/findById'
    const params = {no: noticeNo}
    axios.post(url, params)
      .then((res) => resolve(res.data))
      .catch((err) => reject(err))
  })
}

const methods = {
  editorChange() {
    if(this.editor.readOnly.isEnabled) {
      this.editor.readOnly.toggle()
      this.change = {txt: '저장', active: false}
    } else {
      const url = process.env.VUE_APP_BASEURL + '/Notice/editById'
      this.editor.save().then((data) => {
        if(data.blocks.length > 0) {

          this.notice.content = encode(data)
          axios.post(url, this.notice)
          .then((res) => {
            if(res.data.state) {
              //this.editorJson = encode(data)
              this.editor.readOnly.toggle()
              this.change = {txt: '수정', active: true}
            }
          })
          .catch((err) => console.log(err))

        }
      }).catch((error) => console.log(error))
    }
  },
  del() {
    const url = process.env.VUE_APP_BASEURL + '/Notice/deleteById'
    const params = {no: this.notice.no}
    axios.delete(url, {params})
      .then((res) => {
        if(res.data.state) {
          this.cancel()
        }
      })
      .catch((err) => console.log(err))
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
      .then((res) => {
        if(res.state) {
          this.change.auth = false
          this.notice = res.result
          useRead.data = decode(res.result.content)
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
