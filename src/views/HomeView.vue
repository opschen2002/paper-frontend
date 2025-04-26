<template>
  <div class="page">
    <div class="page-form items-center">
      <el-select
        class="item-input"
        v-model="className"
        placeholder="请选择班级"
      >
        <el-option
          v-for="item in classList"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-input
        class="item-input"
        v-model="studentId"
        clearable
        placeholder="请输入学号"
      ></el-input>
      <el-button type="primary" @click="getData">查询</el-button>
      <div class="margin-left-auto" v-if="role === 'admin'">
        <el-button class="upload" type="success"
          >上传
          <input id="upload-file" type="file" @change="uploadFile" />
        </el-button>
      </div>
    </div>
    <div class="page-container">
      <el-table :data="list" stripe style="width: 100%" height="100%">
        <el-table-column prop="studentId" label="学号" width="120" />
        <el-table-column prop="name" label="姓名" width="80" />
        <el-table-column prop="className" label="班级" width="100" />
        <el-table-column prop="college" label="学院" width="120" />
        <el-table-column prop="height" label="身高(cm)" width="90" />
        <el-table-column prop="weight" label="体重(kg)" width="80" />
        <el-table-column prop="longJump" label="立定跳远(cm)" width="120" />
        <el-table-column prop="vitalCapacity" label="肺活量(ml)" width="100" />
        <el-table-column
          prop="sitAndReach"
          label="坐位体前屈(cm)"
          width="130"
        />
        <el-table-column prop="run1000m" label="长跑1000m(s)" width="120" />
        <el-table-column prop="run800m" label="长跑800m(s)" width="120" />
        <el-table-column prop="pullUp" label="引体向上(个)" width="120" />
        <el-table-column prop="sitUp" label="仰卧起坐(个)" width="120" />
        <el-table-column prop="totalScore" label="总分" width="100" />
        <el-table-column prop="testDate" label="测试日期" width="130">
          <template #default="scope">
            {{ scope.row.testDate && scope.row.testDate.split("T")[0] }}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="操作" width="160" fixed="right">
          <template #default="scope">
            <el-button
              type="primary"
              :disabled="role !== 'admin'"
              text
              @click="edit(scope.row)"
            >
              编辑
            </el-button>
            <el-button type="success" text @click="view(scope.row)">
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      v-model="dialogVisible"
      title="成绩修改"
      width="400px"
      :before-close="handleClose"
    >
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="auto"
        class="demo-ruleForm"
        status-icon
      >
        <el-form-item label="身高" prop="height">
          <el-input v-model="ruleForm.height" />
        </el-form-item>
        <el-form-item label="体重" prop="weight">
          <el-input v-model="ruleForm.weight" />
        </el-form-item>
        <el-form-item label="立定跳远" prop="longJump">
          <el-input v-model="ruleForm.longJump" />
        </el-form-item>
        <el-form-item label="肺活量" prop="vitalCapacity">
          <el-input v-model="ruleForm.vitalCapacity" />
        </el-form-item>
        <el-form-item label="坐位体前屈" prop="sitAndReach">
          <el-input v-model="ruleForm.sitAndReach" />
        </el-form-item>
        <el-form-item label="长跑1000m" prop="" v-if="ruleForm.gender === '男'">
          <el-input v-model="ruleForm.run1000m" />
        </el-form-item>
        <el-form-item label="长跑800m" prop="" v-if="ruleForm.gender === '女'">
          <el-input v-model="ruleForm.run800m" />
        </el-form-item>
        <el-form-item label="引体向上" prop="" v-if="ruleForm.gender === '男'">
          <el-input v-model="ruleForm.pullUp" />
        </el-form-item>
        <el-form-item label="仰卧起坐" prop="" v-if="ruleForm.gender === '女'">
          <el-input v-model="ruleForm.sitUp" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleClose">取消</el-button>
          <el-button type="primary" @click="save"> 保存 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import request from "@/utils/request.js";
import { onMounted, ref } from "vue";
import { ElNotification } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();

const tokens = JSON.parse(localStorage.getItem("tokens"));
const role = tokens?.role;

const dialogVisible = ref(false);

const ruleFormRef = ref(null);
const ruleForm = ref({
  height: "",
  weight: "",
  longJump: "",
  vitalCapacity: "",
  sitAndReach: "",
  run1000m: "",
  run800m: "",
  pullUp: "",
  sitUp: "",
});
const rules = ref({
  height: [{ required: true, message: "请输入身高", trigger: "blur" }],
  weight: [{ required: true, message: "请输入体重", trigger: "blur" }],
  longJump: [{ required: true, message: "请输入立定跳远", trigger: "blur" }],
  vitalCapacity: [{ required: true, message: "请输入肺活量", trigger: "blur" }],
  sitAndReach: [
    { required: true, message: "请输入坐位体前屈", trigger: "blur" },
  ],
  run1000m: [{ required: true, message: "请输入长跑1000m", trigger: "blur" }],
  run800m: [{ required: true, message: "请输入长跑800m", trigger: "blur" }],
  pullUp: [{ required: true, message: "请输入引体向上", trigger: "blur" }],
  sitUp: [{ required: true, message: "请输入仰卧起坐", trigger: "blur" }],
});

const className = ref("");
const classList = ref([]);
const getClassList = () => {
  request.get("/api/admin/getClassNameList").then((res) => {
    console.log("班级列表", res);
    if (res.code === 200) {
      classList.value = res.data.map((v) => {
        return {
          label: v,
          value: v,
        };
      });
      className.value = classList.value[0].value;
      getData();
    }
  });
};

const list = ref([]);
const studentId = ref("");

const getData = () => {
  request
    .get(
      `/api/teacher/scores/byClass?className=${className.value}&studentId=${studentId.value}`
    )
    .then((res) => {
      console.log("---", res);
      if (res.code === 200) {
        list.value = res.data;
      }
    });
};

const edit = (row) => {
  console.log(row);
  ruleForm.value = JSON.parse(JSON.stringify(row));
  dialogVisible.value = true;
};

const handleClose = () => {
  ruleFormRef.value.resetFields();
  dialogVisible.value = false;
};

const save = () => {
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      request
        .put("/api/admin/score", {
          ...ruleForm.value,
        })
        .then((res) => {
          console.log("---", res);
          if (res.code === 200) {
            ElNotification({
              title: "成功",
              message: "修改成功",
              type: "success",
            });
            handleClose();
            getData();
          }
        });
    } else {
      console.log("error submit!!");
      return false;
    }
  });
};

const view = (row) => {
  console.log(row);
  router.push({
    path: "/student",
    query: {
      id: row.studentId,
    },
  });
};

onMounted(() => {
  getClassList();
});

const uploadFile = (e) => {
  const file = e.target.files[0];
  const formData = new FormData();
  formData.append("file", file);
  request.post("/api/admin/score/import", formData).then((res) => {
    console.log("上传结果", res);
    if (res.code === 200) {
      ElNotification({
        title: "成功",
        message: "上传成功",
        type: "success",
      });
      getData();
    }
  });
};
</script>

<style lang="scss" scoped>
.item-input {
  margin-right: 10px;
  width: 200px;
}

.margin-left-auto {
  margin-left: auto;
}

.upload {
  position: relative;
  #upload-file {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0;
  }
}
</style>
