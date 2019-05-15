package dev.dle.apicallexample;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import okhttp3.OkHttpClient;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link MainLaunchFragment.OnFragmentInteractionListener} interface to handle interaction events.
 * Use the {@link MainLaunchFragment#newInstance} factory method to create an instance of this
 * fragment.
 */
public class MainLaunchFragment extends Fragment {

  static final int REQUEST_IMAGE_CAPTURE = 1;
  static final int REQUEST_TAKE_PHOTO = 1;
  static ClarifaiClient clarifaiClient;
  @NonNull
  static MainLaunchFragment mainLaunchFragment;
  static List<ClarifaiOutput<Concept>> predictions;
  @BindView(R.id.progressBar)
  ProgressBar progressBar;
  @BindView(R.id.button_Classify)
  Button button_Classify;
  @BindView(R.id.button_OpenCamera)
  Button button_OpenCamera;
  @BindView(R.id.textView_MainHeader)
  TextView textView_MainHeader;
  @BindView(R.id.textView_SubHeader)
  TextView textView_SubHeader;
  @BindView(R.id.imageView)
  ImageView imageView;
  String mCurrentPhotoPath;
  private File photoFile;
  private OnFragmentInteractionListener mListener;

  public MainLaunchFragment() {
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @return A new instance of fragment MainLaunchFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MainLaunchFragment newInstance() {
    mainLaunchFragment = new MainLaunchFragment();
    return mainLaunchFragment;
  }

  public static synchronized ClarifaiClient getClarifaiClient() {
    return clarifaiClient;
  }

  public static synchronized List<ClarifaiOutput<Concept>> getPredictions() {
    return predictions;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_launch, container, false);
    ButterKnife.bind(this, view);

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    textView_MainHeader
        .setTextColor(getResources().getColor(R.color.primaryFontOnPrimaryBackground));
    textView_SubHeader
        .setTextColor(getResources().getColor(R.color.primaryFontOnPrimaryBackground));
    progressBar.setVisibility(View.INVISIBLE);
    button_Classify.setEnabled(true);
    button_OpenCamera.setEnabled(true);
    // Inflate the layout for this fragment
    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @RequiresApi(api = VERSION_CODES.M)
  @OnClick(R.id.button_OpenCamera)
  public void onClickButtonOpenCamera() {

    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      // Create the File where the photo should go
      photoFile = null;
      try {
        photoFile = createImageFile();
      } catch (IOException ex) {
        // Error occurred while creating the File
        ex.printStackTrace();
      }
      // Continue only if the File was successfully created
      if (photoFile != null) {
        Uri photoURI =
            FileProvider
                .getUriForFile(getContext(), "dev.dle.apicallexample.fileprovider", photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Bitmap imageBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
      imageView.setVisibility(View.VISIBLE);
      imageView.setImageBitmap(imageBitmap);

      textView_MainHeader.setVisibility(View.INVISIBLE);
      textView_SubHeader.setVisibility(View.INVISIBLE);
      button_Classify.setVisibility(View.VISIBLE);
    }
  }

  @RequiresApi(api = VERSION_CODES.M)
  @SuppressLint("StaticFieldLeak")
  @OnClick(R.id.button_Classify)
  public void onClickButtonClassify() {
    if (photoFile == null) {
      Toast.makeText(getContext(), "You have to take a picture first!", Toast.LENGTH_SHORT).show();
    } else {
      progressBar.setVisibility(View.VISIBLE);
      button_Classify.setEnabled(false);
      button_OpenCamera.setEnabled(false);

      new AsyncTask<Void, Void, ClarifaiResponse<List<ClarifaiOutput<Concept>>>>() {
        @Override
        protected ClarifaiResponse<List<ClarifaiOutput<Concept>>> doInBackground(Void... params) {
          // The default Clarifai model that identifies concepts in images
          // TODO INSERT API KEY
          clarifaiClient =
              new ClarifaiBuilder("INSERT_API_KEY").client(new OkHttpClient()).buildSync();
          final ConceptModel generalModel = clarifaiClient.getDefaultModels().generalModel();

          // Use this model to predict, with the image that the user just selected as the input
          return generalModel
              .predict()
              .withInputs(ClarifaiInput.forImage(ClarifaiImage.of(photoFile.getAbsoluteFile())))
              .executeSync();
        }

        @RequiresApi(api = VERSION_CODES.M)
        @Override
        protected void onPostExecute(ClarifaiResponse<List<ClarifaiOutput<Concept>>> response) {
          if (!response.isSuccessful()) {
            Toast
                .makeText(getContext(), getResources().getText(R.string.error_while_contacting_api),
                    Toast.LENGTH_SHORT)
                .show();

            return;
          }
          predictions = response.get();

          if (predictions.isEmpty()) {
            showErrorSnackbar(R.string.no_results_from_api);
            return;
          }
          FragmentUtils.loadFragment(getActivity(), ResultsFragment.newInstance());

        }

        @RequiresApi(api = VERSION_CODES.M)
        private void showErrorSnackbar(@StringRes int errorString) {
          Toast.makeText(getContext(), getResources().getText(errorString), Toast.LENGTH_SHORT)
              .show();
        }
      }.execute();
    }
  }

  @RequiresApi(api = VERSION_CODES.M)
  private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */);

    // Save a file: path for use with ACTION_VIEW intents
    mCurrentPhotoPath = image.getAbsolutePath();
    return image;
  }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an
   * interaction in this fragment to be communicated to the activity and potentially other fragments
   * contained in that activity. <p> See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html" >Communicating with
   * Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }


}
