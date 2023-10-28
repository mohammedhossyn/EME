package com.eme.model.entity;

import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.Status;
import com.google.gson.Gson;
import jakarta.json.bind.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

@MappedSuperclass
public class Base {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonbProperty("وضعیت")
    private Status status;

    @JsonbTransient
    @Version
    @Column(name = "b_version_id", length = 1)
    private Long versionId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
