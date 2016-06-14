package com.tiv.ocpapp.datagenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Main {
    private static final int DB_VERSION = 1;

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DB_VERSION, "com.tiv.ocpapp.model_dao");

        Entity answer = schema.addEntity("Answer");
        answer.addIdProperty();
        answer.addStringProperty("body");
        answer.addBooleanProperty("isCorrect");

        Entity question = schema.addEntity("Question");
        question.addIdProperty();
        question.addStringProperty("text");
        question.addStringProperty("description");


        Property questionId = answer.addLongProperty("questionId").getProperty();
        answer.addToOne(question, questionId);


        ToMany questionToAnswers = question.addToMany(answer, questionId);
        questionToAnswers.setName("answers");

        new DaoGenerator().generateAll(schema, "../app/src/main/java");


    }
}
