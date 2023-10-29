package com.eme.model.engine;

import java.io.FileWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ControllerCreatorService {

    List<String> strings;
    List<String> types;
    List<String> names;
    String fieldType;
    String fieldName;
    String fieldsWithCommaForSave = "";
    String fieldsWithDotsForSave = "";
    String fieldsWithCommaForEdit = "";
    String fieldsWithDotsForEdit = "";

    //------SINGLETON----------------------------------------------------
    private static ControllerCreatorService controllerCreatorService = new ControllerCreatorService();

    private ControllerCreatorService(){
    }

    public static ControllerCreatorService getService() {
        return controllerCreatorService;
    }

    //------CREATE--------------------------------------------------------
    public void create(String className ,String entityName, String servicePackagePath) throws Exception{

        Class b = Class.forName(className);

        Field[] ownFields = b.getDeclaredFields();
        Field[] superFields = b.getSuperclass().getDeclaredFields();

        List ownFieldsWithoutId = new ArrayList<>(Arrays.asList(ownFields));
        ownFieldsWithoutId.remove(0);

        List allFields = new ArrayList<>(Arrays.asList(superFields));
        allFields.addAll(Arrays.asList(ownFields));

        for (Object field : ownFieldsWithoutId) {
            String stringField = field.toString();
            strings = List.of(stringField.split(" "));
            types = List.of(strings.get(1).split("\\."));
            names = List.of(strings.get(2).split("\\."));
            fieldType = types.get((types.toArray().length) - 1);
            fieldName = names.get((names.toArray().length) - 1);

            if ((ownFieldsWithoutId.size() - 1) != ownFieldsWithoutId.indexOf(field))
                fieldsWithCommaForSave += fieldType + " " + fieldName + ", " + System.lineSeparator();
            else fieldsWithCommaForSave += fieldType + " " + fieldName;

            if ((ownFieldsWithoutId.size() - 1) != ownFieldsWithoutId.indexOf(field))
                fieldsWithDotsForSave += " ." + fieldName + "(" + fieldName + ")" + System.lineSeparator();
            else fieldsWithDotsForSave += " ." + fieldName + "(" + fieldName + ")";

        }
        for (Object field : allFields) {
            String stringField = field.toString();
            strings = List.of(stringField.split(" "));
            types = List.of(strings.get(1).split("\\."));
            names = List.of(strings.get(2).split("\\."));
            fieldType = types.get((types.toArray().length) - 1);
            fieldName = names.get((names.toArray().length) - 1);

            if ((allFields.size() - 1) != allFields.indexOf(field))
                fieldsWithCommaForEdit += fieldType + " " + fieldName + ", " + System.lineSeparator();
            else fieldsWithCommaForEdit += fieldType + " " + fieldName;

            if ((allFields.size() - 1) != allFields.indexOf(field))
                fieldsWithDotsForEdit += " ." + fieldName + "(" + fieldName + ")" + System.lineSeparator();
            else fieldsWithDotsForEdit += " ." + fieldName + "(" + fieldName + ")";

        }

        Path path = Paths.get("./src/main/java/com/eme/model/engine/controller.txt");
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);

        content = content.replaceAll("&ENTITY&", entityName);
        content = content.replaceAll("&ENTITY_NAME&", entityName.toLowerCase());
        content = content.replaceAll("&Fields_With_Comma_Save&", fieldsWithCommaForSave);
        content = content.replaceAll("&Fields_With_Dots_Save&", fieldsWithDotsForSave);
        content = content.replaceAll("&Fields_With_Comma_Edit&", fieldsWithCommaForEdit);
        content = content.replaceAll("&Fields_With_Dots_Edit&", fieldsWithDotsForEdit);

        FileWriter writer = new FileWriter(servicePackagePath + "\\" + entityName + "Controller.java");
        writer.write(content);
        writer.close();
    }
}
