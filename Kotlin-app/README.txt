# GreeksSmartLighting

Prerequisites: (TODO: UPDATE)
minSDK: 21? TODO: Go through and try to remove libraries so as to lower minSDK requirements
Gradle-version?
Bluetooth-set up based on https://github.com/webianks/BluetoothChat by Ramankit
BLE-set up based on ??

Tutorial: (TODO: UPDATE)
Two Android phones running the same app at the same time
Both must have bluetooth and location active (TODO: why location?)
If they cannot find each other, press 'Make Visible' in the main-activity and try connecting again.
When connected successfully, click on light to bring forward setting-buttons


General todos:
Get the damn code for from the Python Server on the RPI and add it to Git
Extract all string ressources to string.xml
Prevent data loss on rotationchange. Lock orientation?
Make sure layout for each activity and fragment is generic regardless of phone
Go through all activities / fragments and make sure the backtracking is correct (activies/fragments behave as expected when pressing the back-button and are thus stored correctly on the stack)
Judge all layout. Get an outsider to use the app.
Write unit-tests for as much functionality as possible.


General-progress:

09-08: 
Changed textviews in LightingSettingsFragment to actually represent functionality.
Added ConnectionPreferencesFragment. Shown when clicking on 'connection' textview in LightingSettingsFragment
Various layout changes. 

Skeleton for layout app should now be set up - 
We can scan for connections (main-activity), though Bluetooth should be changed for to BLE ( https://medium.com/@martijn.van.welie/making-android-ble-work-part-1-a736dcd53b02 )
Interface for simulating lighting changes is set up in LightingSettingsFragment
Inteface for connection setting is set up (ConnectionPreferencesFragment, entered from LightingSettingsFragment)

05-08-2021:
Using NRF-CONNECT from app-store, we can successfully pair (but not bond) to BLE server on the RPI 3b+ - though only on the SM-A125. It doesn't work on the Huawei. Why is this??? TODO: PRETTY IMPORTANT

04-08-2021:
App on phones samsung SM-A125 and can Huawei ATU-L21 successfully connect with each other using standard Bluetooth. Not checked which version of the protocol each phones are running. 
App on both phones can also connect with Python Bluetooth-server set up on the RPI 3 b+. However, as this seemingly demands pre-pairing (BlueZ demands it, the standard Bluetooth protocol encourages it for security reasons?) BLE will be chosen instead.


