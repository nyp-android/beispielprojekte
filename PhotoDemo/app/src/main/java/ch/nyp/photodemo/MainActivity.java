package ch.nyp.photodemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Einstiegsactivity. <br/>
 * Beinhaltet einen Button, bei wessen Klick sich die Kamera-App öffnet. Mit der Kamera-App kann
 * dann ein Foto gemacht werden. Das Foto wird auf dem Smartphone gespeichert und in der App in
 * einer ImageView angezeigt.
 * <br/><br/>
 *
 * History:
 * 26.01.2018	1.0	Joel Holzer		Klasse erstellt
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	static final int REQUEST_IMAGE_CAPTURE = 1;

	private Uri mCurrentPhotoUri;
	private ImageView mImageView;

	/**
	 * Wird beim Starten des Activity ausgeführt.<br/>
	 *
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = findViewById(R.id.imageView_main_photo);

		Button button =findViewById(R.id.button_main_photo);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

					File imageFile = null;
					try {
						imageFile = createImageFile();
					} catch (IOException ex) {
						// Error occurred while creating the File
					}

					if (imageFile != null) {
						String providerPath = "ch.nyp.photodemo.provider";
						Context context = getApplicationContext();
						mCurrentPhotoUri = FileProvider.getUriForFile(
								context, providerPath, imageFile);

						takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);
						takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
						startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
					}
				}
			}
		});
	}

	/**
	 * Wird aufgerufen, sobald die Kamera-App geschlossen wurde und wieder diese App zum
	 * vorschein kommt. Die Kamera-App übergibt dieser App die URL des Fotos, welches dann in der
	 * App angezeigt werden soll.
	 *
	 * @param requestCode Dieser Code wird beim Start der Kamera-App übergeben. Anhand des
	 * Request-Codes kann dann festgestellt werden, dass das Resultat auch wieder von der
	 * Kamera-App kommt und nicht von einer anderen app.
	 * @param resultCode Resultat-Code. OK, NOK
	 * @param data Daten, welche von der Kamera-App zurückkommen.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			mImageView.setImageURI(mCurrentPhotoUri);
		}
	}

	/**
	 * Erstellt auf dem Dateisystem auf dem Smartphone eine leere Foto-Datei mit dem Datenamen
	 * "JPEG_yyyyMMdd_HHmmss_.jpg", wobei yyyyMMdd_HHmmss jeweils durch den aktuellen Timestamp
	 * ersetzt wird.
	 *
	 * @return Erstelltes File, jedoch noch ohne Foto drin.
	 * @throws IOException Bei der Erstellung des Files ist eine Exception aufgetreten.
	 */
	private File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
				imageFileName, 		// Dateiname ohne Endung
				".jpg",         // Dateiendung
				storageDir      // Verzeichnis, in welchem Datei gespeichert werden soll
		);

		return image;
	}
}
