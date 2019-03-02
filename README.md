# bouncer_loader

[![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()
[![](https://jitpack.io/v/yalantis/jellytoolbar.svg)](https://jitpack.io/#NazDov/bouncer_loader)


<img src="bouncer_loader_gif.gif"/>

## Requirements
- Android SDK 16+

## Usage

Add to your root build.gradle:
```Groovy
allprojects {
	repositories {
	  ...
	  maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:
```Groovy
dependencies {
  compile 'com.github.NazDov:bouncer_loader:v1.0'
}
```

## How to use this library in your project?

First of all, add `TripleDotLoaderView` to the xml layout of your activity, so it looks like that:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <com.github.nazdov.tripple_dot_loader_view.TripleDotLoaderView
            android:id="@+id/bouncerLoaderView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:stateLoadingColor="#03A9F4"
            app:dotColor="#3F51B5"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent">
    </com.github.nazdov.tripple_dot_loader_view.TripleDotLoaderView>

</android.support.constraint.ConstraintLayout>
```


## License

	The MIT License (MIT)

	Copyright © 2017 NazDov

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
