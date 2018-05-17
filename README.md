<div dir="rtl">
<a href="#part1">تنظیمات اولیه پروژه</a></br>
<a href="#part2">پیاده‌سازی تبلیغات ویدئویی (جایزه دار-بینابینی) و تبلیغات بنری تمام صفحه در پروژه B4A</a></br>
<a href="#part3">پیاده‌سازی تبلیغات بنری استاندارد در پروژه B4A</a></br>
<a href="#part4">پیاده‌سازی تبلیغات بنری هم‌نما (Native Banner) در پروژه B4A</a></br>
<a href="#part5">پیاده‌سازی تبلیغات ویدئویی هم‌نما (Native Video) در پروژه B4A</a></br>
<a href="#part6">موارد پیشرفته‌تر در SDK</a></br>
</div>
</br></br>

<div dir="rtl" id="part1">
  <h1>تنظیمات اولیه پروژه</h1>
  جهت راه اندازی تبلیغات تپسل در اپلیکیشن شما، ابتدا باید مراحل  زیر را در پروژه خود انجام دهید.
<p dir="ltr">[ht_message mstyle="danger" title="نکته Build پروژه" " show_icon="" id="" class="" style="" ]برای Build کردن پروژه حتما نوع Build را در حالت Release قرار دهید.[/ht_message]</p>

<h3><strong>گام ۱: دریافت </strong><strong>SDK</strong><strong> تپسل</strong></h3>
فایل حاوی SDK تپسل را از آدرس زیر دریافت کنید و محتویات آن را در پوشه‌ای ذخیره کنید.
<p style="text-align: center;"><a href="https://storage.backtory.com/tapsell-server/sdk/b4a/TapsellB4A_v3.0.35.zip"><button>دریافت فایل</button></a></p>
&nbsp;
<h3><strong>گام ۲: افزودن </strong><strong>SDK</strong><strong> تپسل به کتابخانه B4A</strong></h3>
فایل‌های TapsellSDK.jar و TapsellSDK.xml را از SDK تپسل به پوشه‌ی Libraries در محل نصب B4A اضافه کنید. پروژه B4A خود را باز کنید و در بخش Libraries Manager، کلیک راست کرده و گزینه Refresh را انتخاب کنید تا فهرست به‌روزرسانی شود. سپس کتابخانه‌ی تپسل را از لیست پیدا کرده و آن را به پروژه خود اضافه کنید.
<p style="text-align: center;"><img class="size-full wp-image-468 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_1-1.png" alt="B4A" width="699" height="374" /></p>
&nbsp;
<h3><strong>گام ۳: افزودن فایل فونت</strong></h3>
فایل bkoodb.ttf موجود در پوشه assets از SDK تپسل را در پوشه‌ی Files در محل قرارگیری پروژه B4A خود کپی کنید. سپس در نرم‌افزار B4A در بخش Files Manager کلیک راست کرده و گزینه Sync Folder را انتخاب کنید. پس از نمایش فایل فونت کپی شده در فهرست، تیک مربوط به آن را بزنید تا به پروژه افزوده شود.
<p style="text-align: center;"><img class="size-full wp-image-469 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_2.png" alt="B4A" width="699" height="367" /></p>
&nbsp;
<h3>گام ۴: اضافه کردن کتابخانه‌های مورد نیاز</h3>
اگر قبلا برای پروژه های خود محل Additional Libraries را تعیین کرده اید لازم هست محتویات پوشه‌ی libs از SDK تپسل را در آدرسی که در Tools-&gt;Configure Paths-&gt;Additional Libraries تعیین کرده‌اید کپی کنید. در غیر اینصورت پوشه‌ی libs را در محل پروژه‌ی B4A خود و در کنار پوشه‌های Files و Objects کپی کنید.
<p style="text-align: center;"><img class="size-full wp-image-470 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_3.png" alt="B4A" width="777" height="224" /></p>
سپس این پوشه را به عنوان پوشه‌ی Additional Libraries در پروژه خود انتخاب کنید.
<p style="text-align: center;"><img class="size-full wp-image-471 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_4.png" alt="B4A" width="779" height="341" /></p>
&nbsp;
<h3><strong>گام ۵: تنظیمات Activity Attributes و Project Attributes</strong></h3>
در محدوده Activity Attributes از پروژه خود، خطوط زیر را اضافه کنید.
<pre dir="ltr">#Region Activity Attributes
    ...
    <span style="color: #ff9900;">#AdditionalJar</span> <span style="color: #a30a0a;">: tapsell-b4a-3.0.35.jar</span>
#End Region</pre>
در صورتی که پروژه‌ی خود را در حالت debug اجرا می‌کنید خط زیر را در بخش Project Attributes فایل اصلی پروژه خود اضافه کنید:
<pre dir="ltr">#Region Project Attributes
    ...
    <span style="color: #ff9900;">#DebuggerForceStandardAssets</span> <span style="color: #a30a0a;">: true</span>
#End Region</pre>
&nbsp;

<img class="size-full wp-image-475 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_5-1.png" alt="B4A" width="842" height="437" />

&nbsp;
<h3><strong>گام ۶: آپدیت فایل </strong><strong>Manifest</strong></h3>
از منو Project گزینه Manifest Editor را انتخاب کرده و خطوط زیر را به manifest برنامه خود اضافه کنید.
<pre dir="ltr"><span style="color: #0000ff;">AddManifestText</span>(
    …
    <span style="color: #a30a0a;">&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;</span>
<span style="color: #a30a0a;">    &lt;uses-permission android:name="android.permission.INTERNET"/&gt;</span>
<span style="color: #a30a0a;">    &lt;uses-permission android:name="android.permission.READ_PHONE_STATE"/&gt;
    &lt;uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/&gt;</span>)
    …
<span style="color: #008000;">'End of default text.</span>
<span style="color: #0000ff;">AddApplicationText</span>(
    <span style="color: #a30a0a;">&lt;!-- Required Activity --&gt;</span>
<span style="color: #a30a0a;">    &lt;activity</span>
<span style="color: #a30a0a;">        android:name="ir.tapsell.sdk.TapsellAdActivity"</span>
<span style="color: #a30a0a;">        android:configChanges="keyboardHidden|orientation|screenSize"/&gt;</span>
    )</pre>
در کد فوق سترسی‌ موقعیت (ACCESS_COARSE_LOCATION) اختیاری است اما درصورتیکه این دسترسی را نیز در برنامه خود قرار دهید، تبلیغات نشان‌داده‌شده به کاربر از کیفیت بالاتری برخوردار خواهد بود.
<p style="text-align: center;"><img class="size-full wp-image-476 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/01/B4A_6.png" alt="B4A" width="856" height="533" /></p>
اکنون پروژه شما آماده‌ی پیاده‌سازی تبلیغات تپسل است.
<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific, sans-serif, Tahoma, Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 1007px; top: 4771px; opacity: 0.05;">
<div id="s3gt_translate_tooltip_mini_logo" class="s3gt_translate_tooltip_mini" title="Translate selected text"></div>
<div id="s3gt_translate_tooltip_mini_sound" class="s3gt_translate_tooltip_mini" title="Play"></div>
<div id="s3gt_translate_tooltip_mini_copy" class="s3gt_translate_tooltip_mini" title="Copy text to Clipboard"></div>
</div>
</div>

<div dir="rtl" id="part2">
  <h1>پیاده‌سازی تبلیغات ویدئویی (جایزه دار-بینابینی) و تبلیغات بنری تمام صفحه در پروژه B4A</h1>
  <h3><strong>گام ۱: دریافت کلید تپسل</strong></h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

&nbsp;
<h3><strong>گام ۲: شروع کار با SDK تپسل</strong></h3>
برای ارتباط با کتابخانه‌ی تپ‌سل باید از کلاس <code>Tapsell</code> استفاده کنید. کافیست یک شیء از آن داشته باشید. این دسترسیبا نوشتن خط زیر در بخش <code>Globals</code> قابل انجام است:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #0000ff;">Sub</span> <strong>Globals</strong>
 <span style="color: #0000ff;">Private</span> <span style="color: #0000ff;"><span style="color: #9900ff;">tapsell </span>As</span> <span style="color: #00ccff;">Tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
&nbsp;

سپس در <code>activity</code> اصلی برنامه‌ی خود در قسمت <code>Activity_Create</code> خط زیر را اضافه کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.initialize(appKey)</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3><strong>گام ۳: دریافت تبلیغ</strong></h3>
نمایش یک تبلیغ ویدئویی در اپلیکیشن به دو صورت ممکن است صورت پذیرد. یک روش، نمایش تبلیغ بصورت stream می‌باشد. در این حالت، همزمان که کاربر درحال مشاهده بخشی از تبلیغ است، ادامه آن از اینترنت لود می‌گردد. ممکن است به دلیل کندی سرعت اینترنت، در این حالت کاربر با مکث‌های متعددی در هنگام دریافت و مشاهده تبلیغ مواجه شود. برای اینکه کاربر در هنگام نمایش تبلیغ منتظر نماند و تجربه کاربر در استفاده از اپلیکیشن بهبود یابد،روش دیگری نیز در SDK تپسل تعبیه شده است که در آن ابتدا فایل ویدئوی تبلیغاتی بطور کامل بارگذاری شده و سپس تبلیغ نمایش داده می‌شود.

همچنین در تپسل، تبلیغ می تواند در ناحیه‌های مختلفی از برنامه شما (مانند فروشگاه، انتهای هر مرحله، ابتدای مرحله جهت دریافت امتیاز دوبرابر، دریافت بنزین/لایف و ...) پخش شود. در تپسل به این ناحیه‌ها zone گفته می شود. ناحیه‌های هر اپلیکیشن در پنل تپسل تعریف می شوند.

با اجرای تابع زیر، می‌توانید یک درخواست تبلیغ به تپسل ارسال کرده و یک تبلیغ دریافت نمایید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.requestAd(zoneId,isCached)
</pre>
&nbsp;

هر درخواست شامل یک ورودی <code>zoneId</code> است که برای استفاده از ناحیه پیش فرض می توانید از یک رشته خالی استفاده نمایید. اطلاعات بیشتر درباره نواحی نمایش تبلیغ را می توانید از تیم فنی تپسل دریافت کنید. ورودی <code>isCached</code> یک متغیر <code>Boolean</code> می‌باشد که نشان می‌دهد که آیا تبلیغ باید ابتدا دانلود شده و سپس به کاربر نشان داده شود یا خیر.

[ht_message mstyle="danger" title="کش کردن ویدئو" " show_icon="" id="" class="" style="" ]تنها در ناحیه‌هایی که کاربر با احتمال زیادی پس از باز کردن اپلیکیشن تبلیغ آن را مشاهده می‌کند، از تبلیغ Cached استفاده کنید. جهت توضیحات بیشتر درباره روش انتخاب متد دریافت مناسب، <a href="https://answers.tapsell.ir/?ht_kb=cached-vs-streamed">اینجا</a> را مطالعه کنید.[/ht_message]

نتیجه درخواست بصورت <code>Event</code> به یک <code>Sub</code> در برنامه شما بازگردانده می‌شود. در صورت وجود یک تبلیغ، شناسه آن تبلیغ به یک ساب‌روتین (Sub) با نام <code>Tapsell_onAdAvailable</code> در کد شما داده می‌شود و می‌بایست آن را جهت نمایش تبلیغ ذخیره نمایید. در گام بعدی همه روتین‌های مورد استفاده در تپسل آورده و توضیح داده شده‌اند.

&nbsp;
<h3>گام ۴: اضافه کردن Sub های دریافت نتیجه درخواست تبلیغ</h3>
در Activity پروژه خطوط زیر را اضافه کنید:
<pre dir="ltr"><span style="color: #0000ff;">Sub</span> Tapsell_onAdAvailable (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' ad is available to show, store adId to show it later</span>
    <span style="color: #9900ff;">ad</span> = adId
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoAdAvailable (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No ad available now</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoNetwork (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No network</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onError (zoneId As <span style="color: #00ccff;">String</span>, error As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' Encountered and error while connecting to tapsell</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onExpiring (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' The stored ad with given adId is expiring and cannot be shown anymore</span>
    <span style="color: #9900ff;">ad</span> = <span style="color: #0000ff;">Null</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onOpened (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' The ad is being opened (shown)</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onClosed (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' The ad is being closed and ad show has finished, returning to user's Activity</span>
<span style="color: #0000ff;">End Sub</span>
</pre>
توضیحات روتین‌های مختلف و شرایط اجرا شدن آن‌ها در جدول ۱ آمده است.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۱ Sub های مربوط به دریافت نتیجه درخواست تبلیغ</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">تابع</th>
<th width="60%">توضیحات (زمان اجرا)</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onError (String)</td>
<td width="60%">هنگامی که هر نوع خطایی در پروسه‌ی دریافت تبلیغ بوجود بیاید</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onAdAvailable (String, String)</td>
<td width="60%">زمانی که تبلیغ دریافت شده و آماده‌ی نمایش باشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onNoAdAvailable (String)</td>
<td width="60%">در صورتی که تبلیغی برای نمایش وجود نداشته باشد.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onNoNetwork (String)</td>
<td width="60%">زمانی که دسترسی به شبکه موجود نباشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onExpiring (String, String)</td>
<td width="60%">زمانی که تبلیغ منقضی شود. هر تبلیغ مدت زمان مشخصی معتبر است و در صورتی که تا قبل از آن نمایش داده نشود منقضی شده و دیگر قابل نمایش نخواهد بود.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onOpened (String, String)</td>
<td width="60%">زمانی که تبلیغ شروع به پخش شود.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onClosed (String, String)</td>
<td width="60%">زمانی که پخش تبلیغ تمام شود و کاربر به اکتیویتی برنامه/بازی باز می گردد.</td>
</tr>
</tbody>
</table>
&nbsp;
<h3>گام ۵: نمایش تبلیغ</h3>
جهت نمایش تبلیغ، می‌توانید از تابع زیر استفاده نمایید (این تابع حداکثر یک بار برای هر شناسه تبلیغ قابل اجراست) :
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.showAd(adId,back_disabled,immersive_mode,rotation_mode,show_dialog)</pre>
&nbsp;

ورودی <code>adId</code> شناسه تبلیغ است که در گام قبل و در روتین <code>Tapsell_onAdAvailable</code> به شما داده شده‌است. ورودی‌های <code>disable_back</code> و <code>immersive_mode</code> از نوع <code>Boolean</code> هستند که جهت قفل کردن کلید back گوشی در هنگام نمایش تبلیغ جایزه‌دار و همینطور نمایش تبلیغ در حالت Immersive Mode (عدم نمایش دکمه‌های روی اسکرین و نمایش ویدئو بصورت Fullscreen در اندروید 4.4 و بالاتر) بکار می‌روند. ورودی <code>show_dialog</code> نیز یک متغیر <code>Boolean</code> است که در صورتی که مقدار آن برابر True باشد، در هنگام زدن دکمه Back در زمان نمایش ویدئو جایزه‌دار، یک اخطار به کاربر نشان داده می‌شود. ورودی <code>rotation_mode</code> برای تعیین جهت‌ نمایش ویدئو در دستگاه ( Orientation ) بکار می‌رود و مقادیر مختلف قابل استفاده برای آن در جدول ۲ آمده است.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۲ مقادیر قابل استفاده برای rotation_mode</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">مقدار</th>
<th width="60%">توضیحات</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">tapsell.ROTATION_LOCKED_PORTRAIT</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Portrait</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">tapsell.ROTATION_LOCKED_LANDSCAPE</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Landscape</div></td>
</tr>
<tr style="background-color: #fefefe;">
<td style="direction: ltr; text-align: center;" width="40%">tapsell.ROTATION_UNLOCKED</td>
<td width="60%">
<div style="text-align: center;" align="right">تعدم قفل کردن چرخش گوشی</div></td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">tapsell.ROTATION_LOCKED_REVERSED_PORTRAIT</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Reversed Portrait</div></td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">tapsell.ROTATION_LOCKED_REVERSED_LANDSCAPE</td>
<td width="60%">
<div style="text-align: center;" align="right">نمایش ویدئو در حالت Reversed Landscape</div></td>
</tr>
</tbody>
</table>
&nbsp;
<h3><strong>گام ۶: دریافت نتیجه نمایش تبلیغ</strong></h3>
نتیجه نمایش تبلیغ، در یک Sub با نام Tapsell_onAdShowFinished در Activity به شما برگردانده می‌شود. لذا باید این Sub را به کد خود اضافه کنید.
<pre dir="ltr"><span style="color: #0000ff;">Sub</span> Tapsell_onAdShowFinished (zoneId <span style="color: #0000ff;">As</span> <span style="color: #00ccff;">String</span>, adId <span style="color: #0000ff;">As</span> <span style="color: #00ccff;">String</span>, completed <span style="color: #0000ff;">As</span> <span style="color: #00ccff;">Boolean</span>, rewarded <span style="color: #0000ff;">As</span> <span style="color: #00ccff;">Boolean</span>)
    <span style="color: #008000;">' showing ad was finished</span>
<span style="color: #0000ff;">End Sub</span></pre>
در نتیجه‌ی دریافت شده، adId و zoneId شناسه مربوط به تبلیغ و محل نمایش آن در اپلیکیشن است. دو متغیر <code>completed</code> و <code>rewarded</code> از نوع <code>Boolean</code> بوده و نشان دهنده‌ی این است که کاربر ویدئو را تا انتها مشاهده کرده است یا خیر و تبلیغ نمایش داده شده از نوع جایزه‌دار بوده است یا خیر. در صورتی که کاربر تبلیغی از نوع جایزه دار را تا انتها مشاهده کند و هردو مقدار <code>completed</code> و <code>rewarded</code> برابر با <code>True</code> باشند، ، باید جایزه درون برنامه (سکه، اعتبار، بنزین یا ...) را به کاربر بدهید.

</div>

<div dir="rtl" id="part3">
  <h1>پیاده‌سازی تبلیغات بنری استاندارد در پروژه B4A</h1>
  <h3><strong>گام ۱: دریافت کلید تپسل</strong></h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

&nbsp;
<h3><strong>گام ۲: شروع کار با SDK تپسل</strong></h3>
برای ارتباط با کتابخانه‌ی تپ‌سل باید از کلاس <code>Tapsell</code> استفاده کنید. کافیست یک شیء از آن داشته باشید. این دسترسیبا نوشتن خط زیر در بخش <code>Globals</code> قابل انجام است:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #0000ff;">Sub</span> <strong>Globals</strong>
 <span style="color: #0000ff;">Private</span> <span style="color: #0000ff;"><span style="color: #9900ff;">tapsell </span>As</span> <span style="color: #00ccff;">Tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
&nbsp;

سپس در <code>activity</code> اصلی برنامه‌ی خود در قسمت <code>Activity_Create</code> خط زیر را اضافه کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.initialize(appKey)</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3>گام ۳: نمایش تبلیغ بنری استاندارد</h3>
جهت نمایش بنر استاندارد، باید محلی برای نمایش آن در صفحه در نظر بگیرید. بنر استاندارد، دارای سایزهای استانداردی است که در SDK تپسل مشخص شده اند. جهت نمایش بنر، از تابع زیر استفاده کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.fillBannerAd(adPanel, zoneId, bannerSize)
</pre>
&nbsp;

ورودی <code>adPanel</code> از جنس <code>Panel</code> بوده و نشان دهنده محلی است که برای نمایش تبلیغ انتخاب کرده‌اید. پارامتر<code>zoneId</code> شناسه تبلیغ‌گاه بنر استاندارد است که آن را می‌توانید از داشبورد تپسل دریافت کنید. ورودی آخر، نوع بنر را مشخص می‌کند. مقادیر قابل قبول انواع بنر، در کلاس <code>Tapsell</code> بصورت <code>tapsell.BANNER_WxH</code> آورده شده‌اند که  <code>W</code>  و  <code>H</code>  به ترتیب عرض و طول بنر استاندارد هستند.

&nbsp;
<h3>گام ۴: تنظیم Callback</h3>
اگر میخواهید callback های مربوط به نمایش بنر را مدیریت کنید میتوانید از قطعه کد زیر استفاده کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;">Sub Tapsell_onNoBannerAdAvailable (zoneId As String)
    //Todo
End Sub

Sub Tapsell_onBannerNoNetwork (zoneId As String)
    //Todo
End Sub

Sub Tapsell_onBannerAdRequestFilled (zoneId As String)
    //Todo
End Sub

Sub Tapsell_onBannerAdMadeHidden (zoneId As String)
    //Todo
End Sub

Sub Tapsell_onBannerAdError (zoneId As String, error As String)
    /Todo
End Sub</pre>
<h3></h3>
<h3>گام ۵: مخفی کردن و نمایش بنر</h3>
به صورت پیش فرض زمانی که تبلیغات بنری دریافت میشود به صفحه اضافه میگردد و Visible می شود. اگر بنا به هر دلیلی میخواهید بنر را مخفی کنید یا بنر مخفی شده را نمایش دهید از این کد استفاده کنید:
  </div>

<div dir="rtl" id="part4">
  <h1>پیاده‌سازی تبلیغات بنری هم‌نما (Native Banner) در پروژه B4A</h1>
  <h3><strong>گام ۱: دریافت کلید تپسل</strong></h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

&nbsp;
<h3><strong>گام ۲: شروع کار با SDK تپسل</strong></h3>
برای ارتباط با کتابخانه‌ی تپ‌سل باید از کلاس <code>Tapsell</code> استفاده کنید. کافیست یک شیء از آن داشته باشید. این دسترسیبا نوشتن خط زیر در بخش <code>Globals</code> قابل انجام است:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #0000ff;">Sub</span> <strong>Globals</strong>
 <span style="color: #0000ff;">Private</span> <span style="color: #0000ff;"><span style="color: #9900ff;">tapsell </span>As</span> <span style="color: #00ccff;">Tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
&nbsp;

سپس در <code>activity</code> اصلی برنامه‌ی خود در قسمت <code>Activity_Create</code> خط زیر را اضافه کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.initialize(appKey)</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3>گام ۳: ایجاد Layout تبلیغ هم‌نما</h3>
در تبلیغات هم‌نما، شما قادر هستید ویژگی‌های ظاهری هر یک از اجزای تبلیغ (اندازه، محل قرارگیری، رنگ فونت و…) را بصورتی که هماهنگ با محتوای اپلیکیشن شما باشد تعیین کنید. لذا باید Layout هم‌نما اپلیکیشن خود را که مدنظرتان است ایجاد نموده و از آن برای نمایش تبلیغ استفاده نمایید. این Layout می‌تواند شامل یک لوگو، یک عنوان، توضیحات، یک نشانگر آگهی بودن، یک بنر و یک دکمه برای دعوت از کاربر به انجام عمل تبلیغ باشد. جهت نمایش تبلیغ باید این اجزا را به SDK تپسل بدهید تا تبلیغ را در آن نمایش دهد.

جهت ایجاد Layout تبلیغ هم‌نما می‌توانید از Designer استفاده کرده و یک قالب مانند زیر آماده کنید.

<img class="size-full wp-image-1486 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/06/b4a_designer_native.png" alt="" width="922" height="601" />

&nbsp;
<h3><strong>گام ۴: درخواست تبلیغ بنری هم‌نما</strong></h3>
در تپسل، تبلیغ می تواند در ناحیه‌های مختلفی از برنامه شما (مانند منو اصلی، بین پست‌ها و ...) پخش شود. در تپسل به این ناحیه‌ها zone گفته می شود. ناحیه‌های هر اپلیکیشن در داشبورد تپسل تعریف می شوند.

با اجرای تابع زیر، می‌توانید یک درخواست تبلیغ به تپسل ارسال کرده و یک تبلیغ دریافت نمایید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.requestNativeBannerAd(zoneId)
</pre>
&nbsp;

هر درخواست شامل یک ورودی شناسه تبلیغ‌گاه (<code>zoneId)</code> است که نشانگر محل نمایش تبلیغ در اپلیکیشن شماست. تبلیغ‌گاه مرتبط با این شناسه در داشبورد تپسل باید از نوع بنری هم‌نما باشد.

نتیجه درخواست بصورت <code>Event</code> به یک <code>Sub</code> در برنامه شما بازگردانده می‌شود. در صورت وجود یک تبلیغ، شناسه آن تبلیغ به یک ساب‌روتین (Sub) با نام <code>Tapsell_onNativeBannerAdAvailable</code> در کد شما داده می‌شود و می‌بایست آن را جهت نمایش تبلیغ ذخیره نمایید. در گام بعدی همه روتین‌های مورد استفاده در تپسل آورده و توضیح داده شده‌اند.

&nbsp;
<h3>گام ۵: اضافه کردن روتین‌های دریافت نتیجه درخواست تبلیغ</h3>
در Activity اصلی خود خطوط زیر را اضافه کنید:
<pre dir="ltr"><span style="color: #0000ff;">Sub</span> Tapsell_onNativeBannerAdAvailable (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' Native ad is available and ready to show</span>
    <span style="color: #9900ff;">ad</span> = adId
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoNativeBannerAdAvailable (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No ad available now</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoNetwork (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No network</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onError (zoneId As <span style="color: #00ccff;">String</span>, error As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' Encountered and error while connecting to tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
توضیحات روتین‌های مختلف و شرایط اجرا شدن آن‌ها در جدول ۱ آمده است.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۱ روتین‌های مربوط به دریافت نتیجه درخواست تبلیغ هم‌نما</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">تابع</th>
<th width="60%">توضیحات (زمان اجرا)</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onError (String)</td>
<td width="60%">هنگامی که هر نوع خطایی در پروسه‌ی دریافت تبلیغ بوجود بیاید</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onNativeBannerAdAvailable (String, String)</td>
<td width="60%">زمانی که تبلیغ دریافت شده و آماده‌ی نمایش باشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onNoNativeBannerAdAvailable (String)</td>
<td width="60%">در صورتی که تبلیغی برای نمایش وجود نداشته باشد.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onNoNetwork (String)</td>
<td width="60%">زمانی که دسترسی به شبکه موجود نباشد.</td>
</tr>
</tbody>
</table>
&nbsp;
<h3>گام ۶: نمایش تبلیغ</h3>
جهت نمایش تبلیغ، می‌توانید از تابع زیر استفاده نمایید (این تابع حداکثر یک بار برای هر شناسه تبلیغ قابل اجراست) :
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.fillNativeBannerAd(adId,lblTitle,lblDescription,ivBanner,ivLogo,btnCallToAction,lblSponsored,adContainer)</pre>
&nbsp;

ورودی <code>adId</code> شناسه تبلیغ است که در گام قبل و در روتین <code>Tapsell_onNativeBannerAdAvailable</code> به شما داده شده‌است. ورودی‌های بعدی از نوع <code>ImageView</code>، <code>Label</code>، <code>Panel</code> و یا <code>Button</code> هستند که به ترتیب نشانگر عنوان تبلیغ، توضیحات تبلیغ، پنل نمایش ویدئو، لوگو، دکمه دعوت از کاربر و نشانگر آگهی بودن می‌باشند. آخرین ورودی نیز <code>Panel</code> دربرگیرنده تبلیغ هست که اختیاری بوده و می‌تواند <code>Null</code> باشد.
برای نمایش تبلیغ حتما باید عنوان و یکی از دو مورد دکمه دعوت از کاربر و <code>Panel</code> دربرگیرنده به SDK ارسال شوند.

&nbsp;
</div>
<div dir="rtl" id="part5">
  <h1>پیاده‌سازی تبلیغات ویدئویی هم‌نما (Native Video) در پروژه B4A</h1>
  <h3><strong>گام ۱: دریافت کلید تپسل</strong></h3>
ابتدا وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.

&nbsp;
<h3><strong>گام ۲: شروع کار با SDK تپسل</strong></h3>
برای ارتباط با کتابخانه‌ی تپ‌سل باید از کلاس <code>Tapsell</code> استفاده کنید. کافیست یک شیء از آن داشته باشید. این دسترسیبا نوشتن خط زیر در بخش <code>Globals</code> قابل انجام است:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #0000ff;">Sub</span> <strong>Globals</strong>
 <span style="color: #0000ff;">Private</span> <span style="color: #0000ff;"><span style="color: #9900ff;">tapsell </span>As</span> <span style="color: #00ccff;">Tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
&nbsp;

سپس در <code>activity</code> اصلی برنامه‌ی خود در قسمت <code>Activity_Create</code> خط زیر را اضافه کنید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.initialize(appKey)</pre>
ورودی appKey کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید.

&nbsp;
<h3>گام ۳: ایجاد Layout تبلیغ هم‌نما</h3>
در تبلیغات هم‌نما، شما قادر هستید ویژگی‌های ظاهری هر یک از اجزای تبلیغ (اندازه، محل قرارگیری، رنگ فونت و…) را بصورتی که هماهنگ با محتوای اپلیکیشن شما باشد تعیین کنید. لذا باید Layout هم‌نما اپلیکیشن خود را که مدنظرتان است ایجاد نموده و از آن برای نمایش تبلیغ استفاده نمایید. این Layout می‌تواند شامل یک لوگو، یک عنوان، توضیحات، یک نشانگر آگهی بودن، یک بنر و یک دکمه برای دعوت از کاربر به انجام عمل تبلیغ باشد. جهت نمایش تبلیغ باید این اجزا را به SDK تپسل بدهید تا تبلیغ را در آن نمایش دهد.

جهت ایجاد Layout تبلیغ هم‌نما می‌توانید از Designer استفاده کرده و یک قالب مانند زیر آماده کنید.

<img class="size-full wp-image-1499 aligncenter" src="https://answers.tapsell.ir/wp-content/uploads/2017/06/b4a_designer_native_video.png" alt="" width="983" height="645" />

&nbsp;
<h3><strong>گام ۴: درخواست تبلیغ ویدئویی هم‌نما</strong></h3>
در تپسل، تبلیغ می تواند در ناحیه‌های مختلفی از برنامه شما (مانند منو اصلی، بین پست‌ها و ...) پخش شود. در تپسل به این ناحیه‌ها zone گفته می شود. ناحیه‌های هر اپلیکیشن در داشبورد تپسل تعریف می شوند.

با اجرای تابع زیر، می‌توانید یک درخواست تبلیغ به تپسل ارسال کرده و یک تبلیغ دریافت نمایید:
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.requestNativeVideoAd(zoneId)
</pre>
&nbsp;

هر درخواست شامل یک ورودی شناسه تبلیغ‌گاه (<code>zoneId)</code> است که نشانگر محل نمایش تبلیغ در اپلیکیشن شماست. تبلیغ‌گاه مرتبط با این شناسه در داشبورد تپسل باید از نوع بنری هم‌نما باشد.

نتیجه درخواست بصورت <code>Event</code> به یک <code>Sub</code> در برنامه شما بازگردانده می‌شود. در صورت وجود یک تبلیغ، شناسه آن تبلیغ به یک ساب‌روتین (Sub) با نام <code>Tapsell_onNativeBannerAdAvailable</code> در کد شما داده می‌شود و می‌بایست آن را جهت نمایش تبلیغ ذخیره نمایید. در گام بعدی همه روتین‌های مورد استفاده در تپسل آورده و توضیح داده شده‌اند.

&nbsp;
<h3>گام ۵: اضافه کردن روتین‌های دریافت نتیجه درخواست تبلیغ</h3>
در Activity اصلی خود خطوط زیر را اضافه کنید:
<pre dir="ltr"><span style="color: #0000ff;">Sub</span> Tapsell_onNativeVideoAdAvailable (zoneId As <span style="color: #00ccff;">String</span>, adId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' Native ad is available and ready to show</span>
    <span style="color: #9900ff;">ad</span> = adId
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoNativeVideoAdAvailable (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No ad available now</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onNoNetwork (zoneId As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' No network</span>
<span style="color: #0000ff;">End Sub</span>

<span style="color: #0000ff;">Sub</span> Tapsell_onError (zoneId As <span style="color: #00ccff;">String</span>, error As <span style="color: #00ccff;">String</span>)
    <span style="color: #008000;">' Encountered and error while connecting to tapsell</span>
<span style="color: #0000ff;">End Sub</span></pre>
توضیحات روتین‌های مختلف و شرایط اجرا شدن آن‌ها در جدول ۱ آمده است.

&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6"><caption>جدول ۱ روتین‌های مربوط به دریافت نتیجه درخواست تبلیغ هم‌نما</caption>
<tbody>
<tr style="border-bottom: 1px solid #ddd;">
<th width="40%">تابع</th>
<th width="60%">توضیحات (زمان اجرا)</th>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onError (String)</td>
<td width="60%">هنگامی که هر نوع خطایی در پروسه‌ی دریافت تبلیغ بوجود بیاید</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onNativeVideoAdAvailable (String, String)</td>
<td width="60%">زمانی که تبلیغ دریافت شده و آماده‌ی نمایش باشد.</td>
</tr>
<tr style="background-color: #fefefe;">
<td dir="ltr" width="40%">Tapsell_onNoNativeVideoAdAvailable (String)</td>
<td width="60%">در صورتی که تبلیغی برای نمایش وجود نداشته باشد.</td>
</tr>
<tr style="background-color: #f2f2f2;">
<td dir="ltr" width="40%">Tapsell_onNoNetwork (String)</td>
<td width="60%">زمانی که دسترسی به شبکه موجود نباشد.</td>
</tr>
</tbody>
</table>
&nbsp;
<h3>گام ۶: نمایش تبلیغ</h3>
جهت نمایش تبلیغ، می‌توانید از تابع زیر استفاده نمایید (این تابع حداکثر یک بار برای هر شناسه تبلیغ قابل اجراست) :
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;">tapsell</span>.fillNativeVideoAd(adId,autoStartVideo,fullscreenOnClick,lblTitle,lblDescription,pVideoContainer,ivLogo,btnCallToAction,lblSponsored,adContainer)</pre>
&nbsp;

ورودی <code>adId</code> شناسه تبلیغ است که در گام قبل و در روتین <code>Tapsell_onNativeBannerAdAvailable</code> به شما داده شده‌است. ورودی های  <code>autoStartVideo</code> و <code>fullscreenOnClick</code> از نوع  <code>Boolean</code> هستند. <code>autoStartVideo</code> مشخص کننده این است که ویدئو بعد از لود شدن در صفحه بصورت اتوماتیک اجرا شود یا خیر. همچنین <code>fullscreenOnClick</code> تعیین کننده این است که آیا زمان کلیک کردن کاربر روی ویدئو باید ویدئو بصورت تمام صفحه نمایش داده شود یا خیر. ورودی‌های بعدی از نوع <code>ImageView</code>، <code>Label</code>، <code>Panel</code> و یا <code>Button</code> هستند که به ترتیب نشانگر عنوان تبلیغ، توضیحات تبلیغ، پنل نمایش ویدئو، لوگو، دکمه دعوت از کاربر و نشانگر آگهی بودن می‌باشند. آخرین ورودی نیز <code>Panel</code> دربرگیرنده تبلیغ هست که اختیاری بوده و می‌تواند <code>Null</code> باشد.
برای نمایش تبلیغ حتما باید عنوان و یکی از دو مورد دکمه دعوت از کاربر و <code>Panel</code> دربرگیرنده به SDK ارسال شوند.
</div>
<div dir="rtl" id="part6">
  <h1>موارد پیشرفته‌تر در SDK</h1>
  &nbsp;
<h3>بررسی وجود تبلیغ دریافت شده</h3>
در صورتیکه در SDK برای یک ناحیه درخواست دریافت تبلیغ از سرور انجام داده باشید، علاوه بر <code>action</code> داده شده در تابع <code>requestAd</code>، می‌توانید از تابع زیر نیز برای چک کردن وجود تبلیغ دریافت شده استفاده کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;"><strong>tapsell</strong></span>.isAdReadyToShow(zoneId <span style="color: #0000ff;">As</span> <strong><span style="color: #00ccff;">String</span></strong>)</pre>
ورودی zoneId در این تابع، شناسه ناحیه نمایش تبلیغ است که از پنل تپسل دریافت نموده‌اید.

&nbsp;
<h3>دریافت نسخه SDK تپسل</h3>
درصورتی که نیازمند به دانستن نسخه تپسل پیاده‌سازی شده در اپلیکیشن خود هستید، می‌توانید با فراخوانی تابع زیر عنوان نسخه را دریافت نمایید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;"><strong>tapsell</strong></span>.Version</pre>
&nbsp;
<h3>تنظیمات کشینگ</h3>
همانطور که در بخش <a href="https://answers.tapsell.ir/?ht_kb=android-sdk-p2">پیاده‌سازی SDK تپسل در اپلیکیشن</a> گفته شد، از نسخه ۳ به بعد تپسل قابلیت نمایش ویدئو بصورت استریم و همینطور نمایش ویدئو بعد از دانلود فایل (کشینگ) را دارد. با این قابلیت، قبل از نمایش تبلیغ و در هنگامی که کاربر مشغول استفاده از اپلیکیشن است، ویدئو بطور کامل دریافت می‌شود و کاربر بدون هیچگونه مکثی می‌تواند ویدئو را تماشا کند.

از طرف دیگر، در اپلیکیشن‌ها و بازی‌های آنلاین، دریافت ویدئو در پس زمینه ممکن است در روند اصلی برنامه خلل ایجاد کند و آن را کند نماید.

جهت جلوگیری از اشغال پهنای باند زیاد توسط تپسل، شما می‌توانید درصد مشخصی از کل پهنای باند موجود را به دانلود ویدئو اختصاص دهید. جهت انجام این عمل، تابع زیر را در آغاز برنامه و تابع  <code>initialize</code> (قبل از درخواست تبلیغ) فراخوانی کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><strong><span style="color: #9900ff;">tapsell</span></strong>.MaxAllowedBandwidthUsagePercentage  = maxPercentage</pre>
که ورودی <code>maxPercentage</code> حداکثر درصدی از پهنای باند در دسترس اپلیکیشن است که SDK تپسل از آن برای دریافت ویدئو استفاده می‌کند. این پارامتر باید عددی بین 0 تا 100 باشد.

همچنین درصورتی که از سرعت دانلود واقعی کاربر در اپلیکیشن خود اطلاع دارید می‌توانید به کمک تابع زیر، مقدار حداکثر پهنای باند قابل استفاده برای دانلود ویدئو را به کمک تابع زیر تنظیم کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;"><strong>tapsell</strong></span>.MaxAllowedBandwidthUsage = maxBpsSpeed</pre>
ورودی دوم این تابع، میزان حداکثر سرعت دانلود ویدئو است که باید به واحد بایت بر ثانیه داده شود.

در صورتی که در بخشی از اپلیکیشن خود می‌خواهید تنظیمات مربوط به محدودیت سرعت دانلود را غیرفعال نمایید، از تابع زیر استفاده کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><strong><span style="color: #9900ff;">tapsell</span></strong>.clearBandwidthUsageConstrains()</pre>
&nbsp;

توضیحات بیشتر درباره کشینگ و استریمینگ در SDK تپسل را <a href="https://answers.tapsell.ir/?ht_kb=cached-vs-streamed">اینجا</a> بخوانید.

&nbsp;
<h3>تنظیمات دسترسی‌های زمان اجرا (Run Time Permissions)</h3>
از نسخه اندروید 6 و بالاتر، برخی دسترسی‌ها در اندروید در زمان اجرا باید از کاربر درخواست شوند. یکی از این دسترسی‌ها، دسترسی <code>READ_PHONE_STATE</code> است که توسط تپسل استفاده می‌شود و بدون این دسترسی، SDK تپسل قابل استفاده نیست. برای سهولت پیاده‌سازی، SDK تپسل بصورت اتوماتیک دسترسی‌ها را از کاربر درخواست می‌کند و هربار درخواست تبلیغی ارسال شود، درصورتی که دسترسی مورد نیاز موجود نباشد، این دسترسی از کاربر خواسته می شود.

در صورتی که شما می‌خواهید درخواست دسترسی‌ها از کاربر را به نحو دیگری در اپلیکیشن خود پیاده‌سازی نمایید، می‌توانید این ویژگی پیش‌فرض را در تپسل غیر فعال کنید. جهت انجام این عمل، کافیست از تابع زیر در زمان آغاز برنامه و بعد از تابع  <code>initialize</code> استفاده کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><strong><span style="color: #9900ff;">tapsell</span></strong>.AutoHandlePermissions = <span style="color: #00ccff;">true</span></pre>
با این دستور، درخواست دسترسی توسط SDK تپسل به کاربر نشان داده نمی‌شود و می‌توانید بصورت مطلوب خود آن را پیاده‌سازی نمایید.

&nbsp;
<h3>حالت دیباگ (Debug Mode)</h3>
در هنگام پیاده‌سازی SDK، ممکن است بدلیل عدم رعایت نکات گفته شده و یا خطاهای دیگر، تبلیغات قابل دریافت و نمایش نباشند. حالت دیباگ جهت تسهیل فرآیند عیب‌یابی در هنگام پیاده‌سازی تعبیه شده است. با فعالسازی این حالت، می‌توانید گزارش‌های لاگ نمایش داده شده توسط SDK را در logcat مشاهده کنید. برای فعالسازی حالت دیباگ کافیست از تابع زیر در آغاز برنامه استفاده کنید.
<pre dir="ltr" style="direction: ltr; margin: 0; line-height: 125%;"><span style="color: #9900ff;"><strong>tapsell</strong></span>.DebugMode =<span style="color: #00ccff;">true</span></pre>
سپس با استفاده از نرم‌افزار Android Studio، از بخش Android Monitor، قسمت logcat را باز کرده و لاگ‌های نوشته شده را بررسی کنید. برای مشاهده لاگ‌های برنامه unity، در بخش فیلتر کلمه unity را وارد کنید.
<p style="text-align: center;"><img src="https://answers.tapsell.ir/wp-content/uploads/2017/02/logs-unity.png" /></p>

<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific, sans-serif, Tahoma, Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 871px; top: 2554px; opacity: 0.55;">
<div id="s3gt_translate_tooltip_mini_logo" class="s3gt_translate_tooltip_mini" title="Translate selected text"></div>
<div id="s3gt_translate_tooltip_mini_sound" class="s3gt_translate_tooltip_mini" title="Play"></div>
<div id="s3gt_translate_tooltip_mini_copy" class="s3gt_translate_tooltip_mini" title="Copy text to Clipboard"></div>
</div>
</div>
