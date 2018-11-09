package cat.valen.m08_eac3;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * @class per reproduir musica d'un fitxer mp3 fent servir la classe @Link android.media.MediaPlayer
 */
public class MusicaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View myFragmentView;
    private boolean activat = false;
    //private OnFragmentInteractionListener mListener;

    public MusicaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicaFragment newInstance(String param1, String param2) {
        MusicaFragment fragment = new MusicaFragment();
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
        myFragmentView = inflater.inflate(R.layout.fragment_musica, container, false);
        super.onViewCreated(myFragmentView, savedInstanceState);

        final ImageView musicaView = (ImageView) myFragmentView.findViewById(R.id.imagePlay);

        musicaView.setOnClickListener(new View.OnClickListener() {
            MediaPlayer so = MediaPlayer.create(getContext(), R.raw.enjoy);

            @Override
            public void onClick(View v) {
                 if (activat){
                     so.pause();
                     activat = false;
                     musicaView.setImageResource(android.R.drawable.ic_media_play);
                     Toast.makeText(getActivity().getBaseContext(), "En pausa",
                             Toast.LENGTH_SHORT).show();
                 } // Activem la repoduccio de so
                 else{
                     so.start();
                     activat = true;
                     musicaView.setImageResource(android.R.drawable.ic_media_pause);
                     Toast.makeText(getActivity().getBaseContext(), "Reproduint",
                             Toast.LENGTH_SHORT).show();
                 }
            }
        }) ;

        return myFragmentView;
        // Inflate the layout for this fragment
        //return inflater.inflate( R.layout.fragment_musica, container, false );
    }


}
