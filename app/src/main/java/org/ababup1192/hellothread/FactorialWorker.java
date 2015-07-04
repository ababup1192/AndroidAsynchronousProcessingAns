package org.ababup1192.hellothread;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

// ジェネリックスは、結果型であるIntegerを指定。
public class FactorialWorker extends AsyncTaskLoader<Integer> {
    private int target;

    public FactorialWorker(Context context, int target) {
        super(context);
        this.target = target;
    }

    // 時間が掛かる計算を別Threadで実行。
    @Override
    public Integer loadInBackground() {
        int result = 1;
        for (int i = 1; i <= target; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result *= i;
        }

        return result;
    }

}
