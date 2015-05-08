package com.crossbowffs.emptyswipe;

import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!"com.android.systemui".equals(lpparam.packageName)) {
            return;
        }

        String className = "com.android.systemui.statusbar.phone.PhoneStatusBar";
        String methodName = "shouldDisableNavbarGestures";

        XposedHelpers.findAndHookMethod(className, lpparam.classLoader, methodName, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });
    }
}
