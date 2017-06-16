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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ir.tapsell.b4asample", "ir.tapsell.b4asample.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "ir.tapsell.b4asample", "ir.tapsell.b4asample.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.tapsell.b4asample.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public anywheresoftware.b4a.objects.ButtonWrapper _request_button = null;
public anywheresoftware.b4a.objects.ButtonWrapper _show_button = null;
public anywheresoftware.b4a.objects.ButtonWrapper _native_button = null;
public anywheresoftware.b4a.objects.ButtonWrapper _native_ad_list = null;
public static String _ad = "";
public static int _coins = 0;
public static boolean _videoviewed = false;
public ir.tapsell.b4asample.nativeacivity _nativeacivity = null;
public ir.tapsell.b4asample.starter _starter = null;
public ir.tapsell.b4asample.nativeactivity2 _nativeactivity2 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (nativeacivity.mostCurrent != null);
vis = vis | (nativeactivity2.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="coins = 0";
_coins = (int) (0);
 //BA.debugLineNum = 38;BA.debugLine="tapsell.initialize(\"gfmkmttpomfmgtsjmmagbdmeesdio";
mostCurrent._tapsell.initialize(mostCurrent.activityBA,"gfmkmttpomfmgtsjmmagbdmeesdioapomfqirteitgkgqndlmjoaqekdplhbpabqfafaib");
 //BA.debugLineNum = 39;BA.debugLine="tapsell.DebugMode = True";
mostCurrent._tapsell.setDebugMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 40;BA.debugLine="request_button.Initialize(\"request_button\")";
mostCurrent._request_button.Initialize(mostCurrent.activityBA,"request_button");
 //BA.debugLineNum = 41;BA.debugLine="show_button.Initialize(\"show_button\")";
mostCurrent._show_button.Initialize(mostCurrent.activityBA,"show_button");
 //BA.debugLineNum = 42;BA.debugLine="Activity.AddView(request_button,0,0,50%x,20%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._request_button.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 43;BA.debugLine="Activity.AddView(show_button,50%x,0,50%x,20%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._show_button.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 44;BA.debugLine="request_button.Text = \"Request Ad\"";
mostCurrent._request_button.setText(BA.ObjectToCharSequence("Request Ad"));
 //BA.debugLineNum = 45;BA.debugLine="show_button.Text = \"Show Ad\"";
mostCurrent._show_button.setText(BA.ObjectToCharSequence("Show Ad"));
 //BA.debugLineNum = 46;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 47;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 49;BA.debugLine="native_button.Initialize(\"native_button\")";
mostCurrent._native_button.Initialize(mostCurrent.activityBA,"native_button");
 //BA.debugLineNum = 50;BA.debugLine="Activity.AddView(native_button, 0, 20%y, 100%x, 2";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._native_button.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 51;BA.debugLine="native_button.Text = \"Show Native Ads\"";
mostCurrent._native_button.setText(BA.ObjectToCharSequence("Show Native Ads"));
 //BA.debugLineNum = 53;BA.debugLine="native_ad_list.Initialize(\"native_ad_list\")";
mostCurrent._native_ad_list.Initialize(mostCurrent.activityBA,"native_ad_list");
 //BA.debugLineNum = 54;BA.debugLine="Activity.AddView(native_ad_list,0,40%y,100%x,20%y";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._native_ad_list.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 55;BA.debugLine="native_ad_list.Text = \"Native Bottom Banner Ad\"";
mostCurrent._native_ad_list.setText(BA.ObjectToCharSequence("Native Bottom Banner Ad"));
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 60;BA.debugLine="If videoViewed=True Then";
if (_videoviewed==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 61;BA.debugLine="videoViewed=False";
_videoviewed = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 62;BA.debugLine="ToastMessageShow(\"You received 1 Coin for watchi";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("You received 1 Coin for watching video!"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 63;BA.debugLine="coins = coins + 1";
_coins = (int) (_coins+1);
 };
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 27;BA.debugLine="Private tapsell As Tapsell";
mostCurrent._tapsell = new ir.tapsell.sdk.b4a.Tapsell();
 //BA.debugLineNum = 28;BA.debugLine="Dim request_button , show_button , native_button,";
mostCurrent._request_button = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._show_button = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._native_button = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._native_ad_list = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private ad As String";
mostCurrent._ad = "";
 //BA.debugLineNum = 30;BA.debugLine="Private coins As Int";
_coins = 0;
 //BA.debugLineNum = 31;BA.debugLine="Private videoViewed As Boolean";
_videoviewed = false;
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _native_ad_list_click() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub native_ad_list_Click";
 //BA.debugLineNum = 87;BA.debugLine="StartActivity(NativeActivity2)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._nativeactivity2.getObject()));
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _native_button_click() throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub native_button_Click";
 //BA.debugLineNum = 83;BA.debugLine="StartActivity(NativeAcivity)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._nativeacivity.getObject()));
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
nativeacivity._process_globals();
starter._process_globals();
nativeactivity2._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _request_button_click() throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub request_button_Click";
 //BA.debugLineNum = 72;BA.debugLine="tapsell.requestAd(\"587f498846846531e1afa37c\",True";
mostCurrent._tapsell.requestAd("587f498846846531e1afa37c",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 73;BA.debugLine="request_button.Enabled = False";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public static String  _show_button_click() throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub show_button_Click";
 //BA.debugLineNum = 77;BA.debugLine="tapsell.showAd(ad,False,False,tapsell.ROTATION_UN";
mostCurrent._tapsell.showAd(mostCurrent._ad,anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False,mostCurrent._tapsell.ROTATION_UNLOCKED,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 78;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 79;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onadavailable(String _zoneid,String _adid) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Tapsell_onAdAvailable (zoneId As String, adId";
 //BA.debugLineNum = 100;BA.debugLine="show_button.Enabled = True";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 101;BA.debugLine="request_button.Enabled = False";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 102;BA.debugLine="ad = adId";
mostCurrent._ad = _adid;
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onadshowfinished(String _zoneid,String _adid,boolean _completed,boolean _rewarded) throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Tapsell_onAdShowFinished (zoneId As String, ad";
 //BA.debugLineNum = 91;BA.debugLine="Log(\"onAdShowFinished\")";
anywheresoftware.b4a.keywords.Common.Log("onAdShowFinished");
 //BA.debugLineNum = 92;BA.debugLine="If completed=True Then";
if (_completed==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 93;BA.debugLine="If rewarded=True Then";
if (_rewarded==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 94;BA.debugLine="videoViewed = True";
_videoviewed = anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onerror(String _zoneid,String _error) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub Tapsell_onError (zoneId As String, error As St";
 //BA.debugLineNum = 118;BA.debugLine="Msgbox(\"Error\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 119;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 120;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onexpiring(String _zoneid,String _adid) throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub Tapsell_onExpiring (zoneId As String, adId As";
 //BA.debugLineNum = 124;BA.debugLine="ad = Null";
mostCurrent._ad = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 125;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 126;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onnoadavailable(String _zoneid) throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub Tapsell_onNoAdAvailable (zoneId As String)";
 //BA.debugLineNum = 106;BA.debugLine="Msgbox(\"NoAdAvailable\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("NoAdAvailable"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 108;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _tapsell_onnonetwork(String _zoneid) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Tapsell_onNoNetwork (zoneId As String)";
 //BA.debugLineNum = 112;BA.debugLine="Msgbox(\"NoNetwork\",\"Tapsell\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("NoNetwork"),BA.ObjectToCharSequence("Tapsell"),mostCurrent.activityBA);
 //BA.debugLineNum = 113;BA.debugLine="request_button.Enabled = True";
mostCurrent._request_button.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 114;BA.debugLine="show_button.Enabled = False";
mostCurrent._show_button.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
}
