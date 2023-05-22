import router from '@/router'
import axios from 'axios'
import EditorJS from '@editorjs/editorjs'
import { useWrite } from '@/editor'
import { decode, encode } from '@/util/Base64'
import useAxios from '@/util/UseAxios'

const data = () => {
  const resultData = {}
  resultData.notice = {title: '', desc: '', content: ''}
  return resultData
}

const methods = {
  save() {
    const url = process.env.VUE_APP_BASEURL + '/Notice/save'

    this.editor.save().then((data) => {
      if(data.blocks.length > 0) {
        this.notice.content = encode(data)

        useAxios.put(url, this.notice)
        .then((res) => {
          if(res.data.state) {
            router.push({ name: 'DetailNotice', params: {noticeNo: res.data.result.no} })
          }
        })
        .catch((err) => console.log(err))

      }
    }).catch((error) => console.log(error))

  },
  cancel() {
    router.push({ name: 'HomeView' })
  }
}

const useController = {
  name: 'CreateNotice',
  components: {},
  props: {},
  methods,
  data() { return data() },
  setup() {
    const editor = new EditorJS(useWrite);
    return { editor }
  },
  created() {},
  mounted() {},
  unmounted() {}
}

export default useController

