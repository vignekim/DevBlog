import router from '@/router'
import axios from 'axios'
import { decode } from '@/util/Base64'

const data = () => {
  const resultData = {}
  resultData.user = {
    name: '',
    email: '',
    pwd: '',
    img: '/account.svg'
  }
  return resultData
}

const methods = {
  edit() {
    router.push({ name: 'EditView', params: {userNo: this.user.no} })
  },
  cancel() {
    router.push({ name: 'HomeView' })
  }
}

const useController = {
  name: 'SelectView',
  components: {},
  props: {},
  methods,
  data() { return data() },
  setup() {},
  created() {
    const user = localStorage.getItem('user');
    if(user) {
      this.user = decode(user)
      const url = process.env.VUE_APP_BASEURL + '/User/findById'
      axios.post(url, this.user)
        .then((res) => {
          if(res.data.state) {
            this.user = res.data.user
            this.user.img = '/account.svg'
          }
        })
        .catch((err) => console.log(err))
    }
    else this.cancel()
  },
  mounted() {},
  unmounted() {}
}

export default useController
