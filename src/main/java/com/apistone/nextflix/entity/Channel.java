package com.apistone.nextflix.entity;

import com.apistone.nextflix.constant.DbConstant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.entity
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/20/2023
 * Time: 11:02 PM
 * <p>
 * Use: Channel entity used to store Nextflix channels data.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = DbConstant.CHANNEL_TABLE)
public class Channel extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long channelId;

    @Column(nullable = false, unique = true)
    private Long channelNumber;

    @NotNull
    private String channelName;

    @NotNull
    private boolean standard = false;

    @NotNull
    private boolean preferred = false;

    @NotNull
    private boolean basic = false;

    @NotNull
    private boolean isPremiumPackage  = false;

    @ManyToMany(
            mappedBy = "channels",
            fetch = FetchType.LAZY
    )
    private Set<Zipcode> zipCodeList;

    private boolean isActive = true;

}
