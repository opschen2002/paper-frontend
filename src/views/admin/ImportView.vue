<template>
  <div class="import-container">
    <el-card>
      <template #header>
        <h3>数据导入</h3>
      </template>
      <FileUpload
        :action="API_URLS.IMPORT_SCORE"
        @upload-success="handleUploadSuccess"
        @upload-error="handleUploadError"
      />
      <el-alert
        v-if="importResult"
        :title="importResult.message"
        :type="importResult.success ? 'success' : 'error'"
        show-icon
        style="margin-top: 20px"
      />
    </el-card>
  </div>
</template>

<script>
import { ref } from 'vue'
import FileUpload from '@/components/common/FileUpload.vue'
import { API_URLS } from '@/api/config'
import { ElMessage } from 'element-plus'

export default {
  name: 'ImportView',
  components: { FileUpload },
  setup() {
    const importResult = ref(null)

    const handleUploadSuccess = (response) => {
      ElMessage.success('导入成功')
      importResult.value = {
        success: true,
        message: `成功导入 ${response.data} 条数据`
      }
    }

    // 修改这里，使用 error 参数或删除它
    const handleUploadError = () => {  // 删除未使用的 error 参数
      ElMessage.error('导入失败')
      importResult.value = {
        success: false,
        message: '导入失败，请检查文件格式'
      }
    }

    return {
      API_URLS,
      importResult,
      handleUploadSuccess,
      handleUploadError
    }
  }
}
</script>

<style scoped>
.import-container {
  padding: 20px;
}
</style>