package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.database.mapper.PictureMapper;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.PictureExample;
import com.bili.diushoujuaner.mgt.PictureMgt;

@Repository
public class PictureMgtImpl implements PictureMgt {

	@Autowired
	PictureMapper pictureMapper;
	
	@Override
	public List<Picture> getPictureListByRecallNo(long recallNo) {
		PictureExample pictureExample = new PictureExample();
		pictureExample.createCriteria().andRecallNoEqualTo(recallNo);
		return pictureMapper.selectByExample(pictureExample);
	}

}
