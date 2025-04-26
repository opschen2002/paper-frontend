<template>
  <div class="page">
    <div class="page-header flex-space-between">
      <h1>体能测试管理系统</h1>
      <el-dropdown :hide-on-click="false" @command="handleCommand">
        <span class="el-dropdown-link">
          <el-avatar :size="20" :src="circleUrl" />
          <el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="1">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div class="page-main">
      <div class="page-aside" v-if="role === 'admin' || role === 'teacher'">
        <el-menu default-active="1" class="el-menu-vertical-demo">
          <el-menu-item index="1" @click="handleRouter('/home')">
            <el-icon><icon-menu /></el-icon>
            <span>成绩列表</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="page-container">
        <RouterView />
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterView, useRouter } from "vue-router";
import { ref } from "vue";
import { ElNotification, ElMessageBox } from "element-plus";
import { ArrowDown, Menu as IconMenu } from "@element-plus/icons-vue";

const tokens = JSON.parse(localStorage.getItem("tokens"));

const role = tokens?.role;

const router = useRouter();

const circleUrl = ref(
  "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
);

const handleRouter = (path) => {
  router.push(path);
};

const handleCommand = (command) => {
  if (command === "1") {
    ElMessageBox.confirm("确定要退出当前账户吗？", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "提示",
    })
      .then(() => {
        localStorage.clear();
        ElNotification({
          title: "提示",
          message: "退出成功",
          type: "success",
        });
        setTimeout(() => {
          router.push("/login");
        }, 1500);
      })
      .catch(() => {});
  }
};
</script>

<style lang="scss" scoped>
.page {
  padding: 0;
  &-header {
    height: 40px;
    padding: 0 10px;
    background-color: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
    > h1 {
      font-size: 18px;
      background: #ffffff;
    }
  }
  &-main {
    height: calc(100% - 40px);
    display: flex;
    .page-aside {
      width: 200px;
      height: 100%;
      background-color: #fff;
      box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
      .el-menu {
        height: 100%;
        .el-menu-item {
          height: 40px;
          line-height: 40px;
        }
      }
    }
    .page-container {
      margin-top: 0;
      flex: 1;
      height: 100%;
      padding: 10px;
      background: none;
    }
  }
}
</style>
