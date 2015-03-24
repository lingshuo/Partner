package cn.edu.bjtu.partner.activity;

import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.partner.R;
import cn.edu.bjtu.partner.zxing.CameraManager;
import cn.edu.bjtu.partner.zxing.CaptureActivityHandler;
import cn.edu.bjtu.partner.zxing.ViewfinderView;

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

		// if (NetworkStateUtil.isAvaliable()) {
		// ThunderStone stone = new ThunderStone(obj.getText(),
		// new IBindKtv() {
		// @Override
		// public void onSuccess() {
		// hideProgress();
		// CaptureActivity.this.setResult(Activity.RESULT_OK);
		// CaptureActivity.this.finish();
		// ToastUtils.showToast(CaptureActivity.this, "绑定成功");
		// ThunderStone.isBind = true;
		// }
		//
		// @Override
		// public void onFailed(int failedCode) {
		// hideProgress();
		// CaptureActivity.this
		// .setResult(Activity.RESULT_CANCELED);
		// CaptureActivity.this.finish();
		// ToastUtils.showToast(CaptureActivity.this, "连接失败");
		// ThunderStone.isBind = false;
		// }
		// });
		// stone.bind();
		// } else {
		// ToastUtils.showToast(CaptureActivity.this, "目前无网络连接，不能绑定KTV");
		// CaptureActivity.this.finish();
		// }
		// }
	}
}