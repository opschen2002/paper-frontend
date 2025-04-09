import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import AdminDashboard from './pages/AdminDashboard';
import TeacherDashboard from './pages/TeacherDashboard';
import StudentDashboard from './pages/StudentDashboard';
import Header from './components/Header';

// 封装的受保护路由组件，判断是否登录
const PrivateRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" />;
};

const App = () => {
  // 当前登录用户角色，用于路由跳转
  const [role, setRole] = useState(localStorage.getItem('role') || null);

  useEffect(() => {
    // 这里可以扩展，比如效验 token 是否失效
  }, []);

  return (
    <BrowserRouter>
      <Header role={role} setRole={setRole} />
      <Routes>
        <Route path="/login" element={<LoginPage setRole={setRole} />} />
        <Route path="/admin" element={
          <PrivateRoute>
            <AdminDashboard />
          </PrivateRoute>
        } />
        <Route path="/teacher" element={
          <PrivateRoute>
            <TeacherDashboard />
          </PrivateRoute>
        } />
        <Route path="/student" element={
          <PrivateRoute>
            <StudentDashboard />
          </PrivateRoute>
        } />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;