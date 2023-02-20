package com.apistone.nextflix.controller;

import com.apistone.nextflix.constant.ApiConstant;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.helper.ZipcodeHelper;
import com.apistone.nextflix.service.ZipcodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.controller
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 2/1/2023
 * Time: 12:25 AM
 * <p>
 * Use: Unit testing through Mockito and jUnit
 */

@ExtendWith(MockitoExtension.class)
public class ZipcodeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ZipcodeService zipcodeService;

    @InjectMocks
    private ZipcodeController zipcodeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(zipcodeController).build();
    }

    @Test
    public void getZipcodeApi_passValidZipcode_returnZipcode() throws Exception {
        ZipcodeDto zipcodeDto = ZipcodeHelper.getDummyZipcodeDto();
        // Setting mock call
        when(zipcodeService.getZipcode(110092)).thenReturn( zipcodeDto);
        // Mock API call and verify results
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ApiConstant.ZIPCODE_ROOT_END_POINT+"/110092")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void downloadZipcodeApi_passValidZipcode_returnBase64() throws Exception {
        // Setting mock call
        when(zipcodeService.getPdfInBase64(110092)).thenReturn( ZipcodeHelper.getDummyDownloadResponse());
        // Mock API call and verify results
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ApiConstant.ZIPCODE_ROOT_END_POINT+"/110092"+ApiConstant.DOWNLOAD_ZIPCODE_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.base64", is("JVBERi0xLjUKJeLjz9MKNCAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDExMjE+PnN0cm")))
                .andExpect(jsonPath("$.fileName", is("Apistone_Batch2_Feb-01,2023-00:12:02.pdf")))
                .andExpect(jsonPath("$.type", is("pdf")))
                .andExpect(jsonPath("$.statusCode", is(200)))
                .andExpect(jsonPath("$.timeStamp", is(1675190522954L)));
    }


}
