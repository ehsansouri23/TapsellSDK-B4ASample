B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private tapsell As Tapsell
	Private adPanel As Panel
	
	tapsell.initialize("kilkhmaqckffopkpfnacjkobgrgnidkphkcbtmbcdhiokqetigljpnnrbfbnpnhmeikjbq")
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:

	adPanel.Initialize("adPanel")
	Activity.AddView(adPanel, 0, 5%y, 100%x, 15%y)
	tapsell.fillBannerAd(adPanel, "5a28f47539086d0001670416", tapsell.BANNER_320x50)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
