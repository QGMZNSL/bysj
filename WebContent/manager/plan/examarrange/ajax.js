var xmlHttp;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function doExcel(menutype){
	var pageSize = ___getPageSize();//
	var pageWidth =  pageSize[0];//页面的宽度
    var pageHeight =  pageSize[1];//页面的宽度
	var ScreenWidth = pageSize[2];//屏幕的宽度
	var ScreenHeight = pageSize[3];//屏幕的高度
	var yScrollTop = pageSize[4];//滚动条高度
	var bgDiv=document.getElementById("bgDiv");
	bgDiv.style.display="block";
	bgDiv.style.width=pageWidth + "px";
	bgDiv.style.height=pageHeight + "px";
    var saveDiv=document.getElementById("saveDiv");
	saveDiv.style.display="block";
	var examYear=document.getElementById("baseSyllabusTime.applyYear").value;
	var applyYear=document.getElementById("baseSyllabusTime.applyYear");
	applyYear.style.display="none";
	var examUnitary=document.getElementById("baseSyllabusTime.examUnitary");
	examUnitary.style.display="none";
	var examinationTime=document.getElementById("baseSyllabusTime.examinationTime");
	examinationTime.style.display="none";
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = handleState;
	xmlHttp.open("POST", "ExcelBiz", true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.send("menutype="+menutype+"&examYear="+examYear);
}

function handleState(){
    if(xmlHttp.readyState == 4){
    	if(xmlHttp.status == 200){
    		var bgDiv=document.getElementById("bgDiv");
			bgDiv.style.display="none";
    		var saveDiv=document.getElementById("saveDiv");
    		saveDiv.style.display="none";
    		var applyYear=document.getElementById("baseSyllabusTime.applyYear");
			applyYear.style.display="block";
    		var examUnitary=document.getElementById("baseSyllabusTime.examUnitary");
    		examUnitary.style.display="block";
			var examinationTime=document.getElementById("baseSyllabusTime.examinationTime");
			examinationTime.style.display="block";
    		var td=document.getElementById("exceldiv");
    		alert("导出成功！");
    		td.innerHTML=xmlHttp.responseText;
    	}
    }
}

function ___getPageSize(){ 
	var xScroll, yScroll,windowWidth, windowHeight,yScrollTop;  
    if(window.innerHeight && window.scrollMaxY){    
        xScroll = window.innerWidth + window.scrollMaxX;  
        yScroll = window.innerHeight + window.scrollMaxY;  
    }
    else if(document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac  
        xScroll = document.body.scrollWidth;  
        yScroll = document.body.scrollHeight;  
    }
    else{ // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari  
        xScroll = document.body.offsetWidth;  
        yScroll = document.body.offsetHeight;  
    } 
    if(self.innerHeight) { // all except Explorer  
        if(document.documentElement.clientWidth){  
            windowWidth = document.documentElement.clientWidth;   
        }
        else{  
            windowWidth = self.innerWidth;  
        }  
        windowHeight = self.innerHeight;  
    }
    else if(document.documentElement && document.documentElement.clientHeight){ // Explorer 6 Strict Mode  
        windowWidth = document.documentElement.clientWidth;  
        windowHeight = document.documentElement.clientHeight;  
    }
    else if(document.body){ // other Explorers  
        windowWidth = document.body.clientWidth;  
        windowHeight = document.body.clientHeight;  
    }
    // for small pages with total height less then height of the viewport  
    if(yScroll < windowHeight){  
        pageHeight = windowHeight;  
    }
    else{   
        pageHeight = yScroll;  
    }  
    // for small pages with total width less then width of the viewport  
    if(xScroll < windowWidth){     
        pageWidth = xScroll;          
    }
    else{  
        pageWidth = windowWidth;
    }
    //取滚动条高度
    if(self.pageYOffset){
    	yScrollTop = self.pageYOffset;
    }
    else if(document.documentElement &&document.documentElement.scrollTop){
    	yScrollTop = document.documentElement.scrollTop;
    }
    else if(document.body){ //div 垂直居中 
    	yScrollTop = document.body.scrollTop;
    }
    arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight,yScrollTop);  
    return arrayPageSize; 
};