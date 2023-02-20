package com.apistone.nextflix.dto;

import lombok.Data;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.dto
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:56 PM
 * <p>
 * Use: DTO for {@link com.apistone.nextflix.entity.Channel} entity.
 */
@Data
public class ChannelDto {
    private Long channelId;
    private Long channelNumber;
    private String channelName;
    private boolean standard = false;
    private boolean preferred = false;
    private boolean basic = false;
    private boolean isPremiumPackage  = false;
    private boolean isActive = true;
    private Long createdOn;
    private Long updatedOn;
}
