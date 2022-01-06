<template>
  <div>
    <b-jumbotron>
      <h1>Welcome to Trivial</h1>
      <p>Answer five questions from random categories and random difficulty.</p>
      <p>Good luck!</p>
      <b-button @click="startQuiz" variant="primary">Start Quiz</b-button>
    </b-jumbotron>
  </div>
</template>

<script>
export default {
  props: {
    startQuiz: Function,
    nrOfQuestions: Number
  }
}
</script>

<style scoped>
 h1 {
   margin-top: 2rem;
 }
</style>
