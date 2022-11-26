package com.example.memorygame.Object.AD8Object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AD8Form {
    List<AD8_Question> questionList;
    List<String> question = Arrays.asList(
            "例如落入圈套或騙局、財務上不好的決定、買了對受禮者不合宜的禮物",
            "對活動和嗜好的興趣降低",
            "重複相同的問題、故事和陳述",
            "學習如何使用工具、設備、和小器具上有困難",
            "忘記正確的月份和年份",
            "處理複雜的財務上有困難",
            "記住約會的時間有困難",
            "有持續的思考和記憶方面的問題");
    public AD8Form() {
        this.questionList = new ArrayList<>();
        for (int i = 0;i<question.size();i++){
            questionList.add(new AD8_Question(i,question.get(i),generateAnswer()));
        }

    }
    public List<AD8_Answer> generateAnswer(){
        return Arrays.asList(
                new AD8_Answer(1, "是"),
                new AD8_Answer(0,"不是"),
                new AD8_Answer(0,"不知道"));
    }
    public List<AD8_Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<AD8_Question> questionList) {
        this.questionList = questionList;
    }
    public int caculated(){
        return this.questionList.stream().mapToInt(AD8_Question::getScore).sum();
    }
}
