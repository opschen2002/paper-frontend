<template>
  <div class="page">
    <div class="container">
      <el-card style="width: 300px">
        <template #header>
          <div class="title">个人信息</div>
        </template>
        <el-descriptions :column="1" border v-if="info">
          <el-descriptions-item label="学号">{{
            info.studentId || "-"
          }}</el-descriptions-item>
          <el-descriptions-item label="姓名">{{
            info.name || "-"
          }}</el-descriptions-item>
          <el-descriptions-item label="班级">{{
            info.className || "-"
          }}</el-descriptions-item>
          <el-descriptions-item label="学院">{{
            info.college || "-"
          }}</el-descriptions-item>
          <el-descriptions-item label="测试日期">{{
            (info.testDate && info.testDate.split("T")[0]) || "-"
          }}</el-descriptions-item>
        </el-descriptions>
      </el-card>
      <el-card style="width: 700px" header="最新体测成绩">
        <el-descriptions :column="2" border v-if="info">
          <el-descriptions-item label="身高"
            >{{ info.height || "-" }} cm</el-descriptions-item
          >
          <el-descriptions-item label="体重"
            >{{ info.weight || "-" }} kg</el-descriptions-item
          >
          <el-descriptions-item label="50米跑"
            >{{ info.run50m || "-" }} m</el-descriptions-item
          >
          <el-descriptions-item label="立定跳远"
            >{{ info.longJump || "-" }} cm</el-descriptions-item
          >
          <el-descriptions-item label="肺活量"
            >{{ info.vitalCapacity || "-" }} ml</el-descriptions-item
          >
          <el-descriptions-item label="体前屈"
            >{{ info.sitAndReach || "-" }} cm</el-descriptions-item
          >
          <el-descriptions-item v-if="info.gender === '女'" label="800米跑"
            >{{ info.run800m || "-" }} m
          </el-descriptions-item>
          <el-descriptions-item v-if="info.gender === '男'" label="1000米跑"
            >{{ info.run1000m || "-" }} m
          </el-descriptions-item>
          <el-descriptions-item v-if="info.gender === '女'" label="仰卧起坐"
            >{{ info.sitUp || "-" }} 个</el-descriptions-item
          >
          <el-descriptions-item v-if="info.gender === '男'" label="引体向上"
            >{{ info.pullUp || "-" }} 个</el-descriptions-item
          >
          <el-descriptions-item label="总分" :span="2">{{
            info.totalScore || "-"
          }}</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import request from "@/utils/request.js";
import { onMounted, ref } from "vue";
import { ElNotification } from "element-plus";
import { useRoute } from "vue-router";
const route = useRoute();
const studentId = route.query.id;

const info = ref(null);
const getData = () => {
  request
    .get(`/api/admin/score/${studentId}`)
    .then((res) => {
      console.log("成绩详情：", res);
      if (res.code === 200) {
        info.value = res.data;
      } else {
        ElNotification({
          title: "失败",
          message: res.message,
          type: "error",
        });
      }
    })
    .catch((err) => {
      ElNotification({
        title: "失败",
        message: err.message,
        type: "error",
      });
    });
};

onMounted(() => {
  getData();
});
</script>

<style lang="scss" scoped>
.container {
  width: 1000px;
  margin: 0 auto;
  display: flex;
  .title {
    font-size: 18px;
    font-weight: 500;
  }
}
</style>
