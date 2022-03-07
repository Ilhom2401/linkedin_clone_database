package model;

import lombok.Data;

@Data
public class Post {
    private long size;
    private String name;
    private String content_type;
    private byte[] content;
}
