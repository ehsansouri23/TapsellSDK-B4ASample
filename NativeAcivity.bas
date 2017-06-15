Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
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
	Private Tapsell As Tapsell
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
	Tapsell.initialize("gfmkmttpomfmgtsjmmagbdmeesdioapomfqirteitgkgqndlmjoaqekdplhbpabqfafaib")
	Tapsell.DebugMode = True
	
	Tapsell.requestNativeAd("587f498846846531e1afa37c")
	
	'ListView1.Initialize("ListView1")
	'For i = 1 To 20
	'	ListView1.AddSingleLine("Item #" & i)
	'Next
	'Activity.AddView(ListView1, 0, 0, 100%x, 100%y)
End Sub

Sub Tapsell_onNativeAdAvailable (zoneId As String, adId As String)
	Log("Tapsell_onNativeAdAvailable")
	Tapsell.fillNativeAd(adId,lblTitle,lblDescription,ivBanner,ivLogo,btnCallToAction,lblSponsored)
	AdPanel.Visible = True
End Sub

Sub Tapsell_onNoNativeAdAvailable (zoneId As String)
	Log("Tapsell_onNoNativeAdAvailable")
	Msgbox("NoNativeAdAvailable","Tapsell")
End Sub

Sub Tapsell_onNoNetwork (zoneId As String)
	Log("Tapsell_onNoNetwork")
	Msgbox("NoNetwork","Tapsell")
End Sub

Sub Tapsell_onError (zoneId As String, error As String)
	Log("Tapsell_onError")
	Msgbox("Error","Tapsell")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
