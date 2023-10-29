package com.eme.model.entity;

import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.Status;
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

public class Person extends Base {

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
    @Pattern(regexp = "[a-zA-Z]{3,30}$*", message = "GitHub_Error")
    private String github;

    @JsonbProperty("کاربری تلگرام")
    @Column(name = "telegram_username", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z\\d]{3,30}$*", message = "Telegram_Error")
    private String telegram;

    @JsonbProperty("لینک لینکدین")
    @Column(name = "linkedin_url", columnDefinition = "NVARCHAR2(80)")
    @Pattern(regexp = "/^(http(s)?:\\/\\/)?([\\w]+\\.)?linkedin\\.com\\/(pub|in|profile)\\/([-a-zA-Z0-9]+)\\/*/gm", message = "Linkedin_Error")
    private String linkedin;

    @JsonbProperty("کاربری اینستاگرام")
    @Column(name = "instagram_username", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "[a-zA-Z\\d]{3,30}$*", message = "Instagram_Error")
    private String instagram;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @JsonbProperty("نقش")
    private Role role;

    @PrePersist
    public void setRoleAndStatus(){
        this.setRole(Role.Unknown);
        this.setStatus(Status.Active);
    }
}
