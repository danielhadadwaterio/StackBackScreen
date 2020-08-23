package com.water_io.stackscreens.utilities;

import android.os.Bundle;
import android.util.Log;

import com.water_io.stackscreens.entities.ScreenInfo;

import java.util.Stack;

public class StackBackScreenV3 {
    private static final String TAG = StackBackScreenV3.class.getSimpleName();
    private static final String END_SCREEN = "END";

    private Stack<ScreenInfo> mAllScreenHistory = new Stack<>();
    public final static int EMPTY_HISTORY_SCREEN = -1;
    private ScreenInfo mTempScreenBack = new ScreenInfo(EMPTY_HISTORY_SCREEN, END_SCREEN, null);
    private int mSizeOfStack;

    public StackBackScreenV3(final int firstScreenInit, String screenName, Bundle bundleFirstScreenInit, int sizeOfStack) {
        this.mSizeOfStack = sizeOfStack;
        newScreenShowing(firstScreenInit, screenName, bundleFirstScreenInit);
    }

    public StackBackScreenV3(final int firstScreenInit, String screenName, int sizeOfStack) {
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

        mRealScreenShowed = screenID;
        mAllScreenHistory.push(new ScreenInfo(screenID, screenName, bundle));
    }

    public void printStack(boolean isBundlePrint) {
        for (int i = 0; i < mAllScreenHistory.size(); i++) {
            StringBuilder bundleKeys = new StringBuilder();
            if (mAllScreenHistory.get(i).getBundle() != null) {
                bundleKeys = new StringBuilder("Bundle{ \n");
                for (String key : mAllScreenHistory.get(i).getBundle().keySet()) {
                    bundleKeys.append(" ").append(key).append(" => ").append(mAllScreenHistory.get(i).getBundle().get(key)).append(";\n");
                }
                bundleKeys.append(" }\n");
            }

            Log.d("SCREEN-PRINT", (i + 1) + ": " + mAllScreenHistory.get(i).getScreenName() + ((mAllScreenHistory.get(i).getBundle() == null) ? "" : ", has data" + ((isBundlePrint) ? ": " + bundleKeys : "")) + "\n");
        }
    }

    public ScreenInfo backToPrevScreen() {
        int AMOUNT_BACK=1;
        if (mAllScreenHistory.isEmpty()) {
            Log.d(TAG, "backToPrevScreen(), return END");
            return new ScreenInfo(EMPTY_HISTORY_SCREEN, END_SCREEN, null);
        } else {
            mAllScreenHistory.size();
        }
        ScreenInfo screenInfo = mAllScreenHistory.get(mAllScreenHistory.size() - AMOUNT_BACK);
        mTempScreenBack = screenInfo;

        mAllScreenHistory.pop();
        Log.d(TAG, "backToPrevScreen(), return " + mTempScreenBack.getScreenName() + ((mTempScreenBack.getBundle() == null) ? "" : ", has data"));
        mRealScreenShowed = mTempScreenBack.getScreenID();
        return screenInfo;
    }


    public int getCurrentScreenFromStuck() {
        if (mAllScreenHistory.isEmpty())
            return EMPTY_HISTORY_SCREEN;
        return mAllScreenHistory.peek().getScreenID();
    }


    private int mRealScreenShowed = EMPTY_HISTORY_SCREEN;

    public int getRealCurrentScreen() {
        return mRealScreenShowed;
    }

    public void setRealScreenShowing(int realScreenShowed) {
        this.mRealScreenShowed = realScreenShowed;
    }

}
