package com.eme.controller.beans;


import com.eme.controller.BookController;
import com.eme.controller.session.FacesSessionMap;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Book;
import com.eme.model.entity.Me;
import com.eme.model.entity.enums.Status;
import com.eme.model.entity.enums.TransactionStatus;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Named
@RequestScoped
public class BookBean {

    @Inject
    private FacesSessionMap facesSessionMap;

    @Inject
    private BookController bookController;

    private Long id;
    private String title;
    private String name;
    private String writer;
    private String explanation;
    private String googleLink;
    private Attachment attachment;
    private Part uploadedImage;


    public void loadForEdit(Long id) {
        Book book = (Book) bookController.findById(id).get(TransactionStatus.Done);
        facesSessionMap.setObjectOnSession(book, "editBook");
    }



    public List<Book> init(){
        return (List<Book>) bookController.findAllActive().get(TransactionStatus.Done);
    }



}
