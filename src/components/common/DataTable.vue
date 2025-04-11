<template>
  <div class="data-table">
    <div class="table-header">
      <slot name="header"></slot>
    </div>
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      style="width: 100%"
    >
      <slot></slot>
    </el-table>
    <div class="table-footer">
      <el-pagination
        v-if="showPagination"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="total"
        layout="total, prev, pager, next"
        @current-change="handlePageChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'DataTable',
  props: {
    loading: {
      type: Boolean,
      default: false
    },
    tableData: {
      type: Array,
      required: true
    },
    showPagination: {
      type: Boolean,
      default: true
    },
    currentPage: {
      type: Number,
      default: 1
    },
    pageSize: {
      type: Number,
      default: 10
    },
    total: {
      type: Number,
      default: 0
    }
  },
  emits: ['page-change'],
  setup(props, { emit }) {
    const handlePageChange = (page) => {
      emit('page-change', page)
    }

    return {
      handlePageChange
    }
  }
}
</script>

<style scoped>
.data-table {
  width: 100%;
}

.table-header {
  margin-bottom: 20px;
}

.table-footer {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>