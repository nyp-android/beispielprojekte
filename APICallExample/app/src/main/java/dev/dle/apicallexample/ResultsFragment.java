package dev.dle.apicallexample;

import static dev.dle.apicallexample.MainLaunchFragment.getPredictions;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import java.util.List;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link
 * ResultsFragment.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link
 * ResultsFragment#newInstance} factory method to create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  @BindView(R.id.resultsList)
  RecyclerView resultsList;
  @BindView(R.id.button_GoBack)
  Button button_GoBack;
  private OnFragmentInteractionListener mListener;
  private final RecognizeConceptsAdapter adapter = new RecognizeConceptsAdapter();

  public ResultsFragment() {}

  /**
   * Use this factory method to create a new instance of this fragment using the provided parameters.
   *

   * @return A new instance of fragment ResultsFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ResultsFragment newInstance() {
    return new ResultsFragment();
  }

  @RequiresApi(api = VERSION_CODES.M)
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);


    }
  }

  @RequiresApi(api = VERSION_CODES.M)
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_results, container, false);
    ButterKnife.bind(this, view);

    resultsList.setLayoutManager(new LinearLayoutManager(getContext()));
    resultsList.setAdapter(adapter);

    List<ClarifaiOutput<Concept>> predictions = MainLaunchFragment.getPredictions();

    adapter.setData(predictions.get(0).data());
    return view;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an interaction in this
   * fragment to be communicated to the activity and potentially other fragments contained in that activity. <p> See the
   * Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }

  @OnClick(R.id.button_GoBack)
  public void onClickGoBack() {
    FragmentUtils.loadFragment(getActivity(), MainLaunchFragment.newInstance());
  }
}
