package com.apistone.nextflix.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.entity
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/20/2023
 * Time: 11:02 PM
 * <p>
 * Use: Audit model for all the audit logs.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdOn", "updatedOn"},
        allowGetters = true
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditModel {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdOn;


    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedOn;

    @PrePersist
    public void onCreate()
    {
        createdOn = updatedOn = new Date();
    }

    @PreUpdate
    public void onUpdate()
    {
        updatedOn = new Date();
    }
}
