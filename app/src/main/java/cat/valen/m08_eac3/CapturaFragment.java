package cat.valen.m08_eac3;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CapturaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CapturaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CapturaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    // Número que identifica l'activitat de l'aplicació de fotos
    private static final int APP_CAMERA = 0;

    // Identificador de la imatge que crearà l'aplicació de fotos
    private Uri identificadorImatge;

    View myFragmentView;

    public CapturaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CapturaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CapturaFragment newInstance(String param1, String param2) {
        CapturaFragment fragment = new CapturaFragment();
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
        myFragmentView = inflater.inflate(R.layout.fragment_captura, container, false);
        super.onViewCreated(myFragmentView, savedInstanceState);

        final ImageView fotoView = (ImageView) myFragmentView.findViewById(R.id.imageFoto);

        fotoView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fesFoto(v);

            }
        }) ;

        return myFragmentView;
        //return inflater.inflate( R.layout.fragment_captura, container, false );
    }

    public void fesFoto(View view) {
    // Es crea l'intent per l'aplicació de fotos
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    // Es crea un nou fitxer a l'emmagatzematge extern i se li passa a l'intent
        File foto = new File(Environment.getExternalStorageDirectory(), "Foto.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
    // Es desa l'identificador de la imatge per recuperar-la quan estigui feta
        identificadorImatge = Uri.fromFile(foto);
    // S'engega l'activitat
        startActivityForResult(intent, APP_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Primer cridem al mètode d'Activity perquè faci la seva tasca
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case APP_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    /* El ContentResolver dóna accés als continguts
                      (la imatge emmagatzemada en aquest cas)*/
                    ContentResolver contRes = getContentResolver();
                    // Cal indicar que el contingut del fitxer ha canviat
                    contRes.notifyChange(identificadorImatge, null);
                    /* Accedeix a l'ImageView i hi carrega la foto que ha fet la
                      càmera */
                    ImageView imageView = (ImageView) myFragmentView.findViewById(R.id.imageFoto);
                    Bitmap bitmap;
                    /* Com que la càrrega de la imatge pot fallar, cal tractar
                      les possibles excepcions*/
                    try {

                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(contRes, identificadorImatge);

                        /* Reduïm la imatge per no tenir problemes de visualització.
                          Calculem l'alçada per mantenir la proporció amb una amplada de 1080 píxels*/
                        int alt = (int) (bitmap.getHeight() * 1080 / bitmap.getWidth());
                        Bitmap reduit = Bitmap.createScaledBitmap(bitmap, 1080, alt, true);

                        imageView.setImageBitmap(reduit);

                    } catch (Exception e) {
                        Toast.makeText(this, "No es pot carregar la imatge" + identificadorImatge.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
