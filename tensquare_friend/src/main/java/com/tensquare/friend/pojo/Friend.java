package com.tensquare.friend.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_friend")
@ToString
@Data
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;
    private String islike;
}
