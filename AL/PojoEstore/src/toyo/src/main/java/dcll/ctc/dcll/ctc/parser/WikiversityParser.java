package dcll.ctc.dcll.ctc.parser;

import java.util.regex.Pattern;

import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.Quiz;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

/**
 * @author alpha oumar binta diallo
 */
public class WikiversityParser implements Parser {

    /**
     * A correct answer.
     */
    private static final char CORRECT_ANSWER = '+';
    /**
     * A bad answer.
     */
    @SuppressWarnings("unused")
    private static final char BAD_ANSWER = '-';
    /**
     * Multiple choice.
     */
    private static final String MULTIPLE_CHOICE = "[]";
    /**
     * Exclusive choice.
     */
    private static final String EXCLUSIVE_CHOICE = "()";
    /**
     * Question type.
     */
    private static final String QUESTION_TYPE_REG = "|type=\"";
    /**
     * REGEX representing the end of the question.
     */
    private static final String QUESTION_END_REG = "\"}\n";
    /**
     * Line separator.
     */
    private static final String LINE_SEPARATOR = "\n";
    /**
     * String containing the questions to be parsed.
     */
    private String input = "";
    /**
     * Quiz to create with the input String.
     */
    private Quiz quiz;

    /**
     * This method parses the input and returns the generated quiz.
     * @throws NoInputException If an input or output exception occurred
     * @throws BadSyntaxException If an input or output exception occurred
     * @return Quiz
     */
    public Quiz parse() throws NoInputException, BadSyntaxException {
        if (input.isEmpty()) {
            throw new NoInputException("The Input is empty");
        }
        DefaultQuiz defQuiz = new DefaultQuiz();
        for (String str : splitQuestions()) {
            checkInputFormat(str);
            DefaultQuestion question = new DefaultQuestion();
            question.setTitle(getQuestionTitle(str));
            question.setQuestionType(getQuestionType(str));
            question.addAnswerBlock(getAnswerBlock(str));
            defQuiz.addQuestion(question);
        }
        quiz = defQuiz;
        return quiz;
    }

    /**
     * This method return the quiz.
     * @return Quiz
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * This method set the input of the parser.
     * @param str the String to set
     */
    public void setInput(final String str) {
        this.input = str;
    }

    /**
     * This method split the input to separate several questions.
     * @return String array
     */
    public String[] splitQuestions() {
        return input.split(LINE_SEPARATOR + LINE_SEPARATOR);
    }

    /**
     * This method check the format of a input.
     * @throws BadSyntaxException If an input or output exception occurred
     * @param str the String to check.
     */
    public void checkInputFormat(final String str)
            throws BadSyntaxException {
        String answerFormat = "[\\+-] (\\w|\\p{Punct})+";
        Pattern pAnswer = Pattern.compile("\\}" + LINE_SEPARATOR + "("
                + answerFormat + "" + LINE_SEPARATOR + ")*" + answerFormat,
                Pattern.DOTALL);
        Pattern pBraces = Pattern.compile("^\\{.*\\}", Pattern.DOTALL);
        Pattern ptype = Pattern.compile("\\|type=\"(\\(\\)|\\[\\])\"\\}");
        Pattern pQuestionText = Pattern.compile("\\{.+\\|", Pattern.DOTALL);
        if (!pBraces.matcher(str).find()) {
            throw new BadSyntaxException("Format error," + " braces missing");
        }
        if (!ptype.matcher(str).find()) {
            throw new BadSyntaxException("Format error,"
                    + " question type missing");
        }
        if (!pQuestionText.matcher(str).find()) {
            throw new BadSyntaxException("Format error,"
                    + " question text missing");
        }
        if (!pAnswer.matcher(str).find()) {
            throw new BadSyntaxException("Format error," + " invalid answers");
        }
        if (str.substring(str.indexOf("|type")).indexOf('+') < 0) {
            throw new BadSyntaxException("Format error,"
                    + " A good answer is missing");
        }
    }

    /**
     * This method return the question type.
     * @param str a String
     * @return QuestionType
     */
    public QuestionType getQuestionType(final String str) {
        String type = str.substring(str.indexOf(QUESTION_TYPE_REG)
                + QUESTION_TYPE_REG.length(), str.indexOf(QUESTION_END_REG));
        if (type.equals(EXCLUSIVE_CHOICE)) {
            return QuestionType.ExclusiveChoice;
        } else if (type.equals(MULTIPLE_CHOICE)) {
            return QuestionType.MultipleChoice;
        } else {
            return QuestionType.Undefined;
        }
    }

    /**
     * This method return the title of the question.
     * @param str a String
     * @return String
     */
    public String getQuestionTitle(final String str) {
        return str.substring(1, str.indexOf(QUESTION_TYPE_REG));
    }

    /**
     * This method return the formatted AnswerBlock of the input.
     * @param str a String containing the questions
     * @return AnswerBlock
     */
    public AnswerBlock getAnswerBlock(final String str) {
        DefaultAnswerBlock answerBlock = new DefaultAnswerBlock();
        int correctAnswerNber = 0;

        String responseBlock = str.substring(
                str.indexOf(QUESTION_END_REG)
                + QUESTION_END_REG.length());
        String[] answersArray = responseBlock.split(LINE_SEPARATOR);

        for (String answer : answersArray) {
            if (answer.charAt(0) == CORRECT_ANSWER) {
                correctAnswerNber++;
            }
        }
        for (String answer : answersArray) {
            DefaultAnswer ans = new DefaultAnswer();
            ans.setTextValue(answer.substring(2));
            if (answer.charAt(0) == CORRECT_ANSWER) {
                ans.setPercentCredit(1.0f / correctAnswerNber);
            } else {
                ans.setPercentCredit(0f);
            }
            answerBlock.addAnswer(ans);
        }
        return answerBlock;
    }

}
