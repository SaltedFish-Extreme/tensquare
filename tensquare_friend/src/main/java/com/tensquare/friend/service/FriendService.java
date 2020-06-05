package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String Friendid) {
        //先判断userid到Friendid是否有数据，有就是重复添加好友，返回0
        Friend friend = friendDao.findByuseridAndFriendid(userid, Friendid);
        if (friend != null) {
            return 0;
        }
        //直接添加好友，让好友表中userid到Friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(Friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从Friendid到userid是否有数据，如果有，把双方的状态都改为1
        if (friendDao.findByuseridAndFriendid(Friendid, userid) != null) {
            friendDao.updateIsLike("1", userid, Friendid);
            friendDao.updateIsLike("1", Friendid, userid);
        }
        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByuseridAndFriendid(userid, friendid);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //删除好友表中userid到friendid这条数据
        friendDao.deletefriend(userid, friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIsLike("0", friendid, userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
