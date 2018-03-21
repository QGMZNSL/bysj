function setImg(obj){
	alert("12345");
            var f=$(obj).val();
            if(f == null || f ==undefined || f == ''){
                return false;
            }
            if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
            {
                alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
                $(obj).val('');
                return false;
            }
            var data = new FormData();
            $.each($(obj)[0].files,function(i,file){
                data.append('file', file);
            });
            $.ajax({
                type: "POST",
                url: "/business/uploadImg.html",
                data: data,
                cache: false,
                contentType: false,    
                processData: false,    
                dataType:"String",
                success: function(suc) {
                    if(suc.code==0){
                            $("#thumbUrl").val(suc.message);
                            $("#thumburlShow").attr("src",suc.message);                                                              
                        }else{
                        alertLayel("上传失败");
                        $("#url").val("");
                        $(obj).val('');
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alertLayel("上传失败，请检查网络后重试");
                    $("#url").val("");
                    $(obj).val('');
                }
            });
        }