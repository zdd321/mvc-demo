
	var fileInput=1;
	function addImgInput(){
		fileInput=fileInput+1;
		var $preview=$('<input class="weui_uploader_input js_file" name="file"'
			+'type="file" accept="image/jpg,image/jpeg,image/png" multiple="multiple"'
			+'id="wxUploadImg'+fileInput+'"/>');
		$("#upload-form").append($preview);
		$("#wxUploadImg"+fileInput).on('change',jsFileChange(event));
	}
	$(".js_file").on('change',jsFileChange(event));
	
	function jsFileChange(event){
		var files=event.target.files;
		if(files.length==0){
			return;
		}
		if((files.length+selectedCount)>4){
			alert('总数最多上传4张');
			$("#wxUploadImg"+fileInput).remove();
			addImgInput();
			return;
		}
		for(var i=0,len=files.length;i<len;i++){
			var file=files[i];
			if(allowTypes.indexOf(file.type)===-1){
				$.weui.alert({text:'本次有不允许类型，请重新选择'})
				$("#wxUploadImg"+fileInput).remove();
				addImgInput();
				return;
			}
			
		}
		
	}
	selectedCount=selectedCount+files.length;
	$("#wxUploadImg"+fileInput).css("display",'none');
	if($('.weui_uploader_file').length<4){
		addImgInput();
	}

