package cat.valen.m08_eac3;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @class MainAcitvy on s'inicalitza el primer fragment Llanterna
 * Es gestionen totes les crides als botons de la barra de menus
 */
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        //ft.replace(R.id.placeholder, new LlanternaFragment());
        ft.add(R.id.placeholder, new LlanternaFragment());
        //ft.add(R.id.placeholder, new CapturaFragment());
        // Complete the changes added above
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_llanterna) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.placeholder, new LlanternaFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }

        if (id == R.id.action_captura) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new LlanternaFragment());
            ft.replace(R.id.placeholder, new CapturaFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }

        if (id == R.id.action_fotograma) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new LlanternaFragment());
            ft.replace(R.id.placeholder, new FotogramaFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }
        if (id == R.id.action_animacio) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new LlanternaFragment());
            ft.replace(R.id.placeholder, new AnimacioFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }

        if (id == R.id.action_musica) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new LlanternaFragment());
            ft.replace(R.id.placeholder, new MusicaFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }

        if (id == R.id.action_video) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new LlanternaFragment());
            ft.replace(R.id.placeholder, new VideoFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }
        if (id == R.id.action_gps) {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            //ft.replace(R.id.placeholder, new MapsActivity());
            ft.replace(R.id.placeholder, new gpsFragment());
            // Complete the changes added above
            ft.commit();

            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    /*@Override
    public void onFragmentInteraction(Uri uri) {

    }*/
}
