package com.eme.controller.faces;

import com.eme.controller.AttachmentController;
import com.eme.controller.MeController;
import com.eme.controller.attachments.AttachmentSaver;
import com.eme.controller.beans.MyBean;
import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.session.FacesSessionMap;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.enums.FormatType;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Named
@RequestScoped
public class MyBeanActions {

    @Inject
    private AttachmentController attachmentController;

    @Inject
    private AttachmentSaver attachmentSaver;

    @Inject
    private MeController meController;

    @Inject
    private FacesSessionMap facesSessionMap;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private Part uploadedImage;
    private String message;

    public void edit(MyBean editedBean) {
        Attachment attachment = (Attachment) attachmentController.findById(2L).get(TransactionStatus.Done);
        Map<TransactionStatus, Object> result = meController.edit(1L, editedBean.getFirstName(),
                editedBean.getLastName(), editedBean.getPhone(), editedBean.getEmail(), editedBean.getGithub(),
                editedBean.getTelegram(), editedBean.getLinkedin(), editedBean.getInstagram(), editedBean.getBirthday(),
                editedBean.getJobPosition(), attachment, editedBean.getShortAboutMeContent(), editedBean.getCompleteAboutMeContent());
        if (result.get(TransactionStatus.Done) != null)
            System.out.println(result.get(TransactionStatus.Done));
        else if (result.get(TransactionStatus.Error) != null)
            System.out.println(result.get(TransactionStatus.Error));
        else System.out.println(result.get(TransactionStatus.Exception));
    }

    public void uploadImage() {
        Map<String, Object> attachmentInfo = attachmentSaver.upload(uploadedImage, "Me");
        if(attachmentInfo != null) {
            Map<TransactionStatus, Object> result = attachmentController.edit(Status.Active,2L,
                    attachmentInfo.get("name").toString(), attachmentInfo
                            .get("path").toString(), (FormatType) attachmentInfo.get("format"));
            if (result.get(TransactionStatus.Done) != null)
                System.out.println(result.get(TransactionStatus.Done));
            else if (result.get(TransactionStatus.Error) != null)
                System.out.println(result.get(TransactionStatus.Error));
            else System.out.println(result.get(TransactionStatus.Exception));
        }else{
            message = attachmentSaver.getMessage();
        }
    }
}
