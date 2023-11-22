package com.eme.model.entity;

import com.eme.model.entity.enums.FormatType;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
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
@Entity(name = "attachmentEntity")
@Table(name = "attachment_table")
public class Attachment extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonbProperty("کد")
    private Long id;

    @JsonbProperty("نام ضمیمه")
    @Column(name = "name", columnDefinition = "NVARCHAR2(25)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\/\\\\.,\\d\\s\\-]{3,25}$*", message = "AttachmentName_Error")
    private String name;

    @JsonbProperty("آدرس ضمیمه")
    @Column(name = "path", columnDefinition = "NVARCHAR2(200)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z:.\\/\\\\\\s\\d\\-]{3,200}$*", message = "AttachmentPath_Error")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "format_type")
    @JsonbProperty("فرمت ضمیمه")
    private FormatType formatType;

}
