<template>
  <div>
    <b-modal ref="my-modal" hide-footer hide-header>
      <div class="d-block text-center">
        <h3>So, how did you do?</h3>
        <p>You answered {{ nrCorrectAnswers }} questions correctly out of {{ nrTotalQuestions }}</p>
        <p>That's... {{ judgement }}</p>
        <b-button block @click="restartQuiz">Play Again</b-button>
      </div>
    </b-modal>
  </div>
</template>

<script>
const RESULT_TRESHOLD = 0.6;
const POSITIVE_JUDGEMENT = 'actually pretty good!';
const NEGATIVE_JUDGEMENT = 'rather bad...';

export default {
  props: {
    nrCorrectAnswers: Number,
    nrTotalQuestions: Number,
    restartQuiz: Function
  },
  data() {
    return {
      judgement: ''
    }
  },
  mounted() {
    if (this.nrCorrectAnswers / this.nrTotalQuestions >= RESULT_TRESHOLD) {
      this.judgement = POSITIVE_JUDGEMENT;
    } else {
      this.judgement = NEGATIVE_JUDGEMENT;
    }
    this.$refs['my-modal'].show();
  },
}
</script>

<style scoped>
h1 {
  margin-top: 2rem;
}
</style>
