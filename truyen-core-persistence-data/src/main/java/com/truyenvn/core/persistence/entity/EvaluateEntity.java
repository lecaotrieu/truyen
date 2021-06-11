package com.truyenvn.core.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "evaluate")
public class EvaluateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluateId;

    @Column(name = "count")
    private Integer count;

    @Column(name = "follow")
    private Integer follow;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private BookEntity bookEntity;

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }
}
