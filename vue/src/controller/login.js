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
        //console.log(res)

        if(res.data.state) {
          const user = {
            name: res.data.result.name,
            no: res.data.result.no
          }
          localStorage.setItem('user', encode(user))
          localStorage.setItem('token', res.data.result.token)
          window.location.href = '/'
        } else {
          alert("사용자의 정보가 존재하지 않습니다.");
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
