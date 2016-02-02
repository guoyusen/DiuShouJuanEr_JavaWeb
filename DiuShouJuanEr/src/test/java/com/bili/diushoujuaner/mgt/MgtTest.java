package com.bili.diushoujuaner.mgt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
@TransactionConfiguration(defaultRollback = true)  
@Transactional
public class MgtTest {
	
	@Test
	public void getUser(){
		VerifyCodeMgt verifyCodeMgt = (VerifyCodeMgt)SpringContextUtil.getBean("verifyCodeMgtImpl");
		verifyCodeMgt.addVerifyCodeByMobileAndTypeAndCode("18817800148", (short) 1, "1234");
	}

}
