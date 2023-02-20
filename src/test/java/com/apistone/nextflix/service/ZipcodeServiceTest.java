package com.apistone.nextflix.service;

import com.apistone.nextflix.constant.TestConstants;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.entity.Zipcode;
import com.apistone.nextflix.exception.ZipcodeNotFoundException;
import com.apistone.nextflix.helper.ZipcodeHelper;
import com.apistone.nextflix.repository.ZipcodeRepository;
import com.apistone.nextflix.response.DownloadResponse;
import com.apistone.nextflix.service.impl.ZipcodeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.service
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 2/1/2023
 * Time: 10:45 AM
 * <p>
 * Use:
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class ZipcodeServiceTest {

    @Spy
    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private ZipcodeRepository zipcodeRepository;

    @InjectMocks
    private ZipcodeServiceImpl zipcodeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveZipcode_passValidZipcode_returnSavedZipcode() throws Exception {
        Zipcode dummyZipcode = new Zipcode();
        // Setting mock calls
        when(zipcodeRepository.save(dummyZipcode)).thenReturn(ZipcodeHelper.getSavedZipcode());
        // Actual call
        ZipcodeDto zipcodeDto = zipcodeService.saveZipcode(dummyZipcode);
        // Test the results
        log.info("getZipcode_passValidZipcode_returnZipcode : " + zipcodeDto);
    }

    @Test
    public void getZipcode_passValidZipcode_returnZipcode() throws Exception {
        Zipcode zipcode = ZipcodeHelper.getDummyOptionalZipcode().get();
        // Setting mock calls
        when(zipcodeRepository.findByZipcode(110092)).thenReturn(ZipcodeHelper.getDummyOptionalZipcode());
        // Actual call
        ZipcodeDto zipcodeDto = zipcodeService.getZipcode(110092);
        // Test the results
        assertEquals(zipcode.getZipcode(), zipcodeDto.getZipcode());
        assertEquals(zipcode.getZipcodeId(), zipcodeDto.getZipcodeId());
    }

    @Test
    public void getZipcode_passInValidZipcode_returnException() throws Exception {
        Optional<Zipcode> zipcodeOpt = Optional.empty();
        // Setting mock calls
        when(zipcodeRepository.findByZipcode(110092)).thenReturn(zipcodeOpt);
        // Actual call and verify the Exception instance
        assertThrows(ZipcodeNotFoundException.class, () -> zipcodeService.getZipcode(110092));
    }

    @Test
    public void getPdfInBase64_passValidZipcode_returnBase64() throws Exception {
        Optional<Zipcode> zipcodeOpt = ZipcodeHelper.getDummyOptionalZipcode();
        // Setting mock calls
        when(zipcodeRepository.findByZipcode(110092)).thenReturn(zipcodeOpt);
        when(zipcodeService.getZipcode(110092)).thenReturn(ZipcodeHelper.getDummyZipcodeDto());
        // Actual call
        DownloadResponse downloadResponse = zipcodeService.getPdfInBase64(110092);
        log.info("getPdfInBase64_passValidZipcode_returnBase64 : "+downloadResponse);
        // Test the results
        assertEquals(200, downloadResponse.getStatusCode());
        assertEquals("pdf", downloadResponse.getType());
        assertTrue(downloadResponse.getFileName().contains("Apistone_Batch2_"));
    }

    @Test
    public void getPdfInBase64_passInValidZipcode_returnException() throws Exception {
        Optional<Zipcode> zipcodeOpt = Optional.empty();
        // Setting mock calls
        when(zipcodeRepository.findByZipcode(110092)).thenReturn(zipcodeOpt);
        // Actual call and verify the Exception instance
        assertThrows(ZipcodeNotFoundException.class, () -> zipcodeService.getPdfInBase64(110092));
    }
}
