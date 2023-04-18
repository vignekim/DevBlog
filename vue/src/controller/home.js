import router from '@/router'
import axios from 'axios'
import HomeList from '@/components/HomeList.vue'
import { useDate } from '@/util/Date'

const data = () => {
  const resultData = {}

  //resultData.list = sampleData()
  //resultData.state = 1
  resultData.list = []
  resultData.state = true
  const user = localStorage.getItem('user');
  if(user) {resultData.account = true}
  else {resultData.account = false}

  return resultData
}

const sampleData = () => {
  const list = []
  const toDay = useDate(new Date())
  for(var i = 1; i <= 30; i++) {
    let row = {}
    row.no = i
    row.img = (i%2 == 1)? '/account.svg' : 'logo.png'
    row.title = `연습용 ${i}`
    row.regDate = toDay
    row.count = 0
    row.desc = '스타일리시한 세 가지 케이스 색상. 건강과 피트니스에 대한 통찰을 제공하는 강력한 센서. 혁신적인 안전 기능. 끊김 없이 소통을 유지하는 편리한 방법. 여기에 한층 향상된 성능을 위한 더 빠른 듀얼 코어 프로세서까지. 수많은 기능들로 가득 찬 Apple Watch SE, 그 어느 때보다도 탁월한 가성비를 자랑합니다.'
    list[list.length] = row
  }
  return list
}

const database = () => {
  return new Promise((resolve, reject) => {
    const url = process.env.VUE_APP_BASEURL + '/Notice/List'
    axios.post(url)
      .then((res) => resolve(res.data))
      .catch((err) => reject(err))
  })
}

const methods = {
  notice() {
    router.push({ name: 'CreateNotice' })
  }
}

const useController = {
  name: 'HomeView',
  components: { HomeList },
  props: {},
  methods,
  data() { return data() },
  setup() {},
  created() {
    database()
      .then((res) => {
        if(res.state) {
          res.result.forEach(row => {
            row.img = (row.no % 2 == 1)? '/account.svg' : 'logo.png'
            row.count = 0
            this.list[row.no] = row
          });
          if(res.result.length > 0) this.state = false
        }
      })
      .catch((err) => {
        console.log(err)
      })
  },
  mounted() {},
  unmounted() {}
}

export default useController
