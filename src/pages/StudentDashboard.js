import React, { useState, useEffect } from 'react';
import api from '../api';

const StudentDashboard = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    // 假设后端接口 /student/data 返回当前学生的体能测试数据
    const fetchData = async () => {
      try {
        const response = await api.get('/student/data');
        setData(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, []);

  if (!data) return <p>加载中...</p>;

  return (
    <div style={{ padding: '20px' }}>
      <h3>个人体能测试数据</h3>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>测试日期</th>
            <th>50米跑(秒)</th>
            <th>立定跳远(米)</th>
            <th>引体向上(次数)</th>
            <th>仰卧起坐(次数)</th>
            <th>肺活量(毫升)</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.score_date}</td>
              <td>{item.fifty_meter_run}</td>
              <td>{item.long_jump}</td>
              <td>{item.pull_ups}</td>
              <td>{item.sit_ups}</td>
              <td>{item.vital_capacity}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {/* 可添加对比班级平均数据的展示 */}
    </div>
  );
};

export default StudentDashboard;