package com.example.a12_13.bean;

public class zd_news_bean {
    /**
     * id : 180
     * cover : http://118.190.154.52:7777/profile/upload/image/2021/ 05/10/b88ef8ac-7538-4984-be59-3a768ec4709f.png
     * title : 卓创资讯：猪价放量急跌 多地猪价破“10”
     * subTitle : null
     * content : <p>据卓创监测，今日生猪均价跌至 19.00 元/公斤，较月初 跌 8.12%。多地开启急跌模式，“10 元”均价线下压到华南区域，仅极少数销区 均价仍在 20.00 元/公斤以上。大肥猪供应过剩，二次育肥补栏偏弱，预计猪价 或继续走低。</p>
     * status : Y
     * publishDate : 2021-05-10
     * tags : null
     * commentNum : 16
     * likeNum : 53
     * readNum : 615
     * type : 26
     * top : N
     * hot : N
     */

    private int id;
    private String cover;
    private String title;
    private Object subTitle;
    private String content;
    private String status;
    private String publishDate;
    private Object tags;
    private int commentNum;
    private int likeNum;
    private int readNum;
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

    public Object getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Object subTitle) {
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
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
