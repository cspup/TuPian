package com.cspup.tupian.controller;

import com.cspup.tupian.common.FileUtils;
import com.cspup.tupian.common.R;
import com.cspup.tupian.entity.Img;
import com.cspup.tupian.service.ImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
        typeMap.put("image/webp",".webp");
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

    @PostMapping("/uploadByUrl")
    @ResponseBody
    public R<?> uploadByUrl(String url){
        try {
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000);
            InputStream inputStream = conn.getInputStream();
            String UUID = String.valueOf(java.util.UUID.randomUUID()).replace("-","");
            File file = readInputStreamToFile(inputStream,filePath+UUID);
            Img img = new Img();
            img.setId(UUID);
            img.setTime(new Date().getTime());
            img.setName(file.getName());
            img.setPath(file.getPath());
            imgService.add(img);
            return  R.ok("ok",img);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return R.error("保存失败");
    }

    /**
     * 从输入流中读取文件
     * @param inStream 输入流
     * @param fileName 文件名
     * @return 文件
     */
    private File readInputStreamToFile (InputStream inStream,String fileName) throws IOException {
        int len;

        byte[] buffer = new byte[1024];
        // 先获取文件类型
        len = inStream.read(buffer);
        String type = FileUtils.getFileType(buffer);
        File file = new File(fileName+"."+type);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(buffer,0,len);

        while ((len = inStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer,0,len);
        }
        fileOutputStream.close();

        return file;
    }



}
