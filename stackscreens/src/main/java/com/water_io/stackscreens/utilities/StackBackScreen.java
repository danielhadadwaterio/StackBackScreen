package com.water_io.stackscreens.utilities;
import android.os.Bundle;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Stack;

public class StackBackScreen {

    private Stack<HashMap.Entry<Integer, Bundle>> mAllScreenHistory = new Stack<>();
    public final static int EMPTY_HISTORY_SCREEN = -1;
    private int mTempScreenBack = EMPTY_HISTORY_SCREEN;
    private  int mSizeOfStack;

    public StackBackScreen(final int firstScreenInit, Bundle bundleFirstScreenInit, int sizeOfStack) {
        this.mSizeOfStack = sizeOfStack;
        newScreenShowing(firstScreenInit, bundleFirstScreenInit);
    }

    public StackBackScreen(final int firstScreenInit, int sizeOfStack) {
        this.mSizeOfStack = sizeOfStack;
        newScreenShowing(firstScreenInit, null);
    }

    public void newScreenShowing(int screenID) {
        newScreenShowing(screenID, null);
    }

    public void newScreenShowing(int screenID, Bundle bundle) {
        if (screenID == mTempScreenBack) {
            return;
        }

        if (mAllScreenHistory.size() > mSizeOfStack) {
            mAllScreenHistory.remove(mAllScreenHistory.size() - 1);
        }

        mAllScreenHistory.push(new AbstractMap.SimpleEntry<>(screenID, bundle));
    }

    public HashMap.Entry<Integer, Bundle> backToPrevScreen() {
        if (mAllScreenHistory.isEmpty() || mAllScreenHistory.size() < 2)
            return new AbstractMap.SimpleEntry<>(EMPTY_HISTORY_SCREEN, null);
        HashMap.Entry<Integer, Bundle> entry = mAllScreenHistory.get(mAllScreenHistory.size() - 2);
        mTempScreenBack = entry.getKey();

        mAllScreenHistory.pop();
        return entry;
    }


    public int getCurrentScreen() {
        if (mAllScreenHistory.isEmpty())
            return EMPTY_HISTORY_SCREEN;
        return mAllScreenHistory.peek().getKey();
    }


}
