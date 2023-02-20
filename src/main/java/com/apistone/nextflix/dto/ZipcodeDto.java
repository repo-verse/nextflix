package com.apistone.nextflix.dto;

import lombok.*;

import java.util.Set;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.dto
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:56 PM
 * <p>
 * Use: DTO for {@link com.apistone.nextflix.entity.Zipcode} entity.
 */
@Data
public class ZipcodeDto {
    private Long zipcodeId;
    private int zipcode;
    private Set<ChannelDto> channels;
    private boolean isActive = true;
    private Long createdOn;
    private Long updatedOn;
}
