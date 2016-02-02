package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Picture;

public interface PictureMgt {
	
	List<Picture> getPictureListByRecallNo(long recallNo);

}
