package ch.nyp.imagegalleryexample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hauptactivity der App. Wird beim App-Start angezeigt. <br/>
 * Dieses Activity beinhaltet eine ImageView. Klickt der User auf die ImageView öffnet sich der
 * Medienmanager und der User kann ein Bild auswählen, welches in der ImageView angezeigt werden
 * soll. Zudem wird das ausgewählte Bild kopiert und im Pictures-Verzeichnis auf dem Handy abgelegt.
 * Auch wird der Pfad der "neuen" (durch Kopie erstellten) Datei in den SharedPreferences
 * abgelegt. <br/><br/>
 *
 * Beim nächsten Start der App wird dann das zuletzt ausgewählte Bild aus den SharedPreferences
 * geladen. Beim ersten App-Start wird das App-Icon in die ImageView geladen, da noch nie ein
 * Bild ausgewählt wurde und somit in den SharedPreferences kein Eintrag vorhanden ist.
 *
 * History:
 * <PRE>
 * 1.0	17.05.2018	Joel Holzer		Klasse erstellt
 * </PRE>
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	public static final int PICK_IMAGE = 1;

	private ImageView mImageView;

	/**
	 * Durch Klick auf die ImageView erscheint die Gallery zum Öffnen eines Bilds.
	 */
	private View.OnClickListener selectImageClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent pickPhoto = new Intent(Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(pickPhoto, PICK_IMAGE);
		}
	};

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Liest aus den SharedPreferences den Pfad des zuletzt geöffneten Bild und zeigt dieses in
	 * einer ImageView an. Falls kein Pfad vorhanden, wird das App-Icon in der ImageView
	 * angezeigt.
	 *
	 * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
	 * 							 being shut down then this Bundle contains the data it most
	 * 							 recently supplied in onSaveInstanceState(Bundle). Note: Otherwise
	 * 							 it is null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = findViewById(R.id.main_imageview_image);

		//Beim Starten der App das zuletzt geöffnete Foto laden (Pfad aus SharedPreferences
		// laden) und dann in ImageView anzeigen
		SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
				"image_path_preferences", Context.MODE_PRIVATE);
		String imagePath = sharedPref.getString("image_path", null);
		if (imagePath != null) {
			Uri imageUri = Uri.parse(imagePath);
			mImageView.setImageURI(imageUri);
		}

		//OnClickListener hinzufügen
		mImageView.setOnClickListener(selectImageClickListener);
	}

	/**
	 * Wird aufgerufen, sobald in der Gallery ein Bild ausgewählt wurde. Das ausgewählte Bild
	 * wird kopiert, der Dateiname des neu vorhandenen Bildes in den SharedPreferences
	 * gespeichert und das Bild in der ImageView angezeigt.
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
			Uri selectedImageUri = data.getData();

			//Ausgewähltes Foto kopieren
			Uri imageUri = copyImage(selectedImageUri);
			String imagePath = imageUri.getPath();

			//Pfad des "neuen" Fotos in den Shared Preferences ablegen
			SharedPreferences sharedPreferences = getApplicationContext()
					.getSharedPreferences("image_path_preferences", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("image_path", imagePath);
			editor.commit();

			//"Neues" Foto in der ImageView anzeigen
			mImageView.setImageURI(Uri.parse(imagePath));
		}
	}

	/**
	 * Kopiert das Bild der übergebenen URI und gibt dann die URI des neu erstellten Bildes zurück.
	 * Das Bild wird im Picture-Verzeichnis abgelegt.
	 *
	 * @param imageToCopy URI des zu kopierenden Bildes.
	 * @return URI des neu erstellten Bildes.
	 * @throws IOException Beim Lesen des gewählten Bildes oder Schreiben des neuen Bildes ist
	 * eine Exception aufgetreten.
	 */
	private Uri copyImage(Uri imageToCopy) {

		//Dateinamen des "neuen" (kopierten) Bildes definieren.
		//Foto_App_yyyyMMdd_HHmmss.jpg
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "Foto_App_" + timeStamp + ".jpg";

		//"Neues" Bild im öffentlichen Bild-Verzeichnis ablegen
		File path = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES);
		File image = new File(path, imageFileName);

		//Abfragen ob die Rechte für das Schreiben einer Datei in den externen Speicher gesetzt
		// sind. Falls diese nicht gesetzt sind, kann hier gesetzt werden.
		if (Build.VERSION.SDK_INT >= 23) {
			int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission
					.WRITE_EXTERNAL_STORAGE);
			if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
						.WRITE_EXTERNAL_STORAGE}, 1);
			}
		}

		//Ausgewähltes Bild lesen und in "neue" Datei schreiben (d.h. Bild kopieren)
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = getContentResolver().openInputStream(imageToCopy);
			outputStream = new FileOutputStream(image);
			copyStream(inputStream, outputStream);
		} catch (IOException exception) {
			Toast.makeText(getApplicationContext(), getText(R.string.main_error_copyimage), Toast
					.LENGTH_LONG);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				//Hie kann nichts gemacht werden ausser Loggen.
			}
		}
		return Uri.fromFile(image);
	}

	/**
	 * Kopiert den übergebenen InputStream in den übergebenen OutputStream, d.h. kopiert den
	 * Inhalt einer Datei in eine andere Datei.
	 *
	 * @param input InputStream der zu kopierenden Datei.
	 * @param output OutputStream der neuen Datei.
	 * @throws IOException Beim Kopieren (Lesen oder Schreiben von Stream) trat eine Exception auf.
	 */
	private void copyStream(InputStream input, OutputStream output)
			throws IOException {

		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}
}
