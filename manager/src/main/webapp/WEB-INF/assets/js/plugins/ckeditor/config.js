/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

/**
function brower() {
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	if (window.ActiveXObject) {
		Sys.ie = ua.match(/msie ([\d.]+)/)[1];
	}
	else if (document.getBoxObjectFor) {
		Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
	}
	else if (window.MessageEvent && !document.getBoxObjectFor) {
		Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1];
	}
	else if (window.opera) {
		Sys.opera = ua.match(/opera.([\d.]+)/)[1];
	}
	else if (window.openDatabase) {
		Sys.safari = ua.match(/version\/([\d.]+)/)[1];
	}
	
	var ua = navigator.userAgent.toLowerCase();
	window.ActiveXObject ? Sys.ie = ua.match(/msie ([\d.]+)/)[1] :
        document.getBoxObjectFor ? Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1] :
        window.MessageEvent && !document.getBoxObjectFor ? Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1] :
        window.opera ? Sys.opera = ua.match(/opera.([\d.]+)/)[1] :
        window.openDatabase ? Sys.safari = ua.match(/version\/([\d.]+)/)[1] : 0;
        
	if(Sys.ie) return "ie";
	if(Sys.firefox) return "firefox";
	if(Sys.chrome) return "chrome";
	if(Sys.opera) return "opera";
	if(Sys.safari) return "safari";
}
**/

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For the complete reference:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];

	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Se the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Make dialogs simpler.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
	//config.filebrowserImageBrowseUrl = 'editor_upload.htm';
	//config.filebrowserFlashBrowseUrl = 'editor_upload.htm';
	config.filebrowserUploadUrl = 'editor_upload.htm?ua=ie';
	//config.filebrowserImageUploadUrl = 'editor_upload.htm';
	//config.filebrowserFlashUploadUrl = 'editor_upload.htm';
	config.filebrowserWindowWidth = '800';
	config.filebrowserWindowHeight = '500';
};
