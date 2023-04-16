export const useDate = (today) => {
  const year = today.getFullYear()
  const month = today.getMonth() + 1
  const date = today.getDate()
  return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`
}
