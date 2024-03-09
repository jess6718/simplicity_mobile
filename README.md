# simplicity_mobile

## Installation

### Downloading Android Studio
get started with Simplicity_code 11.0 or above, you'll need to download and install Android Studio. You can download the latest version from the official Android Developers website.

### Connecting Your Mobile Device to Android Studio

To debug your app on a mobile device, follow these steps:

#### Enable USB Debugging on Your Mobile Device:

1. On your mobile device, navigate to Settings.
2. Scroll down and select "Developer options".
3. Find the "USB debugging" option and enable it.

#### Connect Your Mobile Device to Your Computer:

- Use a USB cable to connect your mobile device to your computer.

#### Setting Up Port Forwarding (Optional):

If needed for your specific setup, you can set up port forwarding using ADB (Android Debug Bridge) to connect your mobile device to Android Studio. This is typically used when debugging over Wi-Fi or for certain network configurations.

#### Run Your App in Android Studio:

1. Open your project in Android Studio.
2. Click on the "Run" button (the green triangle icon) or select "Run" > "Run 'app'" from the menu.
3. Android Studio will detect your connected device and install the app on it. If prompted on your device, allow USB debugging.


### Procedure of Changing IP via Gradle File:

1. **Select Build Variant:** Locate it in the left toolbar.

2. **Choose Variant:** Switch to either "release" mode or "debug" mode, whichever is applicable.

3. **Sync Project with Gradle Files:** Locate the "elephant-like" icon on the toolbar at the top right and click on it.

4. **Run App:** Click on the "Run" button in the toolbar at the top right.
