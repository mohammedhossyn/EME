package com.eme.controller.faces;

import com.eme.controller.AttachmentController;
import com.eme.controller.BookController;
import com.eme.controller.MeController;
import com.eme.controller.attachments.AttachmentSaver;
import com.eme.controller.beans.BookBean;
import com.eme.controller.exceptions.ExceptionWrapper;
import com.eme.controller.session.FacesSessionMap;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Book;
import com.eme.model.entity.enums.FormatType;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Named
@RequestScoped
public class BookBeanActions {

    @Inject
    private AttachmentSaver attachmentSaver;

    @Inject
    private BookController bookController;

    @Inject
    private FacesSessionMap facesSessionMap;

    @Inject
    private ExceptionWrapper exceptionWrapper;

    private String name;
    private String path;
    private FormatType formatType;
    private String message;

    public void save(BookBean bookBean) {
        uploadImage(bookBean.getUploadedImage());
        Map<TransactionStatus, Object> result = bookController.save(bookBean.getTitle(), bookBean.getName(), bookBean.getWriter(),
                bookBean.getExplanation(), bookBean.getGoogleLink(),
                Attachment.builder().status(Status.Active).name(name).path(path).formatType(formatType).build());
        if (result.get(TransactionStatus.Done) != null)
            System.out.println(result.get(TransactionStatus.Done));
        else if (result.get(TransactionStatus.Error) != null)
            System.out.println(result.get(TransactionStatus.Error));
        else System.out.println(result.get(TransactionStatus.Exception));

    }

    public void edit(BookBean bookBean) {
        uploadImage(bookBean.getUploadedImage());
        Book book = (Book) bookController.findById(bookBean.getId()).get(TransactionStatus.Done);
        book.getAttachment().setId(bookBean.getAttachment().getId());
        book.getAttachment().setName(name);
        book.getAttachment().setPath(path);
        book.getAttachment().setFormatType(formatType);
        book.setTitle(bookBean.getTitle());
        book.setName(bookBean.getName());
        book.setWriter(bookBean.getWriter());
        book.setExplanation(bookBean.getExplanation());
        book.setGoogleLink(bookBean.getGoogleLink());
        Map<TransactionStatus, Object> result = bookController.edit(Status.Active, book.getId(), book.getTitle(),
                book.getName(), book.getWriter(), book.getExplanation(), book.getGoogleLink(),
                book.getAttachment());
        if (result.get(TransactionStatus.Done) != null)
            System.out.println(result.get(TransactionStatus.Done));
        else if (result.get(TransactionStatus.Error) != null)
            System.out.println(result.get(TransactionStatus.Error));
        else System.out.println(result.get(TransactionStatus.Exception));
    }

    public void delete(Long id){
        Map<TransactionStatus, Object> result = bookController.logicalRemove(id);
        if (result.get(TransactionStatus.Done) != null)
            System.out.println(result.get(TransactionStatus.Done));
        else if (result.get(TransactionStatus.Error) != null)
            System.out.println(result.get(TransactionStatus.Error));
        else System.out.println(result.get(TransactionStatus.Exception));
    }

    public void uploadImage(Part uploadedImage) {
        Map<String, Object> attachmentInfo = attachmentSaver.upload(uploadedImage, "Books");
        if (attachmentInfo != null) {
            name = attachmentInfo.get("name").toString();
            path = attachmentInfo.get("path").toString();
            formatType = (FormatType) attachmentInfo.get("format");
        } else {
            message = attachmentSaver.getMessage();
        }
    }


}
