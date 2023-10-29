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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonbProperty("کد")
    private Long id;

    @JsonbProperty("تاریخ تولد")
    @Column(name = "birthday", columnDefinition = "NVARCHAR2(10)")
    @NotBlank
    @Pattern(regexp = "[\\\\d]{8,10}$*", message = "MyBirthday_Error")
    private String birthday;

    @JsonbProperty("سمت شغلی")
    @Column(name = "job_position", columnDefinition = "NVARCHAR2(40)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s\\d]{5,40}$*", message = "MyJob_Error")
    private String jobPosition;

    @OneToOne
    @JsonbProperty("پروفایل")
    private Attachment attachment;

    @JsonbProperty("خلاصه درباره من")
    @Column(name = "short_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\d]{10,1000}$*", message = "ShortAboutMe_Error")
    private String shortAboutMeContent;

    @JsonbProperty("درباره من")
    @Column(name = "complete_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\\\d]{100,2500}$*", message = "CompleteAboutMe_Error")
    private String completeAboutMeContent;


}
