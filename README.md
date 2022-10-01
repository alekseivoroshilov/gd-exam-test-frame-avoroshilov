# Welcome to my Testing framework repository

## Description

The purpose of this repository is to store my testing framework I created for education and practice using Java.

It is designed to test a specific Android Dictionary app.

This framework satisfies the requirements of the following patterns:

- Page Object
- Fluent/Chain of Invocations
- Page Factory

## Plugins and dependencies:

- Appium client 7.5.1
- Allure 2.18.1
- TestNG 7.1.0
- Selenium Java 3.141.59
- AspectJ 1.9.6

## Pre-setup
1. brew update
2. brew install nvm
3. mkdir ~/.nvm
4. vim ~/.bash_profile
5. add lines to ~/.bash_profile ( or ~/.zshrc for macOS Catalina or later) - 
   - export NVM_DIR=~/.nvm
   - source $(brew --prefix nvm)/nvm.sh
6. Press ESC + :wq to save and close your file.
7. source ~/.bash_profile
8. nvm install 15.14
9. xcode-select --install (if you're going to create or use tests for iOS)
10. npm install -g appium
11. download last version of Appium GUI using https://github.com/appium/appium-desktop/releases
12. Install Android Studio
13. Setup Android SDK throughout Android Studio.
14. npm install -g uiautomator2
15. npm install -g appium-doctor
16. Add lines to profile
    - export ANDROID_HOME=/usr/local/share/android-sdk
    - export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
    - export JAVA_HOME=/usr/libexec/java_home
    - export PATH=$PATH:$JAVA_HOME/bin

17. restart the terminal window
18. (In your project) brew install git

In case any difficulty while completing pre-setup, I recommend using helpful links below:
 - [Common](https://automationhacks.io/slides/2021/appium-conf/hello-appium-writing-your-first-tests/04-common-libraries/)
 - [Android](https://automationhacks.io/slides/2021/appium-conf/hello-appium-writing-your-first-tests/05-setup-for-android/)
 - [iOS](https://automationhacks.io/slides/2021/appium-conf/hello-appium-writing-your-first-tests/09-setup-for-ios/)

## Usage

1. Open `src/main/resources/config`
    - If you want to run tests for Android, fill the desired capabilities for `android.json`
    - If iOS,  fill the desired capabilities for `iOS.json`
2. Open `src/test/resources/allure.properties`
3. Edit the parameters according to your plans:
    - for _target_ write `android` or `ios` , which means the platform this framework will select for testing
    - for _autoStartAppiumServer_ write `true` of `false`, which means whether you want server starting by itself or the framework uses the port from configuration in `android.json` or `ios.json`
4. Maven(top-right corner) -> Plugins -> clean -> double tap **clean:clean**
5. Click on the right mouse button on any of the suites in `src/test/resources/suites` and select **Run**.
6. Maven(top-right corner) -> Plugins -> allure -> double tap **allure:report**
7. Find `index.html` in `target` folder and open it from IDE using chrome to see allure report.

## Test creation

1. Create package and a Class in `src/test/java`
2. Add suite `src/test/resources/suites/yoursuite.xml`
3. Adding a pageObject if needed:
    - Add Classes here `src/main/java/data/pages`
    - Add a variable in BaseTest class
4. Add all page related methods to their abstract classes
5. Add constants.Constants to `src/main/java/data/constants/Constants.java`
