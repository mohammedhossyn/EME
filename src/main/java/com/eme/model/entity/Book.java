package com.eme.model.entity;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
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
@Entity(name = "bookEntity")
@Table(name = "book_table")
@NamedQueries({
        @NamedQuery(name = "book.findAllActive",
        query = "SELECT b FROM bookEntity b where b.status =: status")
})
public class Book extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonbProperty("کد")
    private Long id;

    @JsonbProperty("تیتر")
    @Column(name = "title", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\d\\s]{3,60}$*", message = "BookTitle_Error")
    private String title;

    @JsonbProperty("نام")
    @Column(name = "name", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z,\\d\\s]{3,60}$*", message = "BookName_Error")
    private String name;

    @JsonbProperty("نویسنده")
    @Column(name = "writer", columnDefinition = "NVARCHAR2(60)")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\.,\\d\\s]{3,60}$*", message = "BookWriter_Error")
    private String writer;

    @JsonbProperty("توضیحات")
    @Column(name = "explanation", columnDefinition = "Clob")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z.'/\\\\,\\d\\s]{3,2000}$*", message = "BookExplanation_Error")
    private String explanation;

    @JsonbProperty("لینک گوگل")
    @Column(name = "google_link", columnDefinition = "Clob")
    @NotBlank
    @Pattern(regexp = ".{3,500}$*", message = "BookGoogleLink_Error")
    private String googleLink;

    @OneToOne
    @JsonbProperty("ضمیمه")
    private Attachment attachment;

}
