
package com.refinninterview.demo.domain;

import com.refinninterview.demo.domain.enums.ContactStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity(name = "contacts")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Title is mandatory")
    private String name;

    @Column(name = "tel_no")
    @NotBlank(message = "Title is mandatory")
    private String telNo;

    @Column(name = "line_id")
    private String lineId;

    @Column(name = "callback_time")
    private Instant callBackTime;

    @Column
    @Enumerated(EnumType.STRING)
    private ContactStatusEnum status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

    @OneToOne
    private Asset asset;

}
