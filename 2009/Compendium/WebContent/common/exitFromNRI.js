// exitFromNRI.js

// onbeforeunload:
window.onbeforeunload = unloadCheck;
window.setTimeout('window.onbeforeunload=null', 60000 * 30);
dialogArrayExit = new Array();	//�������� ����
function unloadCheck()	{
	if(event.clientX > document.body.clientWidth && event.clientY < 0){
		//��������� �������� ����:
		if(dialogArrayExit != null){
			for(var i = 0; i < dialogArrayExit.length; i++){
				if(dialogArrayExit[i])
					dialogArrayExit[i].close();
			}
		}	
		return '��������!\n��� ������ �� ������� \'NRI \' �������������� ������� ���� \'�����\'.';
	}else{
		return;
	}
}
