package com.zhongba01.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Entity
@Table(name = "gou_articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "account_id")
    private Long accountId;

    /**
     * 两种情况，
     * 1，一个msgId下面挂一篇文章，seq=1
     * 2，一个msgId下面挂多篇文章, 用seq来区分
     */
    @Column(name = "msg_id")
    private String msgId;
    @Column
    private int seq;
    @Column
    private boolean origin;
    @Column
    private String author;
    @Column
    private String title;
    @Column(name = "pub_date")
    private Date pubDate;
    @Column
    private String url;
    @Column(name = "post_url")
    private String postUrl;
    @Column
    private String digest;
    @Column
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean isOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", msgId='" + msgId + '\'' +
                ", seq=" + seq +
                ", origin=" + origin +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pubDate=" + pubDate +
                ", url='" + url + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", digest='" + digest + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
