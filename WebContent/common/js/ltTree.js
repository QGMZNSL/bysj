// 标题
var title = 'lt菜单树 V1.0';

// 关于
function about() {
    var msg = [];
    msg.push('ltTree [Version 1.0]');
    msg.push('(C) 2012 LT. All Right Reserved.');
    msg.push('-------------------------------------------------------------------');
    msg.push('Designed By');
    msg.push('\tLiTian');
    msg.push('Contact Author');
    msg.push('\tskylee1016@qq.com');
    alert(msg.join('\n'));
}

// 属性
var containner,  // 容器
	rootElement, // 根节点
	nodePack,    // 目录
	nodeElement, // 节点
	projectUrl;  // 项目地址

// 树结构标签定义
var struct = {
	rootTag     : 'ul',
	packTag     : 'ul',
	entityTag   : 'li',
	
	rootId      : 'ltTreeRoot',
	childAppend : '_pack'
};

// 树节点
var treeNode = {
	_nid     : -1,
	_nname   : '',
	_nparent : -1,
	_icon    : '',
	_url     : ''
};

// 去左右空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/, '');
}



// 构造根节点
function createRoot() {
	rootElement = "<|RTAG| id='|RID|'></|RTAG|>";
	rootElement = rootElement.replace(/\|RTAG\|/g, struct.rootTag);
	rootElement = rootElement.replace(/\|RID\|/g, struct.rootId);
	return rootElement;
}

// 初始化tree容器，并加入根节点
function init( projUrl, id) {
	projectUrl = projUrl;
	containner = $('#' + id);
	var rootNode = createRoot();
	containner.append( rootNode);
}

// 滚动子菜单树
function childSlide( obj, parentNid) {
	$('#' + parentNid + struct.childAppend).slideToggle('slow');
	if ( $(obj.parentNode).css("background") == "url(" + projectUrl +"/treePackClose.gif) no-repeat 2px 8px") {
		$(obj.parentNode).css("background","url(" + projectUrl +"/treePackOpen.gif) no-repeat 2px 8px");
	} else {
		$(obj.parentNode).css("background","url(" + projectUrl +"/treePackClose.gif) no-repeat 2px 8px");
	}
}

// 创建普通树节点
function createNode( nid, parentNid, nname, target, url) {
	var leftMargin = (parentNid == '0' || parentNid == 0) ? "0" : "10";
	var treeNode = "<|ETAG| id='|NID|' style='text-indent:15px;line-height:30px;height:30px;margin-left:" + leftMargin
        + "px; width:180px; background:url(" + projectUrl +"/treeNode.gif) no-repeat 2px 8px;'><a target='|TARGET|' href='|URL|'>|NNAME|</a></|ETAG|>";
	treeNode = treeNode.replace(/\|ETAG\|/g, struct.entityTag);
	treeNode = treeNode.replace(/\|NID\|/g, nid);
	treeNode = treeNode.replace(/\|TARGET\|/g, target);
	treeNode = treeNode.replace(/\|NNAME\|/g, nname);
	treeNode = treeNode.replace(/\|URL\|/g, url);
	return treeNode;
}

// 转化为树节点包
function createNodePack( parentNid) {
	var treeNodePack = "<|ETAG| id='|PNID|' style='text-indent:15px;line-height:30px;margin-left:0px; "
        + "width:180px; background:url(" + projectUrl +"/treePackClose.gif) no-repeat 2px 8px;'>"
		+ "<a href='#' onclick='childSlide( this, \"|PNID|\")'>"
		+ "|PNNAME|" 
		+ "</a>"
		+ "<|EPACK| id='|PNID||CAPPEND|'>"
		+ "</|EPACK|>"
		+ "</|ETAG|>";
	treeNodePack = treeNodePack.replace(/\|ETAG\|/g, struct.entityTag);
	treeNodePack = treeNodePack.replace(/\|EPACK\|/g, struct.packTag);
	treeNodePack = treeNodePack.replace(/\|CAPPEND\|/g, struct.childAppend);
	treeNodePack = treeNodePack.replace(/\|PNID\|/g, parentNid);

	var pnname = $('#' + parentNid + ': a').html();
	treeNodePack = treeNodePack.replace(/\|PNNAME\|/g, pnname);
	return treeNodePack;
}
		
// 创建节点
function add( nid, parentNid, nname, target, url) {
	// 构造节点
	var treeNode = createNode( nid, parentNid, nname, target, url);
	if ( parentNid == 0) {
		// 直接加在根节点上
		containner.append( treeNode);
	} else {
		// 加在子节点上，如果子节点不是包结构，则replace掉该对象
		if ( $('#' + parentNid + struct.childAppend).length == 0) {
			// 转化为包结构
			var nodePack = createNodePack( parentNid, nname);
			$('#' + parentNid).replaceWith(nodePack);
		}
		
		// 已经是包结构，直接附加
		$('#' + parentNid + struct.childAppend).append(treeNode);
		$('#' + parentNid + struct.childAppend).hide();
	}
}
