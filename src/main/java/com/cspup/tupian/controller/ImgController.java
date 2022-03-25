package com.cspup.tupian.controller;

import com.cspup.tupian.common.R;
import com.cspup.tupian.entity.Img;
import com.cspup.tupian.service.ImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author csp
 * @date 2022/3/25 14:37
 * @description
 */
@Controller
public class ImgController {
    private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
    private final ImgService imgService;
    HashMap<String,String> typeMap = new HashMap<>();

    @Value("${cspup.imgDir}")
    String filePath;
    @Value("${cspup.maxSize}")
    long maxSize;

    public ImgController(ImgService imgService) {
        this.imgService = imgService;
        typeMap.put("image/png",".png");
        typeMap.put("image/jpg",".jpg");
        typeMap.put("image/gif",".gif");
        typeMap.put("image/jpeg",".jpeg");
        typeMap.put("image/bmp",".bmp");
    }

    @PostMapping("/upload")
    @ResponseBody
    public R<?> imgUpload(MultipartFile file) throws SQLException, IOException, ClassNotFoundException {
        if (file.isEmpty()){
            return R.error("请选择文件");
        }
        long size =  file.getSize();
        if (size>maxSize){
            return R.error("文件过大");
        }

        String UUID = String.valueOf(java.util.UUID.randomUUID()).replace("-","");
        if (typeMap.get(file.getContentType())==null){
            return R.error("不支持的图片格式");
        }
        String fileName = UUID+typeMap.get(file.getContentType());
        File dest = new File(filePath+fileName);
        try{
            file.transferTo(dest);
            Img img = new Img();

            img.setId(UUID);
            img.setName(fileName);
            img.setPath(dest.getPath());
            img.setTime(new Date().getTime());
            imgService.add(img);
            logger.info("上传成功");
            return R.ok("上传成功",img);
        }catch (IOException e){
            logger.error(e.toString(),e);
        }
        return R.error("上传失败");

    }
}
