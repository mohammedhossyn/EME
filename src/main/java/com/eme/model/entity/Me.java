package com.eme.model.entity;

import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.Status;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
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
@Named
@Dependent
@Entity(name = "myEntity")
@Table(name = "my_table")
public class Me extends Base{

    @Id
    @JsonbProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonbProperty("نام")
    @Column(name = "first_name", columnDefinition = "NVARCHAR2(25)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Zآ-ی\\s]{3,25}$*", message = "FirstName_Error")
    private String firstName;

    @JsonbProperty("نام خانوادگی")
    @Column(name = "last_name", columnDefinition = "NVARCHAR2(25)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Zآ-ی\\s]{3,25}$*", message = "LastName_Error")
    private String lastName;

    @JsonbProperty("شماره تماس")
    @Column(name = "phone", columnDefinition = "NVARCHAR2(11)")
    @Pattern(regexp = "^\\d{11}$", message = "Phone_Error")
    private String phone;

    @JsonbProperty("ایمیل")
    @Column(name = "eamil", columnDefinition = "NVARCHAR2(40)")
    @NotBlank
    @Email(message = "Email_Error")
    private String email;

    @JsonbProperty("کاربری گیت هاب")
    @Column(name = "github_username", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z_\\.]{3,30}$*", message = "GitHub_Error")
    private String github;

    @JsonbProperty("کاربری تلگرام")
    @Column(name = "telegram_username", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z-_\\d]{3,30}$*", message = "Telegram_Error")
    private String telegram;

    @JsonbProperty("لینک لینکدین")
    @Column(name = "linkedin_url", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z-_\\d]{3,30}$", message = "Linkedin_Error")
    private String linkedin;

    @JsonbProperty("کاربری اینستاگرام")
    @Column(name = "instagram_username", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z_\\.\\d]{3,30}$*", message = "Instagram_Error")
    private String instagram;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @JsonbProperty("نقش")
    private Role role;

    @JsonbProperty("تاریخ تولد")
    @Column(name = "birthday", columnDefinition = "NVARCHAR2(10)")
    @NotBlank
    @Pattern(regexp = "[/\\\\\\d]{8,10}$*", message = "MyBirthday_Error")
    private String birthday;

    @JsonbProperty("سمت شغلی")
    @Column(name = "job_position", columnDefinition = "NVARCHAR2(40)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\s\\d]{5,40}$*", message = "MyJob_Error")
    private String jobPosition;

    @OneToOne(cascade = CascadeType.MERGE)
    @JsonbProperty("پروفایل")
    private Attachment attachment;

    @JsonbProperty("خلاصه درباره من")
    @Column(name = "short_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\d,.\\n]{10,1000}$*", message = "ShortAboutMe_Error")
    private String shortAboutMeContent;

    @JsonbProperty("درباره من")
    @Column(name = "complete_about_me_content", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>\\d,.:'?!\\n]{100,2500}$*")
    private String completeAboutMeContent;

    @PrePersist
    public void setRoleAndStatus(){
        this.setRole(Role.ME);
        this.setStatus(Status.Active);
    }

}
