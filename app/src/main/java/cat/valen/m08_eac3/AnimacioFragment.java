package cat.valen.m08_eac3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * @class on es gestiona l'animacio d'una imatge fent servir
 * import android.view.animation.AnimationUtils;
*
*/
public class AnimacioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;
    View myFragmentView;

    public AnimacioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimacioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimacioFragment newInstance(String param1, String param2) {
        AnimacioFragment fragment = new AnimacioFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_animacio, container, false);
        super.onViewCreated(myFragmentView, savedInstanceState);

        final ImageView animacioView = (ImageView) myFragmentView.findViewById(R.id.imageAnimacio);

        animacioView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation animacioAndroid = AnimationUtils.loadAnimation(getContext(), R.anim.animacio);
                animacioView.startAnimation(animacioAndroid);

            }
        }) ;

        return myFragmentView;
        //return inflater.inflate( R.layout.fragment_animacio, container, false );
    }


}
