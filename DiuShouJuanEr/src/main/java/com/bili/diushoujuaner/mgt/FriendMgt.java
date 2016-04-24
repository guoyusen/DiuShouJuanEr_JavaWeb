package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.User;

public interface FriendMgt {
	
	boolean isFriend(long userANo, long userBNo);
	
	boolean addFriendRelationShip(User userFrom, User userTo);
}
