package com.cspup.tupian.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csp
 * @date 2022/3/28 21:58
 * @description 文件工具
 */
@Component
public class FileUtils {

    public static final Map<String, String> TYPES = new HashMap<>();

    static {
        // 图片，此处只提取前六位作为魔数
        TYPES.put("FFD8FF", "jpg");
        TYPES.put("89504E", "png");
        TYPES.put("474946", "gif");
        TYPES.put("524946", "webp");
    }


    /**
     * 使用魔数获取文件类型
     * @param data byte
     * @return 类型
     */
    public static String getFileType(byte[] data) {
        //提取前六位作为魔数
        String magicNumberHex = getHex(data, 6);
        return TYPES.get(magicNumberHex);
    }

    /**
     * 转16进制
     *
     * @param data              字节数组形式的文件数据
     * @param magicNumberLength 魔数长度
     *
     */
    public static String getHex(byte[] data, int magicNumberLength) {
        //提取文件的魔数
        StringBuilder magicNumber = new StringBuilder();
        //一个字节对应魔数的两位
        int magicNumberByteLength = magicNumberLength / 2;
        for (int i = 0; i < magicNumberByteLength; i++) {
            magicNumber.append(Integer.toHexString(data[i] >> 4 & 0xF));
            magicNumber.append(Integer.toHexString(data[i] & 0xF));
        }

        return magicNumber.toString().toUpperCase();
    }
}
