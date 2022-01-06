<template>
  <div>
    <b-jumbotron>
        <template>
          <p>Category: <span>{{ currentQuestion.category }}</span> </p>
          <div>
            {{ currentQuestion.question }}
          </div>
        </template>

      <b-list-group>
        <b-list-group-item
            v-for="(answer, index) in currentQuestion.answers" :key="index"
            @click="selectAnswer(index)"
            :class="answerClass(index)"
        >
          {{ answer }}
        </b-list-group-item>
      </b-list-group>

        <b-button
            @click="submitAnswer"
            variant="primary"
            :disabled="selectedIndex === null || answered"
        >
          Submit</b-button>
        <b-button
            @click="next"
            variant="success"
            :disabled="!answered"
        >
          Next
        </b-button>
    </b-jumbotron>
  </div>
</template>

<script>
export default {
  props: {
    currentQuestion: Object,
    next: Function,
    increment: Function
  },
  data() {
    return {
      selectedIndex: null,
      answered: false,
      correctAnswer: null,
      isCorrect: false,
      correctIndex: null,
    }
  },
  watch: {
    currentQuestion() {
      this.selectedIndex = null;
      this.answered = false;
      this.correctAnswer = null;
    },
    correctAnswer() {
      this.correctIndex = this.currentQuestion.answers.indexOf(this.correctAnswer);
    }
  },
  methods: {
    selectAnswer(index) {
      if (!this.answered) {
        this.selectedIndex = index;
      }
    },
    submitAnswer() {
      this.checkAnswer();
      this.answered = true;
    },
    checkAnswer() {
      let data = {
        answer: this.currentQuestion.answers[this.selectedIndex],
        questionId: this.currentQuestion.questionId
      };
      fetch('http://localhost:8080/question/answer', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
          .then((response) => {
            return response.json();
          }).then((jsonData) => {
            this.increment(jsonData.answerIsCorrect);
            this.isCorrect = jsonData.answerIsCorrect;
            this.correctAnswer = jsonData.correctAnswer;
      })
    },
    answerClass(index) {
      let answerClass = '';
      if (!this.answered && this.selectedIndex === index) {
          answerClass = 'selectedAnswer'
      }
      if (this.answered && this.correctIndex === index) {
        answerClass = 'correctAnswer'
      }
      if (this.answered &&
          this.selectedIndex === index &&
          this.correctIndex !== index) {
        answerClass = 'incorrectAnswer'
      }
      return answerClass;
    }
  },
}
</script>

<style scoped>

  h3, p {
    margin-top: 2rem;
  }

  .list-group {
    margin: 1.5rem;
  }

  .list-group-item:hover {
    background-color: #eee;
    cursor: pointer;
  }

  .btn {
    margin: 0 0.75rem;
  }

  .selectedAnswer {
    background-color: lightblue;
  }

  .correctAnswer {
    background-color: lightgreen;
  }

  .incorrectAnswer {
    background-color: indianred;
  }
</style>
