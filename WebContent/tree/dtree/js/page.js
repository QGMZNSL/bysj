//��дһ�����ڷ�ҳ�Ľű�
function setQueryLink(currentPage,TotalPage,targetPage){
 //����
 var spaceStr = "&nbsp;&nbsp;&nbsp;&nbsp;";
 var pageStatu = spaceStr+"��ǰ��"+currentPage+"ҳ"+spaceStr+"����"+TotalPage+"ҳ";
 if(TotalPage<=1){
   document.getElementById("localtionPage").innerHTML = "��ҳ"+spaceStr+"��һҳ"+spaceStr+"��һҳ"+spaceStr+"ĩҳ"+pageStatu;
 }else
if(currentPage==1){
   document.getElementById("localtionPage").innerHTML = "��ҳ"+spaceStr+"��һҳ"+spaceStr+setLinkText("��һҳ",1,targetPage)+spaceStr+setLinkText("ĩҳ",0,targetPage)+pageStatu;
}
else if(TotalPage!=currentPage){
  document.getElementById("localtionPage").innerHTML = setLinkText("��ҳ",2,targetPage)+spaceStr+setLinkText("��һҳ",-1,targetPage)+spaceStr+setLinkText("��һҳ",1,targetPage)+spaceStr+setLinkText("ĩҳ",0,targetPage)+pageStatu;
}
else if(TotalPage==currentPage){
  document.getElementById("localtionPage").innerHTML = setLinkText("��ҳ",2,targetPage)+spaceStr+setLinkText("��һҳ",-1,targetPage)+spaceStr+"��һҳ"+spaceStr+"ĩҳ"+pageStatu;
}

}
function setLinkText(linkText,act,targetPage){
  return '<a href="'+targetPage+'&queryPage='+act+'">'+linkText+'</a>';
}
