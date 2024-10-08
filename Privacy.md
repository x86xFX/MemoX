## MemoX: Privacy policy

Welcome to the MemoX Note-taking app for Android!

This is an open-source Android app developed by Theekshana Nirmana. The source code is available on GitHub under the Apache-2.0 license.

As an avid Android user myself, I take privacy very seriously.
I know how frustrating it is when apps collect your data without your knowledge.

### Data collected by the app

I hereby state, to the best of my knowledge and belief, that I have not programmed this app to collect any personally identifiable information. All data is stored locally in your device only, and can be simply erased by clearing the app's data or uninstalling it.

### Explanation of permissions requested in the app

The list of permissions required by the app can be found in the `AndroidManifest.xml` file:
<br/>

|                                               Permission                                               | Why it is required                                                                                                                                                                                                                                   |
|:------------------------------------------------------------------------------------------------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `android.permission.CAMERA` | Has to be granted by the user manually; can be revoked by the system or the user at any time. This is required to capture images. |
| `android.permission.ACCESS_FINE_LOCATION` | Required by the app to get the current user's precise location. Has to be granted by the user manually; can be revoked by the system or the user at any time. |
| `android.permission.ACCESS_COARSE_LOCATION` | Required by the app to get the current user's approximate location. Has to be granted by the user manually; can be revoked by the system or the user at any time. |
| `android.permission.INTERNET` and `android.permission.ACCESS_NETWORK_STATE` | Required access to the internet. |

 <hr style="border:1px solid gray">

If you find any security vulnerability that has been inadvertently caused by me, or have any question regarding how the app protectes your privacy, please post a discussion on GitHub, and I will surely try to fix it/help you.

Yours sincerely,  
Theekshana Nirmana
