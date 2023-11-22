package com.eme.model.entity;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.eme.model.entity.Attachment;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Named
@Dependent
@Entity(name = "projectEntity")
@Table(name = "project_table")
public class Project extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonbProperty("کد")
    private Long id;

    @JsonbProperty("نام")
    @Column(name = "name", columnDefinition = "NVARCHAR2(30)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\d\\s]{3,30}$*", message = "ProjectName_Error")
    private String name;

    @JsonbProperty("مقدار انجام شده")
    @Column(name = "progress", columnDefinition = "NVARCHAR2(50)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\d\\s]{3,50}$*", message = "ProjectProgress_Error")
    private String progress;

    @JsonbProperty("تیتر")
    @Column(name = "title", columnDefinition = "NVARCHAR2(50)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\d\\s]{3,50}$*", message = "ProjectTitle_Error")
    private String title;

    @JsonbProperty("توضیحات")
    @Column(name = "explanation", columnDefinition = "CLOB")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s<>/\\\\\\d]{10,1000}$*", message = "ProjectExplanation_Error")
    private String explanation;

    @OneToOne
    @JsonbProperty("عکس")
    private Attachment attachment;
}
