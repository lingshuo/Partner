package cn.edu.bjtu.partner.utils;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SecurityCodeUtil {

	private static final char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static SecurityCodeUtil bpUtil;

	public static SecurityCodeUtil getInstance() {
		if (bpUtil == null)
			bpUtil = new SecurityCodeUtil();
		return bpUtil;
	}

	private static final int DEFAULT_CODE_LENGTH = 4;
	private static final int DEFAULT_FONT_SIZE = 20;
	// private static final int DEFAULT_LINE_NUMBER = 3;
	private static final int BASE_PADDING_LEFT = 5, RANGE_PADDING_LEFT = 10,
			BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 10;
	private static final int DEFAULT_WIDTH = 60, DEFAULT_HEIGHT = 30;

	private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

	private int base_padding_left = BASE_PADDING_LEFT,
			range_padding_left = RANGE_PADDING_LEFT,
			base_padding_top = BASE_PADDING_TOP,
			range_padding_top = RANGE_PADDING_TOP;

	private int codeLength = DEFAULT_CODE_LENGTH,
	// line_number = DEFAULT_LINE_NUMBER,
			font_size = DEFAULT_FONT_SIZE;

	private String code;
	private int padding_left, padding_top;
	private Random random = new Random();

	public Bitmap createBitmap() {
		padding_left = 0;

		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas c = new Canvas(bp);

		code = createCode();

		c.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setTextSize(font_size);

		for (int i = 0; i < code.length(); i++) {
			randomTextStyle(paint);
			randomPadding();
			c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
		}

		// 此处为在验证码上画线
		// for (int i = 0; i < line_number; i++) {
		// drawLine(c, paint);
		// }

		c.save(Canvas.ALL_SAVE_FLAG);
		c.restore();
		return bp;
	}

	public String getCode() {
		return code;
	}

	private String createCode() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < codeLength; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	// private void drawLine(Canvas canvas, Paint paint) {
	// int color = randomColor();
	// int startX = random.nextInt(width);
	// int startY = random.nextInt(height);
	// int stopX = random.nextInt(width);
	// int stopY = random.nextInt(height);
	// paint.setStrokeWidth(1);
	// paint.setColor(color);
	// canvas.drawLine(startX, startY, stopX, stopY, paint);
	// }

	private int randomColor() {
		return randomColor(1);
	}

	private int randomColor(int rate) {
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}

	private void randomTextStyle(Paint paint) {
		int color = randomColor();
		paint.setColor(color);
		float skewX = random.nextInt(11) / 10;
		skewX = random.nextBoolean() ? skewX : -skewX;
		paint.setTextSkewX(skewX);
	}

	private void randomPadding() {
		padding_left += base_padding_left + random.nextInt(range_padding_left);
		padding_top = base_padding_top + random.nextInt(range_padding_top);
	}

}
