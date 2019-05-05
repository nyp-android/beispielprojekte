package ch.nyp.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * MainActivity der Sensor Demo App
 */
public class MainActivity extends AppCompatActivity {

	private SensorManager mSensorManager;

	/**
	 * SensorEventListener, welcher aufgerufen wird, sobald sich beim Barometer-Sensor etwas
	 * ändert, d.h. ein anderer Luftdruck registriert wird. Der Listener wird nach einmaliger
	 * Ausführung unregistriert, damit nur ein Wert ausgegeben wird.
	 */
	private SensorEventListener mBarometerSensorEventListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] sensorValues = event.values;
			String airPressure = sensorValues[0] + " mbar";
			Toast toast = Toast.makeText(getApplicationContext(), airPressure, Toast.LENGTH_LONG);
			toast.show();
			mSensorManager.unregisterListener(this);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	/**
	 * Wird aufgerufen, sobald der Benutzer auf den Button "Show Air Pressure" drückt. Zeigt den
	 * aktuellen Luftdruck in einem Toast an.
	 */
	private View.OnClickListener mButtonBarometerOnclickListener = new View.OnClickListener() {
		@Override
		public void onClick(View button) {
			//Abfragen, ob ein Sensor zur Messung des Luftdrucks verfügbar ist und falls Ja,
			// einen SensorEventListener registrieren (für den SensorEventListener siehe
			// Instanzvariable mBarometerSensorEventListener
			Sensor airPressureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
			if (airPressureSensor != null) {
				mSensorManager.registerListener(mBarometerSensorEventListener, airPressureSensor,
						SensorManager.SENSOR_DELAY_NORMAL);
			}
		}
	};

	/**
	 * Wird aufgerufen, sobald das Activity erstellt wird. <br/>
	 * Hier wird das Activity initialisiert, d.h. das XML-File mit der Activity verbunden.
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

		TextView textViewAvailableSensors = (TextView) findViewById(R.id
				.main_textview_available_sensors);
		Button buttonBarometer = (Button) findViewById(R.id.main_button_barometer);
		buttonBarometer.setOnClickListener(mButtonBarometerOnclickListener);

		//Alle verfügbaren Sensoren des Geräts abfragen und deren Namen auf dem GUI anzeigen
		mSensorManager = (SensorManager) getApplicationContext().getSystemService(Context
				.SENSOR_SERVICE);
		List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

		String sensorString = "";
		for (Sensor deviceSensor : deviceSensors) {
			sensorString += deviceSensor.getName() + "\n";
		}

		textViewAvailableSensors.setText(sensorString);
	}
}
