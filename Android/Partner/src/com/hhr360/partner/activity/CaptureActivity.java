package com.hhr360.partner.activity;

import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.hhr360.partner.R;
import com.hhr360.partner.utils.RegistUtil;
import com.hhr360.partner.zxing.CameraManager;
import com.hhr360.partner.zxing.CaptureActivityHandler;
import com.hhr360.partner.zxing.ViewfinderView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;

	private ImageView mImageView;
	private TextView mTextView;

	public static final int REQUEST = 2048;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.qr_main);

		CameraManager.init(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		mImageView = (ImageView) findViewById(R.id.back_btn);
		mTextView = (TextView) findViewById(R.id.back_tv);

		BackOnClickListener backListen = new BackOnClickListener();
		mImageView.setOnClickListener(backListen);
		mTextView.setOnClickListener(backListen);
		hasSurface = false;
	}

	class BackOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			finish();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	public void handleDecode(Result obj, Bitmap barcode) {
		viewfinderView.drawResultBitmap(barcode);
		String result = obj.getText().toString();
		// 扫码结果不为空，且符合邀请码规则
		if (!TextUtils.isEmpty(result) && RegistUtil.isInviteCodeLegal(result)) {
			Intent intent = new Intent();
			intent.putExtra("qr_result", result);
			setResult(Activity.RESULT_OK, intent);
		} else {
			CaptureActivity.this.setResult(Activity.RESULT_CANCELED);
			Toast.makeText(this,
					// result,
					getResources().getString(R.string.register_qr_failed),
					Toast.LENGTH_SHORT).show();
		}
		finish();
	}
}