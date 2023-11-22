package com.eme.controller.attachments;

import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.session.FacesSessionMap;
import com.eme.model.entity.MessageVO;
import com.eme.model.entity.enums.FormatType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

@Named
@RequestScoped
public class AttachmentSaver {

    @Inject
    private ExceptionWrapper exceptionWrapper;

    @Inject
    private FacesSessionMap facesSessionMap;

    private FormatType formatType;
    private File savedFile;
    private String name = "";
    Map<String, Object> attachmentInfo = new HashMap<>();
    private String message;

    String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/../resources/Images/");

    public Map<String, Object> upload(Part part, String folderName) {
        fullPath = fullPath + folderName;
        name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        formatType = getFormat(name);
        if (formatType != null) {
            try (InputStream input = part.getInputStream()) {
                savedFile = new File(fullPath, name);
                Files.copy(input, savedFile.toPath());
                attachmentInfo.put("name", name.split("\\.")[0]);
                attachmentInfo.put("format", formatType);
                String path = "../resources/Images/" + folderName + "/" + name;
                attachmentInfo.put("path", path);
            } catch (Exception e) {
                exceptionWrapper.getMessage(e);
            }
            return attachmentInfo;
        } else {
            message = "Invalid format type!";
            return null;
        }
    }

    public FormatType getFormat(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        FormatType formatType = null;
        switch (extension) {
            case "jpg":
                formatType = FormatType.jpg;
                break;
            case "jpeg":
                formatType = FormatType.jpeg;
                break;
            case "png":
                formatType = FormatType.png;
                break;
        }
        return formatType;
    }


}
