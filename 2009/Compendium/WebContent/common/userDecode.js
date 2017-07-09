// userDecode.js

function changeEscape() {
	
	// �������������� ������� ��������
var trans = [];
for (var i = 0x410; i <= 0x44F; i++)
  trans[i] = i - 0x350; // �-��-�
trans[0x401] = 0xA8;    // �
trans[0x451] = 0xB8;    // �

// ��������� ����������� ������� escape()
var escapeOrig = window.escape;

// �������������� ������� escape()
window.escape = function(str)
{
  var ret = [];
  // ���������� ������ ����� ��������, ������� ��������� ���������
  for (var i = 0; i < str.length; i++)
  {
    var n = str.charCodeAt(i);
    if (typeof trans[n] != 'undefined')
      n = trans[n];
    if (n <= 0xFF)
      ret.push(n);
  }
  return escapeOrig(String.fromCharCode.apply(null, ret));
}
	
}

