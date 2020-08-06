package com.water_io.stackscreens.utilities;

import android.os.Bundle;
import android.util.Log;

import com.water_io.stackscreens.entities.ScreenInfo;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Stack;

public class StackBackScreenV2 {
    private static final String TAG = StackBackScreenV2.class.getSimpleName();
    private static final String END_SCREEN = "END";

    private Stack<ScreenInfo> mAllScreenHistory = new Stack<>();
    public final static int EMPTY_HISTORY_SCREEN = -1;
    private ScreenInfo mTempScreenBack = new ScreenInfo(EMPTY_HISTORY_SCREEN, END_SCREEN, null);
    private int mSizeOfStack;

    public StackBackScreenV2(final int firstScreenInit, String screenName, Bundle bundleFirstScreenInit, int sizeOfStack) {
        this.mSizeOfStack = sizeOfStack;
        newScreenShowing(firstScreenInit, screenName, bundleFirstScreenInit);
    }

    public StackBackScreenV2(final int firstScreenInit, String screenName, int sizeOfStack) {
        this.mSizeOfStack = sizeOfStack;
        newScreenShowing(firstScreenInit, screenName, null);
    }

    public void newScreenShowing(int screenID, String screenName) {
        newScreenShowing(screenID, screenName, null);
    }

    public void newScreenShowing(int screenID, String screenName, Bundle bundle) {
        if (screenID == mTempScreenBack.getScreenID()) {
            Log.d(TAG, "newScreenShowing(), screen already showed");
            return;
        }
        Log.d(TAG, "newScreenShowing(), " + screenName + ((bundle == null) ? "" : ", has data"));

        if (mAllScreenHistory.size() > mSizeOfStack) {
            mAllScreenHistory.remove(mAllScreenHistory.size() - 1);
        }

        mAllScreenHistory.push(new ScreenInfo(screenID, screenName, bundle));
    }

    public void printStack(boolean isBundlePrint) {
        for (int i = 0; i < mAllScreenHistory.size(); i++) {
            StringBuilder bundleKeys = new StringBuilder();
            if (mAllScreenHistory.get(i).getBundle() != null) {
                bundleKeys = new StringBuilder("Bundle{ ");
                for (String key : mAllScreenHistory.get(i).getBundle().keySet()) {
                    bundleKeys.append(" ").append(key).append(" => ").append(mAllScreenHistory.get(i).getBundle().get(key)).append(";\n");
                }
                bundleKeys.append(" }\n");
            }

            Log.d("SCREEN-PRINT", (i + 1) + ": " + mAllScreenHistory.get(i).getScreenName() + ((mAllScreenHistory.get(i).getBundle() == null) ? "" : ", has data"+((isBundlePrint)?": "+bundleKeys:"")) + "\n");
        }
    }

    public ScreenInfo backToPrevScreen() {
        if (mAllScreenHistory.isEmpty() || mAllScreenHistory.size() < 2) {
            Log.d(TAG, "backToPrevScreen(), return END");
            return new ScreenInfo(EMPTY_HISTORY_SCREEN, END_SCREEN, null);
        }
        ScreenInfo screenInfo = mAllScreenHistory.get(mAllScreenHistory.size() - 2);
        mTempScreenBack = screenInfo;

        mAllScreenHistory.pop();
        Log.d(TAG, "backToPrevScreen(), return " + mTempScreenBack.getScreenName() + ((mTempScreenBack.getBundle() == null) ? "" : ", has data"));
        return screenInfo;
    }


    public int getCurrentScreen() {
        if (mAllScreenHistory.isEmpty())
            return EMPTY_HISTORY_SCREEN;
        return mAllScreenHistory.peek().getScreenID();
    }


}
