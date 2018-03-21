
/*---------------------------------idcard基础内容------------------------------------*/
function $() 
{ 
  var elements = new Array(); 
  for (var i = 0; i < arguments.length; i++) 
  { 
    var element = arguments[i]; 
    if (typeof element == 'string') 
      element = document.getElementById(element); 
    if (arguments.length == 1) 
      return element; 
    elements.push(element); 
  } 
  return elements; 
}

IdCardOp = function(obj){
    this.obj = obj;
}

// IdCardOp对象的成员函数定义
IdCardOp.prototype ={
	// 打开与读卡器的连接
    Open : function(){
        for(i=1;i<3;i++)
		{
			if(this.obj.OpenComm(i)==1)
			  return true;
		}
		for(i=1001;i<=1004;i++)
		{
			if(this.obj.OpenComm(i)==1){
				return true;
			}
		}
		return false;
    },
    // 关闭与读卡器的连接
    Close : function(){
        this.obj.EndComm();
    },
    // 读取信息
    ReadInfo:function(){
        var rt = new Object();
        if(this.obj.Authen()==1)
        {
            if(this.obj.ReadCardPath("",1))
            {
                // 姓名
                rt.Name = this.obj.sName;
                // 性别
                rt.Sex = this.obj.sSex;
                // 民族
                rt.Nation = this.obj.sNation;
                // 出生日期
                rt.BornDate = this.obj.sBornDate; // 这个取出来为空，生日从证件号中截取
                // 地址
                rt.Address = this.obj.sAddress;
                // 身份证号
                rt.IDNo = this.obj.sIDNo;
                // 签发机关
                rt.SignGov = this.obj.sSignGov;
                // 有效期开始日期
                rt.StartDate = this.obj.sStartDate;
                // 有效期结束日期
                rt.EndDate = this.obj.sEndDate;
                // 照片文件
                rt.PhotoBuffer = this.obj.PhotoBuffer;
            }
            else
                throw "读取失败！";
         }
         else
            throw "打开读卡设备失败！";
         this.Close();
         
         return rt;
   }
}

/*---------------------------------idcard扩展内容------------------------------------*/
/*
 * 需要导入的对象
 * <object id="OCX" codebase="Termb.cab#version=1,0,0,1" classid="clsid:18EE8930-6993-4ADA-B8BB-02BA5820AC94"></object>
 */
// 加载，判断设备连接是否正常
function initial( stateShow) {
	var op = new IdCardOp(document.getElementById("OCX"));
	var showInfoSpan = document.getElementById( stateShow);
	if ( op.Open()) {
		showInfoSpan.innerHTML = "准备就绪";
	} else {
		showInfoSpan.innerHTML = "读卡器未连接";
	}
	op.Close();
}

/* 
 * 读取卡内容
 * 返回值为证件对象
 * 属性内容为：
 * 姓名 Name
 * 性别 Sex 1-男 2-女
 * 国家Nation
 * 出生日期BornDate
 * 地址Address
 * 身份证号IDNo
 * 签发机关SignGov
 * 有效期开始日期StartDate
 * 有效期结束日期EndDate
 * 照片文件PhotoBuffer
 */
function readIdCard( runSign, stateShow) {
	var op = new IdCardOp(document.getElementById("OCX"));
	var showInfoSpan = document.getElementById("showInfo");
	if ( op.Open()) {
		showInfoSpan.innerHTML = "准备就绪";
	} else {
		showInfoSpan.innerHTML = "读卡器未连接";
		runSign = false;
		return;
	}

 	var reader;
	try {
		reader = op.ReadInfo();
	} catch(e){
		//alert("读取数据失败！");
	}	
	
	op.Close();
	return reader;
}
