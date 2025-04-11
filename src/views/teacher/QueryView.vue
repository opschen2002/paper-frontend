<template>
  <div class="query-container">
    <el-card>
      <template #header>
        <el-form :inline="true" :model="queryForm">
          <el-form-item label="班级">
            <el-input v-model="queryForm.className" placeholder="请输入班级" />
          </el-form-item>
          <el-form-item label="学院">
            <el-input v-model="queryForm.college" placeholder="请输入学院" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleQuery">查询</el-button>
          </el-form-item>
        </el-form>
      </template>

      <DataTable
        :loading="loading"
        :table-data="studentList"
        :total="total"
        @page-change="handlePageChange"
      >
        <el-table-column prop="studentId" label="学号" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="className" label="班级" />
        <el-table-column prop="college" label="学院" />
        <el-table-column prop="gender" label="性别">
          <template #default="{ row }">
            {{ row.gender === 'male' ? '男' : '女' }}
          </template>
        </el-table-column>
      </DataTable>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import DataTable from '@/components/common/DataTable.vue'
import { teacherApi } from '@/api'
import { ElMessage } from 'element-plus'

export default {
  name: 'QueryView',
  components: { DataTable },
  setup() {
    const loading = ref(false)
    const studentList = ref([])
    const total = ref(0)
    
    const queryForm = reactive({
      className: '',
      college: '',
      page: 1
    })

    const handleQuery = async () => {
      try {
        loading.value = true
        const res = await teacherApi.queryStudents(queryForm)
        studentList.value = res.list || []
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

    return {
      loading,
      studentList,
      total,
      queryForm,
      handleQuery,
      handlePageChange
    }
  }
}
</script>