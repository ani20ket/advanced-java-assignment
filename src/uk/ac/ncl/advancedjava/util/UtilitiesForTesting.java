package uk.ac.ncl.advancedjava.util;

import uk.ac.ncl.advancedjava.model.OptionModel;
import uk.ac.ncl.advancedjava.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * just a temporary workaround to replace a DB call to get all questions
 */
public final class UtilitiesForTesting {

    private UtilitiesForTesting() {
    }

    /**
     * generates a List of Question objects
     *
     * @return List of questions
     */
    public static List<QuestionModel> getGeneralQuestions() {

        List<String> freeTextQuestions = new ArrayList<>(List.of("What is JVM?", "What is JRE?", "What is JDK?", "Default value of int?", "Keyword to inherit a class?", "What is method overloading?", "What is method overriding?", "Which class is the parent of all classes?", "Keyword to prevent inheritance?", "How many primitive types are in Java?"));
        List<String> freeTextCorrectAnswers = new ArrayList<>(List.of("Java Virtual Machine", "Java Runtime Environment", "Java Development Kit", "0", "extends", "Same name, different parameters", "Same name, same parameters", "Object", "final", "8"));
        List<QuestionModel> questionModels = new ArrayList<>();
        for (String question : freeTextQuestions) {
            QuestionModel questionModel = new QuestionModel();
            questionModel.setQuestion(question);
            questionModel.setCorrectAnswer(freeTextCorrectAnswers.get(freeTextQuestions.indexOf(question)));
            questionModels.add(questionModel);
        }

        List<String> multipleChoiceQuestions = new ArrayList<>(List.of("Which of the following is NOT a primitive data type?", "Which method is used to start a thread in Java?", "Which of these access modifiers makes a member accessible only within its own class?", "Which of these is used to handle exceptions in Java?", "Which of the following are wrapper classes for primitive types?", "What is the default value of a boolean variable?", "Which of the following statements is correct about the finally block?", "What does the continue statement do in a loop?", "Which keyword is used to create an object in Java?", "Which of the following are reserved keywords in Java?"));
        List<List<String>> multipleChoiceAnswers = new ArrayList<>(List.of(List.of("a|char|false", "b|String|true", "c|int|false", "d|boolean|false"),
                List.of("a|run()|false", "b|start()|true", "c|init()|false", "d|execute()|false"),
                List.of("a|public|false", "b|protected|false", "c|default|false", "d|private|true"),
                List.of("a|try-catch|true", "b|if-else|false", "c|for|false", "d|switch|false"),
                List.of("a|Integer|true", "b|float|true", "c|int|false", "d|Boolean|true"),
                List.of("a|true|false", "b|false|true", "c|null|false", "d|0|false"),
                List.of("a|It is mandatory in exception handling.|false", "b|It always executes.|true", "c|It is executed only when an exception is thrown.|false", "d|It is optional.|true"),
                List.of("a|Stops the loop|false", "b|Skips the current iteration|true", "c|Exits the loop|false", "d|Starts the loop again|false"),
                List.of("a|class|false", "b|Object|false", "c|new|true", "d|this|false"),
                List.of("a|volatile|true", "b|instanceof|true", "c|const|true", "d|goto|true")));
        return getQuestionModels(questionModels, multipleChoiceQuestions, multipleChoiceAnswers);
    }

    /**
     * generates a List of Question objects
     *
     * @return List of questions
     */
    public static List<QuestionModel> getRevisionQuestions() {

        List<String> freeTextQuestions = new ArrayList<>(List.of(
                "What is an interface?",
                "Default value of boolean?",
                "What is polymorphism?",
                "Keyword to create an interface?",
                "What is encapsulation?",
                "What is abstraction?",
                "What is the default value of a float?",
                "What is a final class?",
                "How many bits are used in a char?",
                "What is autoboxing?"
        ));

        List<String> freeTextCorrectAnswers = new ArrayList<>(List.of(
                "Abstract type",
                "false",
                "Many forms",
                "interface",
                "Data hiding",
                "Hiding implementation",
                "0.0",
                "Cannot be inherited",
                "16",
                "Automatic conversion"
        ));

        List<QuestionModel> questionModels = new ArrayList<>();
        for (String question : freeTextQuestions) {
            QuestionModel questionModel = new QuestionModel();
            questionModel.setQuestion(question);
            questionModel.setCorrectAnswer(freeTextCorrectAnswers.get(freeTextQuestions.indexOf(question)));
            questionModels.add(questionModel);
        }

        List<String> multipleChoiceQuestions = new ArrayList<>(List.of(
                "Which of these is NOT an access modifier in Java?",
                "Which of the following keywords is used to access a superclass constructor?",
                "Which loop is guaranteed to execute at least once?",
                "Which of these statements is true about Java?",
                "Which of the following are valid data types for switch cases?",
                "Which of the following is a reserved keyword in Java?",
                "Which of the following is NOT a feature of Java?",
                "Which of these is a marker interface?",
                "What is the default value of a reference variable?",
                "Which of the following collections is synchronized?"
        ));

        List<List<String>> multipleChoiceAnswers = new ArrayList<>(List.of(
                List.of("a|private|false", "b|protected|false", "c|public|false", "d|static|true"),
                List.of("a|this|false", "b|super|true", "c|final|false", "d|static|false"),
                List.of("a|for|false", "b|while|false", "c|do-while|true", "d|if|false"),
                List.of("a|Java is platform-dependent.|false", "b|Java supports operator overloading.|false", "c|Java supports pointers.|false", "d|Java is object-oriented.|true"),
                List.of("a|int|true", "b|float|false", "c|char|true", "d|boolean|false"),
                List.of("a|default|true", "b|void|true", "c|null|false", "d|goto|true"),
                List.of("a|Platform independence|false", "b|Memory management|false", "c|Multiple inheritance|true", "d|Security|false"),
                List.of("a|Serializable|true", "b|Cloneable|true", "c|RandomAccess|true", "d|Iterable|false"),
                List.of("a|null|true", "b|0|false", "c|false|false", "d|empty string|false"),
                List.of("a|ArrayList|false", "b|HashSet|false", "c|Vector|true", "d|LinkedList|false")
        ));

        return getQuestionModels(questionModels, multipleChoiceQuestions, multipleChoiceAnswers);

    }

    private static List<QuestionModel> getQuestionModels(List<QuestionModel> questionModels, List<String> multipleChoiceQuestions, List<List<String>> multipleChoiceAnswers) {
        for (String question : multipleChoiceQuestions) {
            QuestionModel questionModel = new QuestionModel();
            questionModel.setQuestion(question);
            questionModel.setOptions(new ArrayList<>());
            List<String> options = multipleChoiceAnswers.get(multipleChoiceQuestions.indexOf(question));
            options.forEach(op -> {
                OptionModel optionModel = OptionModel.valueOf(op);
                questionModel.getOptions().add(optionModel);
            });

            questionModels.add(questionModel);
        }

        return questionModels;
    }
}
