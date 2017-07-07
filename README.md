# DUnit
DUnit is used to test or display functions or components to others.

![img1][1] ![img2][2] ![img3][3] ![img4][4]

# Download
Add this to you project-level `build.gradle`:

```groovy
buildscript {
  repositories {
    mavenCentral()
   }
  dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
  }
}
```

Add this to your module-level `build.gradle`:

```groovy
apply plugin: 'com.neenbedankt.android-apt'

android {
  ...
}

dependencies {
  compile 'top.ftas:dunit:0.2'
  apt 'top.ftas:dunit-compiler:0.2'
}
```

Make sure the line `apply plugin ...` is placed somewhere at the top of the file.

# Usage

1. Modify AndroidManifest.xml:

	```java
	<activity android:name="top.ftas.dunit.activity.DUnitSimpleListActivity">
		<intent-filter>
			<action android:name="android.intent.action.MAIN"/>
			<category android:name="android.intent.category.LAUNCHER"/>
		</intent-filter>
	</activity>
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
