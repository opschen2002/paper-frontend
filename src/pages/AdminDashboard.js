import React, { useState } from 'react';
import api from '../api';

const AdminDashboard = () => {
  const [file, setFile] = useState(null);
  const [uploadMsg, setUploadMsg] = useState('');

  const handleFileChange = e => {
    setFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!file) return;
    const formData = new FormData();
    formData.append('file', file);
    try {
      // 假设后端接口 /upload 接收文件上传
      const response = await api.post('/upload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      setUploadMsg('上传成功！');
    } catch (err) {
      console.error(err);
      setUploadMsg('上传失败，请重试');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h3>管理员数据管理后台</h3>
      <div>
        <h4>数据导入</h4>
        <input type="file" onChange={handleFileChange} accept=".xls,.xlsx" />
        <button onClick={handleUpload}>上传文件</button>
        {uploadMsg && <p>{uploadMsg}</p>}
      </div>
      {/* 可在此处添加数据编辑、删除、用户权限管理等其他功能 */}
    </div>
  );
};

export default AdminDashboard;