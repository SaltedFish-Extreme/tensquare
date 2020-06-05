package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {
    public Friend findByuseridAndFriendid(String userid, String Friendid);

    @Modifying
    @Query(value = "update tb_friend set islike = ? where userid = ? and Friendid = ?", nativeQuery = true)
    public void updateIsLike(String islike, String userid, String Friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid = ? and Friendid = ?", nativeQuery = true)
    void deletefriend(String userid, String friendid);
}
