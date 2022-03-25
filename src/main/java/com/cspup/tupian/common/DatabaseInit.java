package com.cspup.tupian.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author csp
 * @date 2022/3/25 18:02
 * @description
 */
@Component
public class DatabaseInit {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);
    @Value("${cspup.imgDir}"+"img.sqlite")
    String dbPath;
    String path = DatabaseInit.class.getProtectionDomain().getCodeSource()
            .getLocation().getPath();
    public void init() {
        File outFile = new File(dbPath);
        File origin = new File("img.sqlite");

        if (!outFile.exists()){
            try{
                FileCopyUtils.copy(origin,outFile);
                logger.info("初始化成功");
            }catch (IOException e){
                logger.error(e.getMessage(),e);
            }
        }


    }

}
