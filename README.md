# DUnit
DUnit is used to test or display functions or components to others.

![img1][1] ![img2][2] ![img3][3] ![img4][4]

# Download

```groovy
dependencies {
  compile 'top.ftas:dunit:2.0'
  annotationProcessor 'top.ftas:dunit-compiler:2.0'
}
```

# Usage1

Add annotations to existing activities：
	
	```java
	@DUnit
	public class BaseActivity extends Activity{ /* Do something */ }
	```

# Usage2

1. Define groups:

	```java
	@DUnitGroup("HttpGroup")
	public class HttpGroup extends DUnitRootGroup{}
	```

2. Create a display unit:

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

# Usage3

### java

	```sh
	android {
		defaultConfig {
			javaCompileOptions {
				annotationProcessorOptions {
					arguments = [DUNIT_MODULE_NAME : "sample"]
				}
			}
		}
	}
	```
	
### kotlin

	```sh
	kapt {
		arguments {
			arg("DUNIT_MODULE_NAME", "sample_kotlin")
		}
	}
	```

 [1]: img/small/01.png
 [2]: img/small/02.png
 [3]: img/small/03.png
 [4]: img/small/04.png
