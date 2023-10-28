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

@Entity(name = "bookEntity")
@Table(name = "book_table")
public class Book extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonbProperty("کد")
    private Long id;

    @JsonbProperty("تیتر")
    @Column(name = "title", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\d\\s]{3,60}$*", message = "BookTitle_Error")
    private String title;

    @JsonbProperty("نام")
    @Column(name = "name", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\d\\s]{3,60}$*", message = "BookName_Error")
    private String name;

    @JsonbProperty("نویسنده")
    @Column(name = "writer", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\,\\d\\s]{3,60}$*", message = "BookWriter_Error")
    private String writer;

    @JsonbProperty("توضیحات")
    @Column(name = "explanation", columnDefinition = "Clob")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\,\\d\\s]{3,2000}$*", message = "BookExplanation_Error")
    private String explanation;

    @OneToOne
    @JsonbProperty("ضمیمه")
    private Attachment attachment;

}
