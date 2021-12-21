package com.example.a12_13.bean;

public class news_init_bean {
    /**
     * id : 5
     * cover : /dev-api/profile/upload/image/2021/04/01/c1eb74b2-e964- 4388-830a-1b606fc9699f.png
     * title : 驱蚊器无去
     * subTitle : 123123123
     * content : <p>企鹅王请问<img src="/dev-api/profile/upload/image /2021/04/07/a9434ccf-5acf-4bf5-a06e-c3457c6762e9.png"></p>
     * status : Y
     * publishDate : 2021-04-01
     * tags : null
     * commentNum : null
     * likeNum : 3
     * readNum : null
     * type : 2
     * top : Y
     * hot : N
     */

    private int id;
    private String cover;
    private String title;
    private String subTitle;
    private String content;
    private String status;
    private String publishDate;
    private Object tags;
    private Object commentNum;
    private int likeNum;
    private Object readNum;
    private String type;
    private String top;
    private String hot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public Object getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Object commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public Object getReadNum() {
        return readNum;
    }

    public void setReadNum(Object readNum) {
        this.readNum = readNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }
}
