import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';

const LoginPage = ({ setRole }) => {
  const [account, setAccount] = useState('');
  const [password, setPassword] = useState('');
  const [userRole, setUserRole] = useState('STUDENT');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      // 假设后端登录接口为 /login，返回 { token, role }
      const response = await api.post('/login', { account, password, role: userRole });
      const { token, role } = response.data;
      localStorage.setItem('token', token);
      localStorage.setItem('role', role);
      setRole(role);
      // 根据角色跳转页面
      if(role === 'ADMIN'){
        navigate('/admin');
      } else if(role === 'TEACHER') {
        navigate('/teacher');
      } else {
        navigate('/student');
      }
    } catch(err) {
      console.error(err);
      setError('登录失败，请检查账号、密码及角色');
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto' }}>
      <h3>登录</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>账号:</label>
          <input
            type="text"
            value={account}
            onChange={e => setAccount(e.target.value)}
            required
          />
        </div>
        <div>
          <label>密码:</label>
          <input
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>角色:</label>
          <select value={userRole} onChange={e => setUserRole(e.target.value)}>
            <option value="ADMIN">管理员</option>
            <option value="TEACHER">教师</option>
            <option value="STUDENT">学生</option>
          </select>
        </div>
        <button type="submit">登录</button>
      </form>
    </div>
  );
};

export default LoginPage;