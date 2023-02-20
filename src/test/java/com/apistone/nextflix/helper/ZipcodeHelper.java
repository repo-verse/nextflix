package com.apistone.nextflix.helper;

import com.apistone.nextflix.constant.TestConstants;
import com.apistone.nextflix.dto.ChannelDto;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.apistone.nextflix.entity.Channel;
import com.apistone.nextflix.entity.Zipcode;
import com.apistone.nextflix.response.DownloadResponse;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.helper
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 2/1/2023
 * Time: 1:15 AM
 * <p>
 * Use:
 */
public class ZipcodeHelper {

    public static DownloadResponse getDummyDownloadResponse(){
        return new DownloadResponse(
                "JVBERi0xLjUKJeLjz9MKNCAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDExMjE+PnN0cm",
                "Apistone_Batch2_Feb-01,2023-00:12:02.pdf",
                "pdf",
                200,
                1675190522954L
        );
    }


    public static ZipcodeDto getDummyZipcodeDto(){
        ZipcodeDto zipcodeDto = new ZipcodeDto();
        Set<ChannelDto> channels = new HashSet<>();
        ChannelDto channel1 = new ChannelDto();
        channel1.setChannelId(1L);
        channel1.setActive(true);
        channel1.setChannelName("HBO");
        channel1.setBasic(true);
        channel1.setPreferred(false);
        channel1.setStandard(true);
        channel1.setChannelNumber(123L);
        channel1.setPremiumPackage(true);

        ChannelDto channel2 = new ChannelDto();
        channel2.setChannelId(2L);
        channel2.setActive(true);
        channel2.setChannelName("HBO2");
        channel2.setBasic(true);
        channel2.setPreferred(true);
        channel2.setStandard(false);
        channel2.setChannelNumber(1243L);
        channel2.setPremiumPackage(false);

        channels.add(channel1);
        channels.add(channel2);

        zipcodeDto.setZipcode(110092);
        zipcodeDto.setZipcodeId(1L);
        zipcodeDto.setChannels(channels);

        return zipcodeDto;
    }

    public static Optional<Zipcode> getDummyOptionalZipcode(){
        Zipcode zipcode = new Zipcode();
        Set<Channel> channels = new HashSet<>();
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        channel1.setActive(true);
        channel1.setChannelName("HBO");
        channel1.setBasic(true);
        channel1.setPreferred(false);
        channel1.setStandard(true);
        channel1.setChannelNumber(111L);
        channel1.setPremiumPackage(true);

        Channel channel2 = new Channel();
        channel2.setChannelId(2L);
        channel2.setActive(true);
        channel2.setChannelName("HBO2");
        channel2.setBasic(true);
        channel2.setPreferred(true);
        channel2.setStandard(false);
        channel2.setChannelNumber(222L);
        channel2.setPremiumPackage(false);

        channels.add(channel1);
        channels.add(channel2);

        zipcode.setZipcode(110092);
        zipcode.setZipcodeId(1L);
        zipcode.setChannels(channels);

        return Optional.of(zipcode);
    }

    public static Zipcode getSavedZipcode(){
        Zipcode zipcode = new Zipcode();
        Set<Channel> channels = new HashSet<>();
        Channel channel = new Channel();
        channel.setChannelId(2L);
        channel.setActive(true);
        channel.setChannelName(TestConstants.HBO);
        channel.setBasic(true);
        channel.setPreferred(true);
        channel.setStandard(false);
        channel.setChannelNumber(222L);
        channel.setPremiumPackage(false);

        channels.add(channel);

        zipcode.setZipcode(110092);
        zipcode.setZipcodeId(1L);
        zipcode.setChannels(channels);
        return zipcode;
    }
}
