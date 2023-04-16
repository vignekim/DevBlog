import router from '@/router'
import axios from 'axios'
import { encode } from '@/util/Base64'

const data = () => {
  const resultData = {}
  resultData.user = {
    email: '',
    pwd: ''
  }
  return resultData
}

const methods = {
  login() {
    const url = process.env.VUE_APP_BASEURL + '/User/Login'
    axios.post(url, this.user)
      .then((res) => {
        if(res.data.state) {
          const user = {
            name: res.data.user.name,
            no: res.data.user.no
          }
          localStorage.setItem('user', encode(user));
          window.location.href = '/'
        }
      })
      .catch((err) => console.log(err))
  },
  create() {
    router.push({ name: 'CreateView'})
  },
  cancel() {
    router.push({ name: 'HomeView' })
  }
}

const useController = {
  name: 'LoginView',
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
