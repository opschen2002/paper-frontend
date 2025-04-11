<template>
  <el-container class="layout-container">
    <el-aside width="200px">
      <el-menu router>
        <el-menu-item index="/admin/import">数据导入</el-menu-item>
        <el-menu-item index="/admin/scores">成绩管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'AdminLayout'
}
</script>