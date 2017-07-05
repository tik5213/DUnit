# DUnit
DUnit is used to test or display functions or components to others.

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
  compile 'top.ftas:dunit:0.1'
  apt 'top.ftas:dunit-compiler:0.1'
}
```

Make sure the line `apply plugin ...` is placed somewhere at the top of the file.

#Usage

1. Define groups:

	```java
	@DUnitGroup("HttpGroup")
	public class HttpGroup extends DUnitRootGroup{}
	```

2. Create a display unit：

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







