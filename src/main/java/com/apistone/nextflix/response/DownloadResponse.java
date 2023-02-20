package com.apistone.nextflix.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.bean
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/24/2023
 * Time: 12:14 AM
 * <p>
 * Use: Response of download pdf in base64 format.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DownloadResponse {
    private String base64;
    private String fileName;
    private String type;
    private int statusCode;
    private Long timeStamp;
}
