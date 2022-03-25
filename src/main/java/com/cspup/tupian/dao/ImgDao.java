package com.cspup.tupian.dao;

import com.cspup.tupian.entity.Img;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author csp
 * @date 2022/3/25 15:35
 * @description
 */
@Repository
public class ImgDao {

    private final DataSource dataSource;

    public ImgDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public boolean insert(Img img){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "insert into img (id,name,path,time) values (?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,img.getId());
                ps.setString(2,img.getName());
                ps.setString(3,img.getPath());
                ps.setLong(4,img.getTime());
                return ps;
            }
        };
        jdbcTemplate.update(preparedStatementCreator);
        return true;
    }
}
