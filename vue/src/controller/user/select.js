import router from '@/router'
import axios from 'axios'
import { decode } from '@/util/Base64'
import useAxios from '@/util/UseAxios'

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
    router.push({ name: 'EditView' })
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
    //const user = localStorage.getItem('user');
    //if(user) {
      //this.user = decode(user)
      const url = process.env.VUE_APP_BASEURL + '/User/findById'
      //axios.post(url, this.user)
      useAxios.post(url)
        .then((res) => {
          //console.log(res)
          if(res.data.state) {
            this.user = res.data.result
            if(res.data.result.fileNo) {
              const fileUrl = process.env.VUE_APP_BASEURL + '/FileUpload/User'
              this.user.img = `${fileUrl}/${res.data.result.fileNo}`
            } else {
              this.user.img = '/account.svg'
            }
          } else {
            localStorage.removeItem('user');
            localStorage.removeItem('token');
            window.location.href = '/'
          }
        })
        .catch((err) => console.log(err))
    //}
    //else this.cancel()
  },
  mounted() {},
  unmounted() {}
}

export default useController
