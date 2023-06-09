import router from '@/router'
import axios from 'axios'
import { encode } from '@/util/Base64'

const data = () => {
  const resultData = {}
  resultData.user = {
    name: '',
    email: '',
    pwd: ''
  }
  return resultData
}

const methods = {
  save() {
    const url = process.env.VUE_APP_BASEURL + '/User/save'
    axios.put(url, this.user)
      .then((res) => {
        if(res.data.state) {
          const user = {
            name: res.data.result.name,
            no: res.data.result.no
          }
          localStorage.setItem('user', encode(user));
          localStorage.setItem('token', res.data.result.token)
          window.location.href = '/'
        } else {
          alert(res.data.message)
        }
      })
      .catch((err) => console.log(err))
  },
  cancel() {
    router.push({ name: 'HomeView' })
  }
}

const useController = {
  name: 'CreateView',
  components: {},
  props: {},
  methods,
  data() { return data() },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {}
}

export default useController
