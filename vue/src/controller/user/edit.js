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
    img: ''
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
    useAxios.post(url, formData)
      .then((res) => {
        if(res.data.state) {
          console.log(res.data.result)
          this.user.img = `${url}/${res.data.result.no}`
          // http://localhost:8080/FileUpload/User/3
          /*
          const path = `${url}?url=${res.data.result.saveName}&mediaType=${res.data.result.mediaType}`
          const imagePreview = document.querySelector('.user');
          imagePreview.setAttribute('src', path)
          */
        }
      })
      .catch((error) => console.log(error))
  },
  save() {
    const url = process.env.VUE_APP_BASEURL + '/User/editById'
    useAxios.post(url, this.user)
      .then((res) => {
        if(res.data.state) {
          router.push({ name: 'SelectView' })
        } else {
          alert(res.data.message)
        }
      })
      .catch((err) => console.log(err))
  },
  del() {
    const url = process.env.VUE_APP_BASEURL + '/User/deleteById'
    //const params = {no: this.user.no}
    //axios.delete(url, {params})
    useAxios.delete(url)
      .then((res) => {
        if(res.data.state) {
          localStorage.removeItem('user');
          localStorage.removeItem('token');
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
    //const user = localStorage.getItem('user');
    //if(user) {
      //this.user = decode(user)
      const url = process.env.VUE_APP_BASEURL + '/User/findById'
      //axios.post(url, this.user)
      useAxios.post(url)
        .then((res) => {
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
