package cat.valen.m08_eac3;


import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * @class on es gestiona l'encesa del flash de la camara
 * Es detecta primer si disposa de la funcio si no es genera una Excepció
 */
public class LlanternaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static Camera cam = null;// has to be static, otherwise onDestroy() destroys it
    ImageView llanterna;
    boolean activat = false;

    public LlanternaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LlanternaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LlanternaFragment newInstance(String param1, String param2) {
        LlanternaFragment fragment = new LlanternaFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //visorImatge.setOnClickListener(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }
    //https://www.codeproject.com/Articles/1112813/Android-Flash-Light-Application-Tutorial-Using-Cam
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_llanterna, container, false);
        super.onViewCreated(myFragmentView, savedInstanceState);

        llanterna = (ImageView) myFragmentView.findViewById(R.id.imageLlanterna);
        /**
         * Gestió de l'ences i apagada del flash fent click al imageView
         * */
        llanterna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (activat){
                    flashLightOff(v);
                } else {
                    flashLightOn(v);
                }

            }
        }) ;

        return myFragmentView;
        //return inflater.inflate( R.layout.fragment_llanterna, container, false );
    }

    public void flashLightOn(View view) {

        try {
            if (getActivity().getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode( Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                llanterna.setImageResource( android.R.drawable.btn_star_big_off );
                Toast.makeText(getActivity().getBaseContext(), "Flaix ences",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), "Aquest dispositiu no disposa de Llanterna",
                    Toast.LENGTH_SHORT).show();
        }
        activat = true;
    }

    public void flashLightOff(View view) {
        try {
            if (getActivity().getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
                llanterna.setImageResource( android.R.drawable.btn_star_big_on );
                Toast.makeText(getActivity().getBaseContext(), "Flaix Apagat",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), "Aquest dispositiu no disposa de Llanterna",
                    Toast.LENGTH_SHORT).show();
        }
        activat = false;
    }

}
