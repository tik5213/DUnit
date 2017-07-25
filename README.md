# DUnit
DUnit is used to test or display functions or components to others.

![img1][1] ![img2][2] ![img3][3] ![img4][4]

# Download

```groovy
dependencies {
  compile 'top.ftas:dunit:1.1'
  annotationProcessor 'top.ftas:dunit-compiler:1.1'
}
```

# Usage1

1. Modify AndroidManifest.xml:

	```java
	<activity android:name="top.ftas.dunit.activity.DUnitSimpleListActivity">
		<intent-filter>
			<action android:name="android.intent.action.MAIN"/>
			<category android:name="android.intent.category.LAUNCHER"/>
		</intent-filter>
	</activity>
	<activity android:name="top.ftas.dunit.activity.SingleFragmentActivity" />
	<activity android:name="top.ftas.dunit.activity.SingleSupportFragmentActivity" />
	<activity android:name=".BaseActivity" />
	```

2. Add annotations to existing activities：
	
	```java
	@DUnit
	public class BaseActivity extends Activity{ /* Do something */ }
	```

# Usage2

1. Modify AndroidManifest.xml:

	```java
	<activity android:name="top.ftas.dunit.activity.DUnitSimpleListActivity">
		<intent-filter>
			<action android:name="android.intent.action.MAIN"/>
			<category android:name="android.intent.category.LAUNCHER"/>
		</intent-filter>
	</activity>
	<activity android:name="top.ftas.dunit.activity.SingleFragmentActivity" />
	<activity android:name="top.ftas.dunit.activity.SingleSupportFragmentActivity" />
	```

2. Define groups:

	```java
	@DUnitGroup("HttpGroup")
	public class HttpGroup extends DUnitRootGroup{}
	```

3. Create a display unit:

	```java
	@DUnit(
			name = "RetrofitDisplayUnit",
			group = HttpGroup.class
	)
	public class RetrofitDisplayUnit extends AbstractDisplayUnit{
		@Override
		public void callUnit() { /* Do something */ }
	}
	```

 [1]: img/small/01.png
 [2]: img/small/02.png
 [3]: img/small/03.png
 [4]: img/small/04.png
