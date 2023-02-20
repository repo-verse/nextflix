package com.apistone.nextflix.controller;

import com.apistone.nextflix.entity.Zipcode;
import com.apistone.nextflix.response.DownloadResponse;
import com.apistone.nextflix.constant.ApiConstant;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.exception.ZipcodeNotFoundException;
import com.apistone.nextflix.service.ZipcodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.controller
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:53 PM
 * <p>
 * Use: Controller class to provide Rest APIs which are required.
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(ApiConstant.ZIPCODE_ROOT_END_POINT)
public class ZipcodeController {

    @Autowired
    private ZipcodeService zipcodeService;

    /**
     * API-DOC-01 : API used to get {@link com.apistone.nextflix.entity.Zipcode} by id.
     * @param zipcode zipcode of particular location.
     * @return {@link com.apistone.nextflix.entity.Zipcode} object.
     * @throws ZipcodeNotFoundException When {@link com.apistone.nextflix.entity.Zipcode} not found
     */
    @CrossOrigin
    @GetMapping(ApiConstant.GET_ZIPCODE_BY_ID_END_POINT)
    public ResponseEntity<ZipcodeDto> getZipcodeApi(@PathVariable int zipcode) throws ZipcodeNotFoundException {
        log.info("getZipcodeApi : Getting zipcode by id : "+zipcode);
        ZipcodeDto zipcodeDto = zipcodeService.getZipcode(zipcode);
        return new ResponseEntity<>(zipcodeDto, HttpStatus.OK);
    }

    /**
     * API-DOC-02 : API used to save {@link com.apistone.nextflix.entity.Zipcode} instance to DB.
     * @param zipcode {@link com.apistone.nextflix.entity.Zipcode} instance.
     * @return Saved {@link com.apistone.nextflix.entity.Zipcode} instance.
     */
    @PostMapping
    public ResponseEntity<ZipcodeDto> saveZipcodeApi(@RequestBody Zipcode zipcode){
        log.info("saveZipcodeApi : saving zipcode : "+zipcode);
        ZipcodeDto createdZipcodeDto = zipcodeService.saveZipcode(zipcode);
        return new ResponseEntity<>(createdZipcodeDto, HttpStatus.CREATED);
    }

    /**
     * API-DOC-03 : API used to get PDF in base64 string.
     * @param zipcode zipcode of particular location.
     * @return PDF in base64 string.
     * @throws ZipcodeNotFoundException When {@link com.apistone.nextflix.entity.Zipcode} not found
     * @throws EncoderException if any exception occurs during encoding.
     * @throws IOException if any IO exception occurs.
     */
    @GetMapping(ApiConstant.GET_ZIPCODE_BY_ID_END_POINT + ApiConstant.DOWNLOAD_ZIPCODE_END_POINT)
    public ResponseEntity<DownloadResponse> downloadZipcodeApi(@PathVariable int zipcode) throws IOException, EncoderException, ZipcodeNotFoundException {
        log.info("downloadZipcodeApi : downloading channels for : "+zipcode);
        DownloadResponse downloadResponse = zipcodeService.getPdfInBase64(zipcode);
        return new ResponseEntity<>(downloadResponse, HttpStatus.OK);
    }
}
