package org.tsaap.questions;

/**
 * @author franck Silvestre
 */
public interface QuizContentHandler {

    /**
     * Receive notification of the beginning of a quiz.
     */
    void onStartQuiz();

    /**
     * Receive notification of the end of a quiz.
     */
    void onEndQuiz();

    /**
     * Receive notification of the beginning of a question.
     */
    void onStartQuestion();

    /**
     * Receive notification of the end of a question.
     */
    void onEndQuestion();

   /**
    * str.
    * @param str the str
    */
    void onString(String str);

    /**
     * Receive notification of the beginning of a title.
     */
    void onStartTitle();

    /**
     * Receive notification of the end of a title.
     */
    void onEndTitle();

    /**
     * Receive notification of the beginning of an answer block.
     */
    void onStartAnswerBlock();

    /**
     * Receive notification of the end of an answer block.
     */
    void onEndAnswerBlock();

    /**
     * Set the question
     * @param question
     *            the question
     */

    /**
     * Receive notification of the beginning of an answer.
     * @param prefix
     *            the question
     */
    void onStartAnswer(String prefix);

    /**
     * Receive notification of the end of an answer.
     */
    void onEndAnswer();

    /**
     * Notification of the beginning of a credit specification.
     */
    void onStartAnswerCredit();

    /**
     * Notification of the end of a credit specification.
     */
    void onEndAnswerCredit();

    /**
     * Receive notification of the beginning feedback.
     */
    void onStartAnswerFeedBack();

    /**
     * Receive notification of the end of a feedback.
     */
    void onEndAnswerFeedBack();

}
