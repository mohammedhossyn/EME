package com.eme.model.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "myEntity")
@Table(name = "my_table")
public class Me extends Person{

    @OneToOne
    @JsonbProperty("پروفایل")
    private Attachment attachment;

    @JsonbProperty("خلاصه درباره من")
    @Column(name = "short_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\d]{10,1000}$*", message = "Short_About_Me_Error")
    private String shortAboutMeContent;

    @JsonbProperty("درباره من")
    @Column(name = "complete_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\d]{100,2500}$*", message = "Complete_About_Me_Error")
    private String completeAboutMeContent;


}
