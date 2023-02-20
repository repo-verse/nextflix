package com.apistone.nextflix.entity;

import com.apistone.nextflix.constant.DbConstant;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.entity
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/20/2023
 * Time: 11:02 PM
 * <p>
 * Use: Zipcode entity used to store zipcode data of particular location.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = DbConstant.ZIPCODE_TABLE)
public class Zipcode extends AuditModel{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long zipcodeId;

    @Column(nullable = false, unique = true)
    private int zipcode;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = DbConstant.CHANNEL_ZIPCODE_MAP,
            joinColumns = @JoinColumn(name = DbConstant.ZIPCODE_ID),
            inverseJoinColumns = @JoinColumn(name = DbConstant.CHANNEL_ID)
    )
    private Set<Channel> channels;

    private boolean isActive = true;
}
