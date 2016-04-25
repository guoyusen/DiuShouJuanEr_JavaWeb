package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.User;

public interface FriendMgt {
	
	boolean isFriend(long userANo, long userBNo);
	
	boolean addFriendRelationShip(User userFrom, User userTo);
	
	boolean modifyFriendRemark(long userANo, long userBNo, String remark);
	
	boolean deleteFriendRelationShip(long userANo, long userBNo);
}
