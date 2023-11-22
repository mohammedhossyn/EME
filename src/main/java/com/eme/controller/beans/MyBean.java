package com.eme.controller.beans;

import com.eme.controller.MeController;
import com.eme.model.entity.Attachment;
import com.eme.model.entity.Me;
import com.eme.model.entity.enums.*;
import com.eme.model.service.MeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.*;
import jakarta.servlet.http.Part;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Named
@RequestScoped
public class MyBean {

    @Inject
    private MeController meController;

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String github;
    private String telegram;
    private String linkedin;
    private String instagram;
    private String birthday;
    private String jobPosition;
    private Attachment attachment;
    private String shortAboutMeContent;
    private String completeAboutMeContent;
    private Part uploadedImage;

    public void load() {
        Me me = (Me) meController.findById(1L).get(TransactionStatus.Done);
        setId(me.getId());
        setFirstName(me.getFirstName());
        setLastName(me.getLastName());
        setPhone(me.getPhone());
        setEmail(me.getEmail());
        setGithub(me.getGithub());
        setTelegram(me.getTelegram());
        setLinkedin(me.getLinkedin());
        setInstagram(me.getInstagram());
        setBirthday(me.getBirthday());
        setJobPosition(me.getJobPosition());
        setAttachment(me.getAttachment());
        setShortAboutMeContent(me.getShortAboutMeContent());
        setCompleteAboutMeContent(me.getCompleteAboutMeContent());
    }

    @PostConstruct
    public void init() {
        load();
    }
}
