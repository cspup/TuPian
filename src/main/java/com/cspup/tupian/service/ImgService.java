package com.cspup.tupian.service;

import com.cspup.tupian.dao.ImgDao;
import com.cspup.tupian.entity.Img;
import org.springframework.stereotype.Service;

/**
 * @author csp
 * @date 2022/3/25 18:50
 * @description
 */
@Service
public class ImgService {
    private final ImgDao imgDao;

    public ImgService(ImgDao imgDao) {
        this.imgDao = imgDao;
    }

    public void add(Img img){
        imgDao.insert(img);
    }
}
