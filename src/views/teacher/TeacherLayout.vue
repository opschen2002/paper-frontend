<template>
  <el-container class="layout-container">
    <el-aside width="200px">
      <el-menu router>
        <el-menu-item index="/teacher/query">学生查询</el-menu-item>
        <el-menu-item index="/teacher/scores">成绩查看</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'TeacherLayout'
}
</script>