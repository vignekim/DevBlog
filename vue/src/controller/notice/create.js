import router from '@/router'
import axios from 'axios'
import EditorJS from '@editorjs/editorjs'
import { useWrite } from '@/editor'
import { decode, encode } from '@/util/Base64'

const data = () => {
  const resultData = {}
  resultData.notice = {title: '', desc: '', content: ''}
  return resultData
}

const methods = {
  save() {
    const url = process.env.VUE_APP_BASEURL + '/Notice/save'
    axios.put(url, this.notice)
      .then((res) => {
        router.push({ name: 'DetailNotice', params: {noticeNo: res.data.no} })
      })
      .catch((err) => console.log(err))
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

