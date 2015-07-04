package org.ababup1192.hellothread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.TextView;

// <UIスレッドから受け取る型, タスクの途中で受け取る型, UIスレッドへ渡す型>
public class FactorialTask extends AsyncTask<Integer, Integer, Integer> implements DialogInterface.OnCancelListener {
    private ProgressDialog dialog;
    private Context context;
    private Activity activity;

    public FactorialTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    // タスクを始める前に実行されるメソッド。プログレスバーを表示。
    @Override
    protected void onPreExecute() {
        // プログレスバーの内容を記述し、表示。
        dialog = new ProgressDialog(context);
        dialog.setTitle("計算中...");
        dialog.setMessage("10の階乗の計算をしています。");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(this);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.show();
    }

    // 別スレッドの処理をするメソッド。UIスレッドから値を受け取ることができる。
    @Override
    protected Integer doInBackground(Integer... integers) {
        int result = 1;
        int target = integers[0];

        // 時間が掛かる計算
        for (int i = 1; i <= target; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 計算がキャンセルされたとき。
            if (isCancelled()) {
                break;
            }
            // 計算の進捗をプログレスバーに伝える。
            publishProgress((int) (100 * ((double) i / target)));
            result *= i;
        }

        // 計算結果を戻り値として返す。
        return result;
    }

    // 計算の進捗をプログレスバーに反映。
    @Override
    protected void onProgressUpdate(Integer... values) {
        dialog.setProgress(values[0]);
    }

    // 計算が終了したので、計算結果を表示し、プログレスバーを消す。
    protected void onPostExecute(Integer result) {
        TextView textView = (TextView) activity.findViewById(R.id.text_hello);
        textView.setText(result.toString());
        dialog.dismiss();
    }

    // 計算がキャンセルされたときは、エラー文を表示する。
    @Override
    public void onCancel(DialogInterface dialogInterface) {
        TextView textView = (TextView) activity.findViewById(R.id.text_hello);
        textView.setText("計算はキャンセルされました。");
        cancel(true);
    }
}
