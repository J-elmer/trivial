<template>
  <div>
    <b-nav tabs align="center">
      <b-nav-item disabled><span>Trivial</span></b-nav-item>
      <b-nav-item disabled>Question {{ nrTotalAnswers }} / {{ nrTotalQuestions }} </b-nav-item>
      <b-nav-item disabled>Correct: {{ nrCorrectAnswers }} </b-nav-item>
    </b-nav>
  </div>
</template>

<script>
export default {
  props: {
    nrCorrectAnswers: Number,
    nrTotalAnswers: Number,
    nrTotalQuestions:  Number
  }
}
</script>

