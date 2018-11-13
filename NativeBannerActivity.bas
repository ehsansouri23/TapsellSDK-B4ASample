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
	Private btnCallToAction As Button
	Private ivBanner As ImageView
	Private ivLogo As ImageView
	Private lblTitle As Label
	Private lblSponsored As Label
	Private lblDescription As Label
	Private AdPanel As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("TapsellAdLayout")
	tapsell.initialize("kilkhmaqckffopkpfnacjkobgrgnidkphkcbtmbcdhiokqetigljpnnrbfbnpnhmeikjbq")
	
	tapsell.fillNativeBannerAd("5953bc774684652dd8fc937e", lblTitle, lblDescription, ivBanner, ivLogo, btnCallToAction, lblSponsored, AdPanel)
End Sub

Sub Tapsell_onNativeBannerAdAvailable (zoneId As String, adId As String)
	Log("Tapsell_onNativeAdAvailable")
	AdPanel.Visible = True
End Sub

Sub Tapsell_onNoNativeBannerAdAvailable (zoneId As String)
	Log("Tapsell_onNoNativeAdAvailable")
	Msgbox("NoNativeAdAvailable","Tapsell")
End Sub

Sub Tapsell_onNoNetwork (zoneId As String)
	Log("Tapsell_onNoNetwork")
	Msgbox("NoNetwork", "Tapsell")
End Sub

Sub Tapsell_onError (zoneId As String, error As String)
	Log("Tapsell_onError")
	Msgbox("Error", "Tapsell")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
