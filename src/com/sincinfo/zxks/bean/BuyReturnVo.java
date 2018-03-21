package com.sincinfo.zxks.bean;
/**----------------------------------------------------------------
/*Copyright (C) 2010 ����ʡ���Թ�������
/*��Ȩ����
/*
/*
/*�ļ���BuyReturnVo.java
/*�ļ�����������֧���ӿڷ��ز���˵��
/*
/*
/*�����ߣ�Wucf
/*����ʱ�䣺2010-9-25
/**----------------------------------------------------------------*/
public class BuyReturnVo {
	private String p1_MerId;//1.�̻���� Max(20) �̻���š� 
	private String r0_Cmd;//2. ҵ������ Max(11) �̶�ֵΪ��Buy���� 
	private String r1_Code;//3.���˽��  1:�ɹ��� 
	private String r2_TrxId;//4.������ˮ�� Max(50) �ױ�֧��ƽ̨����Ľ�����ˮ�ţ�ÿ�ʶ���Ψһ 
	private String r3_Amt;//5.���˽�� Max(20) ��ԪΪ��λ����С���1Ԫ�� 
	private String r4_Cur;//6.���� Max(10) ����ʱ�ǡ�RMB���� 
	private String r5_Pid;//7.��Ʒ��� Max(50) �ױ�֧�������̻����õ���Ʒ���. 	�˲������õ����ģ���ע��ת��. 
	private String r6_Order;//8. ������ Max(50) �̻�ƽ̨�Ķ����š� 
	private String r8_MP;//9.�̼���չ��Ϣ Max(200) �̼���ǰ���������Զ�����Ϣ�� 
	private String r9_BType;//10.�������� Max(1) ��1����������ء� ��2�����������ء�

	private String ra_Details;//11.���˵��б�  ����ʵ�ʷ��˽��,����������еĸ�ʽ��ͬ�� 
	private String rb_BankId;//12.rb_BankId ���ĸ�����֧����  hmac�������ò����мǡ� 
	private String rb_SplitStatus;//13.rb_SplitStatus ����״̬  UNSPLIT��δ����(������Ϊ��ʼ״̬) SPLITING��������(������Ϊ����״̬) 	SPLITED���ѷ���(������Ϊȷ��״̬)
	private String ro_BankOrderId;//14.ro_BankOrderId ���ж�����  hmac�������ò����мǡ� 
	private String rp_PayDate;//15.rp_PayDate ֧��ʱ��  hmac�������ò����мǡ� 
	private String hmac;//16. hmac  ǩ�����  Max(32) 
	
	public String getP1_MerId() {
		return p1_MerId;
	}
	public void setP1_MerId(String merId) {
		p1_MerId = merId;
	}
	public String getR0_Cmd() {
		return r0_Cmd;
	}
	public void setR0_Cmd(String cmd) {
		r0_Cmd = cmd;
	}
	public String getR1_Code() {
		return r1_Code;
	}
	public void setR1_Code(String code) {
		r1_Code = code;
	}
	public String getR2_TrxId() {
		return r2_TrxId;
	}
	public void setR2_TrxId(String trxId) {
		r2_TrxId = trxId;
	}
	public String getR3_Amt() {
		return r3_Amt;
	}
	public void setR3_Amt(String amt) {
		r3_Amt = amt;
	}
	public String getR4_Cur() {
		return r4_Cur;
	}
	public void setR4_Cur(String cur) {
		r4_Cur = cur;
	}
	public String getR5_Pid() {
		return r5_Pid;
	}
	public void setR5_Pid(String pid) {
		r5_Pid = pid;
	}
	public String getR6_Order() {
		return r6_Order;
	}
	public void setR6_Order(String order) {
		r6_Order = order;
	}
	public String getR8_MP() {
		return r8_MP;
	}
	public void setR8_MP(String r8_mp) {
		r8_MP = r8_mp;
	}
	public String getR9_BType() {
		return r9_BType;
	}
	public void setR9_BType(String type) {
		r9_BType = type;
	}
	public String getRa_Details() {
		return ra_Details;
	}
	public void setRa_Details(String ra_Details) {
		this.ra_Details = ra_Details;
	}
	public String getRb_BankId() {
		return rb_BankId;
	}
	public void setRb_BankId(String rb_BankId) {
		this.rb_BankId = rb_BankId;
	}
	public String getRb_SplitStatus() {
		return rb_SplitStatus;
	}
	public void setRb_SplitStatus(String rb_SplitStatus) {
		this.rb_SplitStatus = rb_SplitStatus;
	}
	public String getRo_BankOrderId() {
		return ro_BankOrderId;
	}
	public void setRo_BankOrderId(String ro_BankOrderId) {
		this.ro_BankOrderId = ro_BankOrderId;
	}
	public String getRp_PayDate() {
		return rp_PayDate;
	}
	public void setRp_PayDate(String rp_PayDate) {
		this.rp_PayDate = rp_PayDate;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}	
}
