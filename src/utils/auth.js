export function setToken(token) {
  localStorage.setItem('token', token)
}

export function getToken() {
  return localStorage.getItem('token')
}

export function removeToken() {
  localStorage.removeItem('token')
}

export function setUserRole(role) {
  localStorage.setItem('userRole', role)
}

export function getUserRole() {
  return localStorage.getItem('userRole')
}

export function clearUserInfo() {
  localStorage.removeItem('token')
  localStorage.removeItem('userRole')
}