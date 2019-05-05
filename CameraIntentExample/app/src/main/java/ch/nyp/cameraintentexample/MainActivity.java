package ch.nyp.cameraintentexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	private static final int REQUEST_IMAGE_CAPTURE = 1;

	private ImageView mPhotoImageView;

	private View.OnClickListener mTakePhotoOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View sendButton) {
			//Camera-App starten mit implizitem Intent
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button takePhotoButton = findViewById(R.id.main_button_takephoto);
		takePhotoButton.setOnClickListener(mTakePhotoOnClickListener);

		mPhotoImageView = findViewById(R.id.main_imageview_photo);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			mPhotoImageView.setImageBitmap(imageBitmap);
		}
	}
}
