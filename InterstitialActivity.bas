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
	Private ad As String
	Dim request_button , show_button As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	tapsell.initialize("kilkhmaqckffopkpfnacjkobgrgnidkphkcbtmbcdhiokqetigljpnnrbfbnpnhmeikjbq")
	
	request_button.Initialize("request_button")
	Activity.AddView(request_button, 0, 0, 100%x, 20%y)
	request_button.Text = "Request Ad"
	request_button.Enabled = True
	
	show_button.Initialize("show_button")
	Activity.AddView(show_button, 0, 20%y, 100%x, 20%y)
	show_button.Text = "Show Ad"
	show_button.Enabled = False
End Sub

Sub request_button_Click
	tapsell.requestAd("5af292bef5a56d000196e9c8", True)
	request_button.Enabled = False
End Sub

Sub show_button_Click
	tapsell.showAd(ad, False, False, tapsell.ROTATION_UNLOCKED, True)
	show_button.Enabled = False
	request_button.Enabled = True
End Sub

Sub tapsell_onadavailable (zoneId As String, adId As String)
	show_button.Enabled = True
	request_button.Enabled = False
	ad = adId
End Sub

Sub Tapsell_onNoAdAvailable (zoneId As String)
	Msgbox("NoAdAvailable", "Tapsell")
	request_button.Enabled = True
	show_button.Enabled = False
End Sub

Sub Tapsell_onNoNetwork (zoneId As String)
	Msgbox("NoNetwork", "Tapsell")
	request_button.Enabled = True
	show_button.Enabled = False
End Sub

Sub tapsell_onerror (zoneId As String, error As String)
	Msgbox("Error", "Tapsell")
	request_button.Enabled = True
	show_button.Enabled = False
End Sub

Sub Tapsell_onExpiring (zoneId As String, adId As String)
	ad = Null
	request_button.Enabled = True
	show_button.Enabled = False
End Sub

Sub Tapsell_onAdShowFinished (zoneId As String, adId As String, completed As Boolean, rewarded As Boolean)
	Msgbox("Finished", "Tapsell")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
