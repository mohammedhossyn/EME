package com.eme.model;

import com.eme.model.entity.Me;
import com.eme.model.entity.Person;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

//        ServiceCreatorService.getService().create(
//                "attachment",
//                "./src/main/java/com/eme/model/service");

//                ControllerCreatorService.getService().create(
//                        "com.eme.model.entity.Attachment",
//                "attachment",
//                "./src/main/java/com/eme/controller");

//        System.out.println(Arrays.toString(Person.class.getDeclaredFields()));
//        List<String> strings;
//        List<String> types;
//        List<String> names;
//        String fieldType;
//        String fieldName;
//        String fieldsWithCommaForSave = "";
//        String fieldsWithDotsForSave = "";
//        String fieldsWithCommaForEdit = "";
//        String fieldsWithDotsForEdit = "";
//
//        Class b = Class.forName("com.eme.model.entity.Me");
//
//        Field[] ownFields = b.getDeclaredFields();
//        Field[] superFields = b.getSuperclass().getDeclaredFields();
//
//        List ownFieldsWithoutId = new ArrayList<>(Arrays.asList(ownFields));
//        ownFieldsWithoutId.remove(0);
//
//        List allFields = new ArrayList<>(Arrays.asList(superFields));
//        allFields.addAll(Arrays.asList(ownFields));
//
//        for (Object field : ownFieldsWithoutId) {
//            String stringField = field.toString();
//            strings = List.of(stringField.split(" "));
//            types = List.of(strings.get(1).split("\\."));
//            names = List.of(strings.get(2).split("\\."));
//            fieldType = types.get((types.toArray().length) - 1);
//            fieldName = names.get((names.toArray().length) - 1);
//
//            if ((ownFieldsWithoutId.size() - 1) != ownFieldsWithoutId.indexOf(field))
//                fieldsWithCommaForSave += fieldType + " " + fieldName + ", " + System.lineSeparator();
//            else fieldsWithCommaForSave += fieldType + " " + fieldName;
//
//            if ((ownFieldsWithoutId.size() - 1) != ownFieldsWithoutId.indexOf(field))
//                fieldsWithDotsForSave += " ." + fieldName + "()" + System.lineSeparator();
//            else fieldsWithDotsForSave += " ." + fieldName + "()";
//
//        }
//        for (Object field : allFields) {
//            String stringField = field.toString();
//            strings = List.of(stringField.split(" "));
//            types = List.of(strings.get(1).split("\\."));
//            names = List.of(strings.get(2).split("\\."));
//            fieldType = types.get((types.toArray().length) - 1);
//            fieldName = names.get((names.toArray().length) - 1);
//
//            if ((allFields.size() - 1) != allFields.indexOf(field))
//                fieldsWithCommaForEdit += fieldType + " " + fieldName + ", " + System.lineSeparator();
//            else fieldsWithCommaForEdit += fieldType + " " + fieldName;
//
//            if ((allFields.size() - 1) != allFields.indexOf(field))
//                fieldsWithDotsForEdit += " ." + fieldName + "()" + System.lineSeparator();
//            else fieldsWithDotsForEdit += " ." + fieldName + "()";
//
//        }
//        System.out.println(fieldsWithCommaForSave);
//        System.out.println("-----------------");
//        System.out.println(fieldsWithDotsForSave);
//        System.out.println("-----------------");
//        System.out.println(fieldsWithCommaForEdit);
//        System.out.println("-----------------");
//        System.out.println(fieldsWithDotsForEdit);

    }
}
