package me.shufork.biz.utils;

import com.google.common.base.Charsets;
import me.shufork.common.dto.supercell.coc.LeagueIconUrlsDto;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;

public final class CrcUtils {
    private static final ThreadLocal<ByteBuffer> BYTE_BUFFER = ThreadLocal.withInitial(() -> ByteBuffer.allocateDirect(1024*4));
    private static final ThreadLocal<StringBuffer> STRING_BUFFER = ThreadLocal.withInitial(() -> new StringBuffer(1024));
    private CrcUtils(){}

    public static long StringCrc(StringBuffer stringBuffer,ByteBuffer byteBuffer,List<String> strings){
        strings.forEach(o -> stringBuffer.append(StringUtils.trimToEmpty(o)));

        CharBuffer inputBuffer = CharBuffer.wrap(stringBuffer.toString());
        CRC32 crc32 = new CRC32();
        CharsetEncoder encoder = Charsets.UTF_8.newEncoder();

        CoderResult coderResult;
        do {
            coderResult = encoder.encode(inputBuffer, byteBuffer, true);
            byteBuffer.flip();
            crc32.update(byteBuffer);
        } while (coderResult.isOverflow());

        return crc32.getValue();
    }
    /*public static long CrcOf(CocClanBadgeUrls cocClanBadgeUrls){
        final ByteBuffer byteBuffer = BYTE_BUFFER.get();
        final StringBuffer stringBuffer = STRING_BUFFER.get();
        try{
            return StringCrc(stringBuffer,byteBuffer,Arrays.asList(
                    cocClanBadgeUrls.getTiny(),
                    cocClanBadgeUrls.getSmall(),
                    cocClanBadgeUrls.getMedium(),
                    cocClanBadgeUrls.getLarge()));
        }finally {
            byteBuffer.reset();
            stringBuffer.setLength(0);
        }
    }*/

    public static long CrcOf(LeagueIconUrlsDto leagueIconUrlsDto){
        final ByteBuffer byteBuffer = BYTE_BUFFER.get();
        final StringBuffer stringBuffer = STRING_BUFFER.get();
        try{
            return StringCrc(stringBuffer,byteBuffer,Arrays.asList(
                    leagueIconUrlsDto.getTiny(),
                    leagueIconUrlsDto.getSmall(),
                    leagueIconUrlsDto.getMedium(),
                    null));
        }finally {
            byteBuffer.reset();
            stringBuffer.setLength(0);
        }
    }
}
