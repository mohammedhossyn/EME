package com.eme.model.engine;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceCreatorService {

    //------SINGLETON----------------------------------------------------
    private static ServiceCreatorService service = new ServiceCreatorService();

    private ServiceCreatorService(){
    }

    public static ServiceCreatorService getService() {
        return service;
    }

    //------CREATE--------------------------------------------------------
    public void create(String entityName, String servicePackagePath) throws Exception{
        Path path = Paths.get("./src/main/java/com/eme/model/engine/service.txt");
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);

        content = content.replaceAll("&ENTITY&", entityName);
        content = content.replaceAll("&ENTITY_NAME&", entityName.toLowerCase());

        FileWriter writer = new FileWriter(servicePackagePath + "\\" + entityName + "Service.java");
        writer.write(content);
        writer.close();
    }
}
