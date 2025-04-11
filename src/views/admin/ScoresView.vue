<template>
  <div class="scores-container">
    <DataTable
      :loading="loading"
      :table-data="scoreList"
      :total="total"
      @page-change="handlePageChange"
    >
      <template #header>
        <el-form :inline="true" :model="queryForm">
          <el-form-item label="学号">
            <el-input v-model="queryForm.studentId" placeholder="请输入学号" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleQuery">查询</el-button>
          </el-form-item>
        </el-form>
      </template>

      <el-table-column prop="studentId" label="学号" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="className" label="班级" />
      <el-table-column prop="totalScore" label="总分" />
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button type="primary" @click="handleEdit(row)">编辑</el-button>
        </template>
      </el-table-column>
    </DataTable>

    <!-- 编辑对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="编辑成绩"
      width="50%"
    >
      <ScoreCard
        v-if="currentScore"
        :score-data="currentScore"
        :editable="true"
        @update="handleUpdate"
      />
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import DataTable from '@/components/common/DataTable.vue'
import ScoreCard from '@/components/common/ScoreCard.vue'
import { adminApi } from '@/api'
import { ElMessage } from 'element-plus'

export default {
  name: 'ScoresView',
  components: { DataTable, ScoreCard },
  setup() {
    const loading = ref(false)
    const scoreList = ref([])
    const total = ref(0)
    const dialogVisible = ref(false)
    const currentScore = ref(null)
    
    const queryForm = reactive({
      studentId: '',
      page: 1
    })

    const handleQuery = async () => {
      try {
        loading.value = true
        const res = await adminApi.getStudentScore(queryForm.studentId)
        if (res) {
          scoreList.value = [res]
          total.value = 1
        }
      } catch (error) {
        ElMessage.error('查询失败')
      } finally {
        loading.value = false
      }
    }

    const handlePageChange = (page) => {
      queryForm.page = page
      handleQuery()
    }

    const handleEdit = (row) => {
      currentScore.value = row
      dialogVisible.value = true
    }

    const handleUpdate = async (data) => {
      try {
        await adminApi.updateScore(data)
        ElMessage.success('更新成功')
        dialogVisible.value = false
        handleQuery()
      } catch (error) {
        ElMessage.error('更新失败')
      }
    }

    return {
      loading,
      scoreList,
      total,
      queryForm,
      dialogVisible,
      currentScore,
      handleQuery,
      handlePageChange,
      handleEdit,
      handleUpdate
    }
  }
}
</script>