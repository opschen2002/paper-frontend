<template>
  <div class="scores-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h3>成绩查询</h3>
        </div>
      </template>

      <el-form :inline="true" :model="queryForm" class="search-form">
        <el-form-item label="班级">
          <el-input v-model="queryForm.className" placeholder="请输入班级" />
        </el-form-item>
        <el-form-item label="时间范围">
          <el-date-picker
            v-model="queryForm.dateRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">查询</el-button>
        </el-form-item>
      </el-form>

      <DataTable
        :loading="loading"
        :table-data="scoreList"
        :total="total"
        @page-change="handlePageChange"
      >
        <el-table-column prop="studentId" label="学号" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="className" label="班级" />
        <el-table-column prop="testDate" label="测试日期">
          <template #default="{ row }">
            {{ formatDate(row.testDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="totalScore" label="总分" />
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleViewDetail(row)">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </DataTable>
    </el-card>

    <!-- 详情对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="成绩详情"
      width="50%"
    >
      <ScoreCard
        v-if="currentScore"
        :score-data="currentScore"
        :editable="false"
      />
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import DataTable from '@/components/common/DataTable.vue'
import ScoreCard from '@/components/common/ScoreCard.vue'
import { teacherApi } from '@/api'
import { ElMessage } from 'element-plus'

export default {
  name: 'ScoresView',
  components: {
    DataTable,
    ScoreCard
  },
  setup() {
    const loading = ref(false)
    const scoreList = ref([])
    const total = ref(0)
    const dialogVisible = ref(false)
    const currentScore = ref(null)

    const queryForm = reactive({
      className: '',
      dateRange: null,
      page: 1
    })

    const handleQuery = async () => {
      try {
        loading.value = true
        let res
        if (queryForm.dateRange && queryForm.dateRange.length === 2) {
          res = await teacherApi.getScoresByDateRange(
            queryForm.dateRange[0],
            queryForm.dateRange[1]
          )
        } else {
          res = await teacherApi.getClassScores(queryForm.className)
        }
        scoreList.value = res.list || []
        total.value = res.total || 0
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

    const handleViewDetail = (row) => {
      currentScore.value = row
      dialogVisible.value = true
    }

    const formatDate = (date) => {
      if (!date) return ''
      return new Date(date).toLocaleDateString()
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
      handleViewDetail,
      formatDate
    }
  }
}
</script>

<style scoped>
.scores-container {
  padding: 20px;
}

.search-form {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>