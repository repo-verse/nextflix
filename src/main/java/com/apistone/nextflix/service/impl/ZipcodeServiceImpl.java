package com.apistone.nextflix.service.impl;

import com.apistone.nextflix.entity.Zipcode;
import com.apistone.nextflix.response.DownloadResponse;
import com.apistone.nextflix.constant.StringConstant;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.exception.ZipcodeNotFoundException;
import com.apistone.nextflix.repository.ZipcodeRepository;
import com.apistone.nextflix.service.ZipcodeService;
import com.apistone.nextflix.util.DateTimeUtil;
import com.apistone.nextflix.util.PdfExporter;
import org.apache.commons.codec.EncoderException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.service.impl
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:04 PM
 * <p>
 * Use: Provide implementation for zipcode service.
 */
@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @Override
    public ZipcodeDto getZipcode(int zipcode) throws ZipcodeNotFoundException {
        Optional<Zipcode> zipcodeOpt = zipcodeRepository.findByZipcode(zipcode);
        if(zipcodeOpt.isEmpty())
            throw new ZipcodeNotFoundException(StringConstant.ZIP_CODE_NOT_FOUND_ERROR_MESSAGE);
        else
            return modelMapper.map(zipcodeOpt.get(), ZipcodeDto.class);
    }

    @Override
    public ZipcodeDto saveZipcode(Zipcode zipcode) {
        Zipcode createdZipcode = zipcodeRepository.save(zipcode);
        ZipcodeDto createdZipcodeDto = modelMapper.map(createdZipcode, ZipcodeDto.class);
        return createdZipcodeDto;
    }

    @Override
    public DownloadResponse getPdfInBase64(int zipcode) throws ZipcodeNotFoundException, EncoderException, IOException {
        DownloadResponse downloadResponse = new DownloadResponse();
        ZipcodeDto zipcodeDto = getZipcode(zipcode);
        long timeStamp = System.currentTimeMillis();
        String fileName = "Apistone_Batch2_"+ DateTimeUtil.now()+".pdf";
        String b64 = new PdfExporter().getPdfInBase64(zipcodeDto);
        downloadResponse.setBase64(b64);
        downloadResponse.setType(StringConstant.FILE_TYPE_PDF);
        downloadResponse.setFileName(fileName);
        downloadResponse.setStatusCode(HttpStatus.OK.value());
        downloadResponse.setTimeStamp(timeStamp);
        return downloadResponse;
    }


}
