package dcll.ctc.dcll.ctc.parser;

import org.tsaap.questions.Quiz;

/**
 * @author alpha oumar binta diallo
 * Parser is the interface that allows to analyze a Question
 * */

public interface Parser {
    /**
     * This method parses the input and returns the generated quiz.
     * @throws NoInputException If an input or output exception occurred
     * @throws BadSyntaxException If an input or output exception occurred
     * @return Quiz
     */
    Quiz parse() throws NoInputException, BadSyntaxException;

    /**
     * This method set the input of the parser.
     * @param str the String to set.
     */
    void setInput(String str);

    /**
     * This method return the quiz.
     * @return Quiz
     */
    Quiz getQuiz();
}
