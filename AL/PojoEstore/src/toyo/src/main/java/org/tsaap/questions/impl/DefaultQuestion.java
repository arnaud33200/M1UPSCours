/*
 * Copyright 2013 Tsaap Development Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tsaap.questions.impl;

import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.QuestionBlock;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.TextBlock;
import java.util.ArrayList;
import java.util.List;

/**
 * @author franck Silvestre
 */
public class DefaultQuestion implements Question {
    /**
     * title.
     */
    private String title;
    /**
     * title.
     */
    private List<QuestionBlock> blockList = new ArrayList<QuestionBlock>();
    /**
     * title.
     */

    private List<AnswerBlock> answerBlockList = new ArrayList<AnswerBlock>();
    /**
     * title.
     */
    private List<TextBlock> textBlockList = new ArrayList<TextBlock>();
    /**
     * title.
     */
    private QuestionType questionType = QuestionType.Undefined;

    /**
     * Get the title of the question.
     * @return the title of the question
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the question type.
     * @return the question type
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    /**
     * Set the question type.
     * @param pquestionType
     *            the question type
     */
    public void setQuestionType(QuestionType pquestionType) {
        this.questionType = pquestionType;
    }
/**
 * settitle.
 * @param ptitle the title
 */
    public void setTitle(String ptitle) {
        this.title = ptitle;
    }

    /**
     * Get the question fragment list.
     * @return the question fragment list
     */
    public List<QuestionBlock> getBlockList() {
        return blockList;
    }

    /**
     * Add an answer fragment of the question to the question.
     * @param fragment
     *            the fragment to add
     */
    public void addAnswerBlock(AnswerBlock fragment) {
        blockList.add(fragment);
        answerBlockList.add(fragment);
    }

    /**
     * Add an answer fragment of the question to the question.
     * @param fragment
     *            the fragment to add
     */
    public void addTextBlock(TextBlock fragment) {
        blockList.add(fragment);
        textBlockList.add(fragment);
    }

    /**
     * Get answer fragment list.
     * @return the answer fragment list
     */
    public List<AnswerBlock> getAnswerBlockList() {
        return answerBlockList;
    }

    /**
     * Get the text fragment list.
     * @return the text fragment list
     */
    public List<TextBlock> getTextBlockList() {
        return textBlockList;
    }

}
