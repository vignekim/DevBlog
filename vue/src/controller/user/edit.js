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
  imgeChange() {
    const img = document.createElement('INPUT')
    img.setAttribute('type', 'file')
    img.setAttribute('accept', 'image/*')
    img.onchange = (e) => {
      e.preventDefault
      if(e.target.files.length > 0) {
        //this.imgViewer(e.target.files[0])
        this.imgSave(e.target.files[0])
      }
    }
    img.click()
  },
  imgViewer(file) {
    var reader = new FileReader();
    reader.onload = function (e) {
      const imagePreview = document.querySelector('.user');
      imagePreview.setAttribute('src', e.target.result)
    };
    reader.readAsDataURL(file);
  },
  imgSave(file) {
    const formData = new FormData()
    formData.append("file", file)
    const url = process.env.VUE_APP_BASEURL + '/FileUpload/User'
    axios.post(url, formData)
      .then((res) => {
        if(res.data.state) {
          const path = `${url}?url=${res.data.url}&mediaType=${res.data.mediaType}`
          const imagePreview = document.querySelector('.user');
          imagePreview.setAttribute('src', path)
        }
      })
      .catch((error) => console.log(error))
  },
  save() {
    const url = process.env.VUE_APP_BASEURL + '/User/editById'
    axios.post(url, this.user)
      .then((res) => {
        if(res.data.state) {
          router.push({ name: 'SelectView', params: {userNo: this.user.no} })
        }
      })
      .catch((err) => console.log(err))
  },
  del() {
    const url = process.env.VUE_APP_BASEURL + '/User/delteById'
    axios.post(url, this.user)
      .then((res) => {
        if(res.data.state) {
          localStorage.removeItem('user');
          window.location.href = '/'
        }
      })
      .catch((err) => console.log(err))
  },
  cancel() {
    router.push({ name: 'SelectView', params: {userNo: this.user.no} })
  }
}

const useController = {
  name: 'EditView',
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
