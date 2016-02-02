package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.VerifyCode;

public interface VerifyCodeMgt {
	
    void addVerifyCodeByMobileAndTypeAndCode(String mobile, short type, String code);
	
	VerifyCode getVerifyCodeByMobileAndType(String mobile, short type);
	
	void updateVerifyCodeByMobileAndTypeAndCode(String mobile, short type, String code);
	
}
