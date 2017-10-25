package ir.tapsell.b4asample;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class nativeactivity2 extends Activity implements B4AActivity{
	public static nativeactivity2 mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "ir.tapsell.b4asample", "ir.tapsell.b4asample.nativeactivity2");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (nativeactivity2).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ir.tapsell.b4asample", "ir.tapsell.b4asample.nativeactivity2");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.tapsell.b4asample.nativeactivity2", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (nativeactivity2) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (nativeactivity2) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return nativeactivity2.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (nativeactivity2) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (nativeactivity2) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public ir.tapsell.sdk.b4a.Tapsell _tapsell = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _adlogo = null;
public anywheresoftware.b4a.objects.LabelWrapper _adtitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _adsponsored = null;
public anywheresoftware.b4a.objects.ButtonWrapper _adcalltoaction = null;
public anywheresoftware.b4a.objects.PanelWrapper _adpanel = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _panelbackground = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _sponsoredbackground = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _ctabackground = null;
public static boolean _showstandardbanner = false;
public ir.tapsell.b4asample.main _main = null;
public ir.tapsell.b4asample.nativeacivity _nativeacivity = null;
public ir.tapsell.b4asample.nativevideoactivity _nativevideoactivity = null;
public ir.tapsell.b4asample.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _i = 0;
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="tapsell.initialize(\"gfmkmttpomfmgtsjmmagbdmeesdio";
mostCurrent._tapsell.initialize(mostCurrent.activityBA,"gfmkmttpomfmgtsjmmagbdmeesdioapomfqirteitgkgqndlmjoaqekdplhbpabqfafaib");
 //BA.debugLineNum = 33;BA.debugLine="ListView1.Initialize(\"ListView1\")";
mostCurrent._listview1.Initialize(mostCurrent.activityBA,"ListView1");
 //BA.debugLineNum = 34;BA.debugLine="showStandardBanner = True";
_showstandardbanner = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 35;BA.debugLine="For i = 1 To 40";
{
final int step4 = 1;
final int limit4 = (int) (40);
for (_i = (int) (1) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 36;BA.debugLine="ListView1.AddSingleLine(\"Item #\" & i)";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence("Item #"+BA.NumberToString(_i)));
 }
};
 //BA.debugLineNum = 38;BA.debugLine="Activity.AddView(ListView1, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._listview1.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 39;BA.debugLine="If showStandardBanner Then";
if (_showstandardbanner) { 
 //BA.debugLineNum = 40;BA.debugLine="adPanel.Initialize(\"adPanel\")";
mostCurrent._adpanel.Initialize(mostCurrent.activityBA,"adPanel");
 //BA.debugLineNum = 41;BA.debugLine="ListView1.Height = 80%y";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA));
 //BA.debugLineNum = 42;BA.debugLine="Activity.AddView(adPanel,0,85%y,100%x,15%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._adpanel.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 43;BA.debugLine="tapsell.fillBannerAd(adPanel, \"59f094054684650e7";
mostCurrent._tapsell.fillBannerAd((android.view.ViewGroup)(mostCurrent._adpanel.getObject()),"59f094054684650e7a54ef38",mostCurrent._tapsell.BANNER_320x50);
 }else {
 //BA.debugLineNum = 45;BA.debugLine="tapsell.requestNativeBannerAd(\"5943a6474684652bd";
mostCurrent._tapsell.requestNativeBannerAd("5943a6474684652bd8fc126d");
 };
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private tapsell As Tapsell";
mostCurrent._tapsell = new ir.tapsell.sdk.b4a.Tapsell();
 //BA.debugLineNum = 16;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private adLogo As ImageView";
mostCurrent._adlogo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private adTitle As Label";
mostCurrent._adtitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private adSponsored As Label";
mostCurrent._adsponsored = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private adCallToAction As Button";
mostCurrent._adcalltoaction = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private adPanel As Panel";
mostCurrent._adpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim panelBackground As ColorDrawable";
mostCurrent._panelbackground = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 24;BA.debugLine="Dim sponsoredBackground As ColorDrawable";
mostCurrent._sponsoredbackground = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 25;BA.debugLine="Dim ctaBackground As ColorDrawable";
mostCurrent._ctabackground = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 26;BA.debugLine="Dim showStandardBanner As Boolean";
_showstandardbanner = false;
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onerror(String _zoneid,String _error) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Tapsell_onError (zoneId As String, error As St";
 //BA.debugLineNum = 96;BA.debugLine="Log(\"Tapsell_onError\")";
anywheresoftware.b4a.keywords.Common.Log("Tapsell_onError");
 //BA.debugLineNum = 97;BA.debugLine="Msgbox(\"Error\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onnativebanneradavailable(String _zoneid,String _adid) throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Sub Tapsell_onNativeBannerAdAvailable (zoneId As S";
 //BA.debugLineNum = 50;BA.debugLine="Log(\"Tapsell_onNativeAdAvailable\")";
anywheresoftware.b4a.keywords.Common.Log("Tapsell_onNativeAdAvailable");
 //BA.debugLineNum = 51;BA.debugLine="ListView1.Height = 80%y";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA));
 //BA.debugLineNum = 52;BA.debugLine="adLogo.Initialize(\"adLogo\")";
mostCurrent._adlogo.Initialize(mostCurrent.activityBA,"adLogo");
 //BA.debugLineNum = 53;BA.debugLine="adTitle.Initialize(\"adTitle\")";
mostCurrent._adtitle.Initialize(mostCurrent.activityBA,"adTitle");
 //BA.debugLineNum = 54;BA.debugLine="adSponsored.Initialize(\"adSponsored\")";
mostCurrent._adsponsored.Initialize(mostCurrent.activityBA,"adSponsored");
 //BA.debugLineNum = 56;BA.debugLine="adCallToAction.Initialize(\"adCallToAction\")";
mostCurrent._adcalltoaction.Initialize(mostCurrent.activityBA,"adCallToAction");
 //BA.debugLineNum = 58;BA.debugLine="adTitle.TextColor = Colors.Black";
mostCurrent._adtitle.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 59;BA.debugLine="adTitle.Gravity = Gravity.RIGHT";
mostCurrent._adtitle.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 60;BA.debugLine="adTitle.Gravity = Gravity.CENTER_VERTICAL";
mostCurrent._adtitle.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 61;BA.debugLine="adTitle.TextSize = 16";
mostCurrent._adtitle.setTextSize((float) (16));
 //BA.debugLineNum = 62;BA.debugLine="adCallToAction.TextColor = Colors.Blue";
mostCurrent._adcalltoaction.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 63;BA.debugLine="adCallToAction.Gravity = Gravity.CENTER";
mostCurrent._adcalltoaction.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 64;BA.debugLine="adCallToAction.TextSize = 14";
mostCurrent._adcalltoaction.setTextSize((float) (14));
 //BA.debugLineNum = 66;BA.debugLine="ctaBackground.Initialize2(Colors.White, 3, 3, Col";
mostCurrent._ctabackground.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (3),(int) (3),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 67;BA.debugLine="adCallToAction.Background = ctaBackground";
mostCurrent._adcalltoaction.setBackground((android.graphics.drawable.Drawable)(mostCurrent._ctabackground.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="adSponsored.TextColor = Colors.White";
mostCurrent._adsponsored.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 69;BA.debugLine="adSponsored.Gravity = Gravity.CENTER_HORIZONTAL";
mostCurrent._adsponsored.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 70;BA.debugLine="sponsoredBackground.Initialize(Colors.Red,4)";
mostCurrent._sponsoredbackground.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (4));
 //BA.debugLineNum = 71;BA.debugLine="adSponsored.Background = sponsoredBackground";
mostCurrent._adsponsored.setBackground((android.graphics.drawable.Drawable)(mostCurrent._sponsoredbackground.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="panelBackground.Initialize2(Colors.White,4,4,Colo";
mostCurrent._panelbackground.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (4),(int) (4),anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 74;BA.debugLine="adPanel.Initialize(\"adPanel\")";
mostCurrent._adpanel.Initialize(mostCurrent.activityBA,"adPanel");
 //BA.debugLineNum = 75;BA.debugLine="adPanel.Background = panelBackground";
mostCurrent._adpanel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._panelbackground.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="adPanel.AddView(adLogo,80%x,0,20%x,15%y)";
mostCurrent._adpanel.AddView((android.view.View)(mostCurrent._adlogo.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),mostCurrent.activityBA),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 78;BA.debugLine="adPanel.AddView(adTitle,0,0%y,78%x,7%y)";
mostCurrent._adpanel.AddView((android.view.View)(mostCurrent._adtitle.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (78),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA));
 //BA.debugLineNum = 79;BA.debugLine="adPanel.AddView(adCallToAction,5%x,6%y,50%x,8%y)";
mostCurrent._adpanel.AddView((android.view.View)(mostCurrent._adcalltoaction.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (6),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
 //BA.debugLineNum = 80;BA.debugLine="adPanel.AddView(adSponsored,0,0,10%x,5%y)";
mostCurrent._adpanel.AddView((android.view.View)(mostCurrent._adsponsored.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA));
 //BA.debugLineNum = 81;BA.debugLine="Activity.AddView(adPanel,0,85%y,100%x,15%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._adpanel.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 82;BA.debugLine="tapsell.fillNativeBannerAd(adId,adTitle,Null,Null";
mostCurrent._tapsell.fillNativeBannerAd(_adid,(android.widget.TextView)(mostCurrent._adtitle.getObject()),(android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Null),(android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Null),(android.widget.ImageView)(mostCurrent._adlogo.getObject()),(android.widget.TextView)(mostCurrent._adcalltoaction.getObject()),(android.widget.TextView)(mostCurrent._adsponsored.getObject()),(android.view.ViewGroup)(mostCurrent._adpanel.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onnonativebanneradavailable(String _zoneid) throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Sub Tapsell_onNoNativeBannerAdAvailable (zoneId As";
 //BA.debugLineNum = 86;BA.debugLine="Log(\"Tapsell_onNoNativeAdAvailable\")";
anywheresoftware.b4a.keywords.Common.Log("Tapsell_onNoNativeAdAvailable");
 //BA.debugLineNum = 87;BA.debugLine="Msgbox(\"NoNativeAdAvailable\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("NoNativeAdAvailable"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onnonetwork(String _zoneid) throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Tapsell_onNoNetwork (zoneId As String)";
 //BA.debugLineNum = 91;BA.debugLine="Log(\"Tapsell_onNoNetwork\")";
anywheresoftware.b4a.keywords.Common.Log("Tapsell_onNoNetwork");
 //BA.debugLineNum = 92;BA.debugLine="Msgbox(\"NoNetwork\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("NoNetwork"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
}
