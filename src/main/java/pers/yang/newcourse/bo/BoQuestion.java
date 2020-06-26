package pers.yang.newcourse.bo;

import lombok.Data;
import pers.yang.newcourse.entity.*;

import java.io.Serializable;
import java.util.List;
@Data
public class BoQuestion implements Serializable {
    private Question question;
    private List<Answer> answerList;
    private List<Choice> choiceList;
    private List<Img> imgList;
    private List<StudentAnswer> studentAnswerList;

}
