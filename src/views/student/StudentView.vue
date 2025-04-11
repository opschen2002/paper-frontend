<template>
  <div class="student-container">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <template #header>
            <h3>个人信息</h3>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="学号">
              {{ studentInfo.studentId }}
            </el-descriptions-item>
            <el-descriptions-item label="姓名">
              {{ studentInfo.name }}
            </el-descriptions-item>
            <el-descriptions-item label="班级">
              {{ studentInfo.className }}
            </el-descriptions-item>
            <el-descriptions-item label="学院">
              {{ studentInfo.college }}
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
      <el-col :span="16">
        <ScoreCard
          :score-data="scoreData"
          title="最新体测成绩"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import ScoreCard from '@/components/common/ScoreCard.vue'
import { studentApi } from '@/api'
import { ElMessage } from 'element-plus'

export default {
  name: 'StudentView',
  components: { ScoreCard },
  setup() {
    const studentInfo = ref({})
    const scoreData = ref({})
    const studentId = localStorage.getItem('username')

    const loadData = async () => {
      try {
        const [info, score] = await Promise.all([
          studentApi.getStudentInfo(studentId),
          studentApi.getPersonalScore(studentId)
        ])
        studentInfo.value = info
        scoreData.value = score
      } catch (error) {
        ElMessage.error('获取数据失败')
      }
    }

    onMounted(loadData)

    return {
      studentInfo,
      scoreData
    }
  }
}
</script>

<style scoped>
.student-container {
  padding: 20px;
}
</style>