//编写一个关于翻页的脚本
function setQueryLink(currentPage,TotalPage,targetPage){
 //内容
 var spaceStr = "&nbsp;&nbsp;&nbsp;&nbsp;";
 var pageStatu = spaceStr+"当前第"+currentPage+"页"+spaceStr+"共有"+TotalPage+"页";
 if(TotalPage<=1){
   document.getElementById("localtionPage").innerHTML = "首页"+spaceStr+"上一页"+spaceStr+"下一页"+spaceStr+"末页"+pageStatu;
 }else
if(currentPage==1){
   document.getElementById("localtionPage").innerHTML = "首页"+spaceStr+"上一页"+spaceStr+setLinkText("下一页",1,targetPage)+spaceStr+setLinkText("末页",0,targetPage)+pageStatu;
}
else if(TotalPage!=currentPage){
  document.getElementById("localtionPage").innerHTML = setLinkText("首页",2,targetPage)+spaceStr+setLinkText("上一页",-1,targetPage)+spaceStr+setLinkText("下一页",1,targetPage)+spaceStr+setLinkText("末页",0,targetPage)+pageStatu;
}
else if(TotalPage==currentPage){
  document.getElementById("localtionPage").innerHTML = setLinkText("首页",2,targetPage)+spaceStr+setLinkText("上一页",-1,targetPage)+spaceStr+"下一页"+spaceStr+"末页"+pageStatu;
}

}
function setLinkText(linkText,act,targetPage){
  return '<a href="'+targetPage+'&queryPage='+act+'">'+linkText+'</a>';
}
