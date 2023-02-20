package com.apistone.nextflix.service;

import com.apistone.nextflix.entity.Zipcode;
import com.apistone.nextflix.response.DownloadResponse;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.exception.ZipcodeNotFoundException;
import org.apache.commons.codec.EncoderException;

import java.io.IOException;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.service.impl
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:00 PM
 * <p>
 * Use: Provide services related to zipcode.
 */
public interface ZipcodeService {

    /**
     * Get {@link com.apistone.nextflix.entity.Zipcode} by id.
     * @param zipcode zipcode of particular location.
     * @return {@link com.apistone.nextflix.entity.Zipcode} object.
     * @throws ZipcodeNotFoundException When {@link com.apistone.nextflix.entity.Zipcode} not found
     */
    ZipcodeDto getZipcode(int zipcode) throws ZipcodeNotFoundException;

    /**
     * Save {@link com.apistone.nextflix.entity.Zipcode} instance to DB.
     * @param zipcode {@link com.apistone.nextflix.entity.Zipcode} instance.
     * @return Saved {@link com.apistone.nextflix.entity.Zipcode} instance.
     */
    ZipcodeDto saveZipcode(Zipcode zipcode);

    /**
     * Get PDF in base64 string.
     * @param zipcode zipcode of particular location.
     * @return PDF in base64 string.
     * @throws ZipcodeNotFoundException When {@link com.apistone.nextflix.entity.Zipcode} not found
     * @throws EncoderException if any exception occurs during encoding.
     * @throws IOException if any IO exception occurs.
     */
    DownloadResponse getPdfInBase64(int zipcode) throws ZipcodeNotFoundException, EncoderException, IOException;
}
