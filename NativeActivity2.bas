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
	Dim ListView1 As ListView
	Private adLogo As ImageView
	Private adTitle As Label
	Private adSponsored As Label
	Private adCallToAction As Button
	Private adPanel As Panel
	
	Dim panelBackground As ColorDrawable
	Dim sponsoredBackground As ColorDrawable
	Dim ctaBackground As ColorDrawable
	Dim showStandardBanner As Boolean
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	tapsell.initialize("gfmkmttpomfmgtsjmmagbdmeesdioapomfqirteitgkgqndlmjoaqekdplhbpabqfafaib")
	ListView1.Initialize("ListView1")
	showStandardBanner = True
	For i = 1 To 40
		ListView1.AddSingleLine("Item #" & i)
	Next
	Activity.AddView(ListView1, 0, 0, 100%x, 100%y)
	If showStandardBanner Then
		adPanel.Initialize("adPanel")
		ListView1.Height = 80%y
		Activity.AddView(adPanel,0,85%y,100%x,15%y)
		tapsell.fillBannerAd(adPanel, "59f094054684650e7a54ef38", tapsell.BANNER_320x50)
	Else 
		tapsell.fillNativeBannerAd("5943a6474684652bd8fc126d",adTitle,Null,Null,adLogo,adCallToAction,adSponsored,adPanel)
	End If
End Sub

Sub Tapsell_onNativeBannerAdAvailable (zoneId As String, adId As String)
	Log("Tapsell_onNativeAdAvailable")
	ListView1.Height = 80%y
	adLogo.Initialize("adLogo")
	adTitle.Initialize("adTitle")
	adSponsored.Initialize("adSponsored")
	'adDescription.Initialize("adDescription")
	adCallToAction.Initialize("adCallToAction")
	
	adTitle.TextColor = Colors.Black
	adTitle.Gravity = Gravity.RIGHT
	adTitle.Gravity = Gravity.CENTER_VERTICAL
	adTitle.TextSize = 16
	adCallToAction.TextColor = Colors.Blue
	adCallToAction.Gravity = Gravity.CENTER
	adCallToAction.TextSize = 14
	
	ctaBackground.Initialize2(Colors.White, 3, 3, Colors.Blue)
	adCallToAction.Background = ctaBackground
	adSponsored.TextColor = Colors.White
	adSponsored.Gravity = Gravity.CENTER_HORIZONTAL
	sponsoredBackground.Initialize(Colors.Red,4)
	adSponsored.Background = sponsoredBackground
	
	panelBackground.Initialize2(Colors.White,4,4,Colors.LightGray)
	adPanel.Initialize("adPanel")
	adPanel.Background = panelBackground
	
	adPanel.AddView(adLogo,80%x,0,20%x,15%y)
	adPanel.AddView(adTitle,0,0%y,78%x,7%y)
	adPanel.AddView(adCallToAction,5%x,6%y,50%x,8%y)
	adPanel.AddView(adSponsored,0,0,10%x,5%y)
	Activity.AddView(adPanel,0,85%y,100%x,15%y)
	
End Sub

Sub Tapsell_onNoNativeBannerAdAvailable (zoneId As String)
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
