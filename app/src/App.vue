<template>
  <div id="app">
    <ProgressBar
        v-if="questions.length"
        :nrCorrectAnswers="nrCorrectAnswers"
        :nrTotalAnswers="nrTotalAnswers"
        :nrTotalQuestions="questions.length"
    />
    <b-container>
      <b-row>
        <b-col sm="6" offset="3">
          <WelcomeScreen
              v-if="!questions.length && !restart"
              :start-quiz="startQuiz"
          />
          <Question
              v-if="questions.length && index < questions.length"
              :currentQuestion="questions[index]"
              :next="next"
              :increment="increment"
          />
          <ResultModal
            v-if="questions.length && index === questions.length"
            :nr-total-questions="nrTotalAnswers"
            :nr-correct-answers="nrCorrectAnswers"
            :restart-quiz="restartQuiz"
          />
        </b-col>
      </b-row>
    </b-container>

  </div>
</template>

<script>
import ProgressBar from "./components/ProgressBar";
import WelcomeScreen from "./components/WelcomeScreen";
import Question from "./components/Question";
import ResultModal from "./components/ResultModal";

export default {
  name: 'App',
  components: {
    ProgressBar,
    WelcomeScreen,
    Question,
    ResultModal
  },
  data() {
    return {
      questions: [],
      index: 0,
      nrCorrectAnswers: 0,
      nrTotalAnswers: 0,
      restart: false
    }
  },
  methods: {
    next() {
      this.index++;
    },
    increment(isCorrect) {
      if (isCorrect) {
        this.nrCorrectAnswers++;
      }
      this.nrTotalAnswers++;
    },
    startQuiz() {
      fetch('http://localhost:8080/question/new', {
        method: 'get',
      })
          .then((response) => {
            return response.json();
          })
          .then((jsonData) => {
            this.questions = jsonData;
          })
    },
    restartQuiz() {
      this.restart = true;
      this.questions = [];
      this.index = 0;
      this.nrCorrectAnswers = 0;
      this.nrTotalAnswers = 0;
      this.startQuiz();
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

span {
  font-weight: bolder;
}

</style>
