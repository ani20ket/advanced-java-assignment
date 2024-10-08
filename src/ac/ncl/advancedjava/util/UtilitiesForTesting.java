package ac.ncl.advancedjava.util;

import ac.ncl.advancedjava.model.QuestionModel;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * just a temporary workaround to replace a DB call to get all questions
 */
public final class UtilitiesForTesting {

    private UtilitiesForTesting() {}

    /**
     * generates a List of Question objects using generalQuestions.json file
     * @return List of questions
     */
    public static List<QuestionModel> getGeneralQuestions(){
        return new ArrayList<>();
    }

    /**
     * generates a List of Question objects using revisionQuestions.json file
     * @return List of questions
     */
    public static List<QuestionModel> getRevisionQuestions(){
        return new ArrayList<>();
    }
}
