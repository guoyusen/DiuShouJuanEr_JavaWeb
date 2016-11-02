package com.bili.diushoujuaner.common.pinyin;

import java.util.Comparator;

import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.model.ContactVo;

public class PinyinComparator implements Comparator<ContactVo> {

	@Override
	public int compare(ContactVo arg0, ContactVo arg1) {
		if(arg0.getType() == ConstantUtils.CONTACT_PARTY && arg1.getType() == ConstantUtils.CONTACT_FRIEND){
			arg0.setSortLetter("群组");
			arg1.setSortLetter(PinyinUtil.getHeadCapitalByChar(arg1.getDisplayName().charAt(0)) + "");
			return -1;
		}
		else if(arg0.getType() == ConstantUtils.CONTACT_FRIEND && arg1.getType() == ConstantUtils.CONTACT_PARTY){
			arg0.setSortLetter(PinyinUtil.getHeadCapitalByChar(arg0.getDisplayName().charAt(0)) + "");
			arg1.setSortLetter("群组");
			return 1;
		}
		else if(arg0.getType() == ConstantUtils.CONTACT_PARTY && arg1.getType() == ConstantUtils.CONTACT_PARTY){
			arg0.setSortLetter("群组");
			arg1.setSortLetter("群组");
			return 0;
		}
		else{
			char capitalA = PinyinUtil.getHeadCapitalByChar(arg0.getDisplayName().charAt(0));
			char capitalB = PinyinUtil.getHeadCapitalByChar(arg1.getDisplayName().charAt(0));
			arg0.setSortLetter(capitalA+"");
			arg1.setSortLetter(capitalB+"");
			if((capitalA < 'A' || capitalA > 'Z') && (capitalB < 'A' || capitalB > 'Z')){
				return 0;
			}
			else if((capitalA < 'A' || capitalA > 'Z') && (capitalB >= 'A' && capitalB <= 'Z')){
				return 1;
			}
			else if((capitalA >= 'A' && capitalA <= 'Z') && (capitalB < 'A' || capitalB > 'Z')){
				return -1;
			}
			else if(capitalA >= capitalB){
				return 1;
			}
			else{
				return -1;
			}
		}
	}

}
