import React from 'react';
import { useNavigate } from 'react-router-dom';

const Header = ({ role, setRole }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    setRole(null);
    navigate('/login');
  };

  return (
    <header style={{ padding: '10px 20px', backgroundColor: '#eee', marginBottom: '20px' }}>
      <h2>学生体能测试数据平台</h2>
      {role && (
        <div>
          <span>当前角色: {role}</span>
          <button onClick={handleLogout} style={{ marginLeft: '20px' }}>退出登录</button>
        </div>
      )}
    </header>
  );
};

export default Header;