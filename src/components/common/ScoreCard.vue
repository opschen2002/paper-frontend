<template>
  <el-card class="score-card">
    <template #header>
      <div class="card-header">
        <span>{{ title }}</span>
        <slot name="header-right"></slot>
      </div>
    </template>
    <el-descriptions :column="2" border>
      <el-descriptions-item label="身高">
        {{ scoreData.height }} cm
      </el-descriptions-item>
      <el-descriptions-item label="体重">
        {{ scoreData.weight }} kg
      </el-descriptions-item>
      <el-descriptions-item label="50米跑">
        {{ scoreData.run50m }} 秒
      </el-descriptions-item>
      <el-descriptions-item label="立定跳远">
        {{ scoreData.longJump }} cm
      </el-descriptions-item>
      <el-descriptions-item label="肺活量">
        {{ scoreData.vitalCapacity }} ml
      </el-descriptions-item>
      <el-descriptions-item label="体前屈">
        {{ scoreData.sitAndReach }} cm
      </el-descriptions-item>
      <template v-if="scoreData.gender === 'male'">
        <el-descriptions-item label="1000米跑">
          {{ scoreData.run1000m }} 秒
        </el-descriptions-item>
        <el-descriptions-item label="引体向上">
          {{ scoreData.pullUp }} 个
        </el-descriptions-item>
      </template>
      <template v-else>
        <el-descriptions-item label="800米跑">
          {{ scoreData.run800m }} 秒
        </el-descriptions-item>
        <el-descriptions-item label="仰卧起坐">
          {{ scoreData.sitUp }} 个
        </el-descriptions-item>
      </template>
      <el-descriptions-item label="总分">
        {{ scoreData.totalScore }}
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<script>
export default {
  name: 'ScoreCard',
  props: {
    title: {
      type: String,
      default: '体测成绩'
    },
    scoreData: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
.score-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>