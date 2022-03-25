package com.cspup.tupian.entity;

/**
 * @author csp
 * @date 2022/3/25 18:41
 * @description
 */
public class Img {
    private String id;
    private String name;
    private String path;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
