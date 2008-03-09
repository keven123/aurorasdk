package org.auroraide.client.ui.codePressEditor.impl;

import org.auroraide.client.ui.codePressEditor.CodePressEditor1;

public class CodePressEditorImpl {

	public native CodePressEditor1 create(String elementID)/*-{
			
			if(!$doc.getElementById(elementID))
				return;
			
			s = $doc.getElementsByTagName('script');
			for(var i=0,n=s.length;i<n;i++) {
				if(s[i].src.match('codepress.js')) {
					$wnd.CodePress.path = s[i].src.replace('codepress.js','');
				}
			}
			
			t = $doc.getElementById(elementID);
			if(t.lang.match('codepress')) {

				id = t.id;
				t.id = id+'_cp';
				eval(id+' = new $wnd.CodePress(t)');
				t.parentNode.insertBefore(eval(id), t);
			}
			//$wnd.alert(t.parentNode.innerHTML);
			return eval(id);
		}-*/;

	public native CodePressEditor1 create1(String elementID)/*-{
		
		s = $doc.getElementsByTagName('script');
		for(var i=0,n=s.length;i<n;i++) {
			if(s[i].src.match('codepress.js')) {
				$wnd.CodePress.path = s[i].src.replace('codepress.js','');
				$wnd.alert($wnd.CodePress.path);
			}
		}
		t = $doc.getElementsByTagName('textarea');
		for(var i=0,n=t.length;i<n;i++) {
			$wnd.alert(t[i].className);
			if(t[i].className.match('codepress')) {
				id = t[i].id;
				t[i].id = id+'_cp';
				eval(id+' = new $wnd.CodePress(t[i])');
				t[i].parentNode.insertBefore(eval(id), t[i]);
				window.alert(t[i].parentNode.innerHTML);
				return eval(id);
			} 
		}

	}-*/;

	public native String getCode(CodePressEditor1 editor)/*-{
			return editor.getCode();
		}-*/;

	public native void setCode(CodePressEditor1 editor, String code)/*-{
			editor.setCode(code);
		}-*/;

	public native void toggleLineNumbers(CodePressEditor1 editor)/*-{
			editor.toggleLineNumbers();
		}-*/;

	public native void edit(CodePressEditor1 editor, String language) /*-{
		editor.edit(editor.id,language);
	}-*/;

	public native void toggleEditor(CodePressEditor1 editor)/*-{
			editor.toggleEditor();
		}-*/;

	public native void toggleReadOnly(CodePressEditor1 editor)/*-{
			editor.toggleReadOnly();
		}-*/;

	public native void toggleAutoComplete(CodePressEditor1 editor)/*-{
			editor.toggleAutoComplete();
		}-*/;

}
