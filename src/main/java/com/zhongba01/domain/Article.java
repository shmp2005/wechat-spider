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
@Table(name = "zbq_wechat_articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

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
    @Column(name = "author_id")
    private Long authorId;
    @Column
    private String title;
    @Column(name = "pub_time")
    private Date pubTime;
    @Column
    private String url;
    @Column
    private String digest;

    @Column
    private String memo;

    @Column
    private String content;
    @Column
    private int words;
    @Column
    private String state;
    @Column(name = "state_memo")
    private String stateMemo;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateMemo() {
        return stateMemo;
    }

    public void setStateMemo(String stateMemo) {
        this.stateMemo = stateMemo;
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
                ", userId=" + userId +
                ", msgId='" + msgId + '\'' +
                ", seq=" + seq +
                ", origin=" + origin +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", pubTime=" + pubTime +
                ", url='" + url + '\'' +
                ", digest='" + digest + '\'' +
                ", memo='" + memo + '\'' +
                ", content='" + content + '\'' +
                ", words=" + words +
                ", state='" + state + '\'' +
                ", stateMemo='" + stateMemo + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
