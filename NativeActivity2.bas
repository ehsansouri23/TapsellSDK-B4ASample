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
	Private tapsell As Tapsell
	Dim ListView1 As ListView
	Private adLogo As ImageView
	Private adTitle As Label
	Private adSponsored As Label
	Private adCallToAction As Button
	Private adPanel As Panel
	
	Dim panelBackground As ColorDrawable
	Dim sponsoredBackground As ColorDrawable
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	tapsell.initialize("gfmkmttpomfmgtsjmmagbdmeesdioapomfqirteitgkgqndlmjoaqekdplhbpabqfafaib")
	ListView1.Initialize("ListView1")
	For i = 1 To 40
		ListView1.AddSingleLine("Item #" & i)
	Next
	Activity.AddView(ListView1, 0, 0, 100%x, 100%y)
	tapsell.requestNativeAd("587f498846846531e1afa37c")
End Sub

Sub Tapsell_onNativeAdAvailable (zoneId As String, adId As String)
	Log("Tapsell_onNativeAdAvailable")
	ListView1.Height = 80%y
	adLogo.Initialize("adLogo")
	adTitle.Initialize("adTitle")
	adSponsored.Initialize("adSponsored")
	adCallToAction.Initialize("Button")
	
	adTitle.TextColor = Colors.Black
	adTitle.Gravity = Gravity.RIGHT
	adTitle.TextSize = 16
	adSponsored.TextColor = Colors.Black
	adSponsored.Gravity = Gravity.CENTER_HORIZONTAL
	sponsoredBackground.Initialize(Colors.LightGray,4)
	adSponsored.Background = sponsoredBackground
	
	panelBackground.Initialize(Colors.White,4)
	adPanel.Initialize("adPanel")
	adPanel.Background = panelBackground
	
	adPanel.AddView(adLogo,80%x,0,19%x,20%y)
	adPanel.AddView(adTitle,0,5%y,78%x,10%y)
	adPanel.AddView(adCallToAction,5%x,10%y,70%x,10%y)
	adPanel.AddView(adSponsored,0,0,10%x,5%y)
	Activity.AddView(adPanel,0,80%y,100%x,20%y)
	tapsell.fillNativeAd(adId,adTitle,Null,Null,adLogo,adCallToAction,adSponsored)
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
