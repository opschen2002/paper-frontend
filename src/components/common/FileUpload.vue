<template>
  <div class="upload-container">
    <el-upload
      class="upload"
      :action="action"
      :headers="headers"
      :before-upload="beforeUpload"
      :on-success="handleSuccess"
      :on-error="handleError"
      :accept="accept"
    >
      <template #trigger>
        <el-button type="primary">选择文件</el-button>
      </template>
      <template #tip>
        <div class="tip">{{ tip }}</div>
      </template>
    </el-upload>
  </div>
</template>

<script>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

export default {
  name: 'FileUpload',
  props: {
    action: {
      type: String,
      required: true
    },
    accept: {
      type: String,
      default: '.xlsx,.xls'
    },
    tip: {
      type: String,
      default: '只能上传 Excel 文件'
    },
    maxSize: {
      type: Number,
      default: 5 // MB
    }
  },
  emits: ['upload-success', 'upload-error'],
  setup(props, { emit }) {
    const headers = ref({
      // 可以添加token等认证信息
    })

    const beforeUpload = (file) => {
      const isValidType = props.accept.split(',').some(type => 
        file.name.toLowerCase().endsWith(type.replace('.', ''))
      )
      
      const isLessThanMaxSize = file.size / 1024 / 1024 < props.maxSize

      if (!isValidType) {
        ElMessage.error('请上传正确的文件格式！')
        return false
      }

      if (!isLessThanMaxSize) {
        ElMessage.error(`文件大小不能超过 ${props.maxSize}MB！`)
        return false
      }

      return true
    }

    const handleSuccess = (response) => {
      ElMessage.success('上传成功！')
      emit('upload-success', response)
    }

    const handleError = (error) => {
      ElMessage.error('上传失败！')
      emit('upload-error', error)
    }

    return {
      headers,
      beforeUpload,
      handleSuccess,
      handleError
    }
  }
}
</script>

<style scoped>
.upload-container {
  text-align: center;
  padding: 20px;
}

.tip {
  font-size: 12px;
  color: #909399;
  margin-top: 10px;
}
</style>