package com.apistone.nextflix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.bean
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:26 PM
 * <p>
 * Use: Response for Global exception handler and also for success responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private int statusCode;
    private Long timeStamp;
    private String uri;
}
