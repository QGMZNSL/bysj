package com.sincinfo.zxks.common.util.dbf;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class importTest /*extends TestCase*/ {


//	@Test
	public void ttt() throws Exception{
		List<String[]> list=DbfUtil.importFromDbf("f:\\1.dbf");
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
	}	
	
}
