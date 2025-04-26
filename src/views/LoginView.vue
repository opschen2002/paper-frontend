<template>
  <div class="page flex-center">
    <div class="login">
      <h6>体能测试登录</h6>
      <el-form
        ref="ruleFormRef"
        style="max-width: 600px"
        :model="ruleForm"
        :rules="rules"
        label-width="auto"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" />
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="ruleForm.role" placeholder="">
            <el-option label="管理员" value="admin" />
            <el-option label="教师" value="teacher" />
            <el-option label="学生" value="student" />
          </el-select>
        </el-form-item>
        <el-form-item label=" ">
          <el-button style="width: 100%" type="primary" @click="submitForm(ruleFormRef)">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import request from "@/utils/request";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter();

const ruleFormRef = ref(null);
const ruleForm = ref({
  username: "",
  password: "",
  role: "",
});
const rules = ref({
  username: [{ required: true, message: "请出入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请出入密码", trigger: "blur" }],
  role: [{ required: true, message: "请选择角色", trigger: "change" }],
});

const submitForm = (formEl) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      console.log("submit!");

      request
        .post("/api/auth/login", ruleForm.value)
        .then((res) => {
          console.log(res);
          if (res.code === 200) {
            localStorage.setItem("tokens", JSON.stringify(res.data));
            if (res.data && res.data.role !== "student") {
              router.push("/home");
            } else {
              router.push("/student?id=" + res.data.username);
            }
          } else {
            console.log("登录失败");
            ElMessage({
              message: res.message,
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
          ElMessage({
            message: error.message,
            type: "error",
          });
        });
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
</script>

<style lang="scss" scoped>
.login {
  width: 400px;
  background: #ffffff;
  padding: 20px;
  border-radius: 10px;

  > h6 {
    font-size: 20px;
    text-align: center;
    margin-bottom: 20px;
  }
}
</style>
