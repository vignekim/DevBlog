import axios from 'axios'

const setInterceptors = (instance) => {
  instance.interceptors.request.use(
    (config) => {
      let token = localStorage.getItem('token')
      if(token == null) token = ''
      config.headers.Authorization = token
      return config;
    },
    (error) => {return Promise.reject(error)}
  )
  return instance
}

const useAxios = () => {
  const instance = axios.create({baseURL: ''})
  return setInterceptors(instance)
}

export default useAxios()