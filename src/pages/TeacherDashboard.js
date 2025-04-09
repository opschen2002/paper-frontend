import React, { useState } from 'react';
import api from '../api';

const TeacherDashboard = () => {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = async () => {
    try {
      // 假设后端接口 /teacher/data 根据查询条件返回学生体能数据
      const response = await api.get('/teacher/data', { params: { query } });
      setResults(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h3>教师数据查询与分析</h3>
      <div>
        <input 
          type="text" 
          placeholder="输入查询条件：班级、测试项目、时间段等" 
          value={query}
          onChange={e => setQuery(e.target.value)}
        />
        <button onClick={handleSearch}>查询</button>
      </div>
      <div style={{ marginTop: '20px' }}>
        <h4>查询结果</h4>
        {results.length === 0 ? (
          <p>暂无数据</p>
        ) : (
          <table border="1" cellPadding="5">
            <thead>
              <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>测试成绩</th>
              </tr>
            </thead>
            <tbody>
              {results.map((item, index) => (
                <tr key={index}>
                  <td>{item.student_id}</td>
                  <td>{item.name}</td>
                  <td>{item.score}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      {/* 可集成图表组件展示数据分析结果 */}
    </div>
  );
};

export default TeacherDashboard;