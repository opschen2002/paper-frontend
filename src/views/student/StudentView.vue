<template>
  <div class="student-container">
    <el-row :gutter="20" v-loading="loading">
      <el-col :span="8">
        <el-card v-if="hasStudentInfo">
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
        <el-empty v-else description="暂无学生信息" />
      </el-col>
      <el-col :span="16">
        <ScoreCard
          v-if="hasScoreData"
          :score-data="scoreData"
          title="最新体测成绩"
        />
        <el-empty v-else description="暂无成绩信息" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import ScoreCard from '@/components/common/ScoreCard.vue'
import { studentApi } from '@/api'
import { ElMessage } from 'element-plus'

export default {
  name: 'StudentView',
  components: { ScoreCard },
  setup() {
    const studentInfo = ref({})
    const scoreData = ref({})
    const loading = ref(false)
    const error = ref(null)
    
    const studentId = localStorage.getItem('username')
    
    const hasStudentInfo = computed(() => Object.keys(studentInfo.value).length > 0)
    const hasScoreData = computed(() => Object.keys(scoreData.value).length > 0)

    const loadData = async () => {
      if (!studentId) {
        ElMessage.error('未找到学号信息，请重新登录')
        return
      }

      loading.value = true
      error.value = null

      try {
        // 分开请求以便于单独处理错误
        const info = await studentApi.getStudentInfo(studentId)
        studentInfo.value = info
        
        try {
          const score = await studentApi.getPersonalScore(studentId)
          scoreData.value = score
        } catch (scoreError) {
          console.error('获取成绩失败:', scoreError)
          ElMessage.warning('获取成绩信息失败')
        }
      } catch (error) {
        console.error('获取学生信息失败:', error)
        ElMessage.error(error.message || '获取学生信息失败')
        error.value = error
      } finally {
        loading.value = false
      }
    }

    onMounted(loadData)

    return {
      studentInfo,
      scoreData,
      loading,
      error,
      hasStudentInfo,
      hasScoreData
    }
  }
}
</script>

<style scoped>
.student-container {
  padding: 20px;
}
</style>
