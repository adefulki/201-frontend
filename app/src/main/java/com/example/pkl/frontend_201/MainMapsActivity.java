package com.example.pkl.frontend_201;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pkl.frontend_201.model.Dagangan;
import com.example.pkl.frontend_201.restful.GetImageMethod;
import com.example.pkl.frontend_201.restful.GetMethod;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.pkl.frontend_201.R.id.map;

/**
 * Created by AdeFulki on 5/27/2017.
 */

public class MainMapsActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        NavigationView.OnNavigationItemSelectedListener,
        ClusterManager.OnClusterClickListener<Dagangan>,
        ClusterManager.OnClusterInfoWindowClickListener<Dagangan>,
        ClusterManager.OnClusterItemClickListener<Dagangan>,
        ClusterManager.OnClusterItemInfoWindowClickListener<Dagangan>,
        LocationListener {

    private GoogleMap mMap;
    private ClusterManager<Dagangan> mClusterManager;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_search, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pengaturan) {
            return true;
        }else if (id == R.id.action_tentang) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

        mClusterManager = new ClusterManager<Dagangan>(this, mMap);
        mClusterManager.setRenderer(new DaganganRenderer());
        final CameraPosition[] mPreviousCameraPosition = {null};
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener(){
            @Override
            public void onCameraIdle(){

                CameraPosition position = mMap.getCameraPosition();
                if(mPreviousCameraPosition[0] == null || mPreviousCameraPosition[0].zoom !=position.zoom){
                    mPreviousCameraPosition[0] = mMap.getCameraPosition();
                    mClusterManager.cluster();
                }
            }
        });
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        addItems();
        mClusterManager.cluster();
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     * Draws profile photos inside markers (using IconGenerator).
     * When there are multiple people in the cluster, draw multiple photos (using MultiDrawable).
     */
    private class DaganganRenderer extends DefaultClusterRenderer<Dagangan> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mSingleImageView;
        private final ImageView mSingleStar;
        private final ImageView mSingleIcon;
        private final FrameLayout mSingleFrame;


        private final ImageView mClusterImageView;
        private final ImageView mClusterStar;
        private final TextView mClusterTextView;
        private final int mDimension;

        public DaganganRenderer() {
            super(getApplicationContext(), mMap, mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);
            mClusterTextView = (TextView) multiProfile.findViewById(R.id.text);
            mClusterStar = (ImageView) multiProfile.findViewById(R.id.star);

            View singleProfile = getLayoutInflater().inflate(R.layout.single_profile, null);
            mIconGenerator.setContentView(singleProfile);
            mSingleImageView = (ImageView) singleProfile.findViewById(R.id.image);
            mSingleStar = (ImageView) singleProfile.findViewById(R.id.star);
            mSingleFrame = (FrameLayout) singleProfile.findViewById(R.id.frame);
            mSingleIcon = (ImageView) singleProfile.findViewById(R.id.icon);

            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
        }

        @Override
        protected void onBeforeClusterItemRendered(Dagangan dagangan, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            GetImageMethod getImageMethod = new GetImageMethod();

            byte[] image = new byte[0];
            try{
                image = getImageMethod.execute("http://pkl-tracker.hol.es/assets/image/"+dagangan.getFoto_dagangan()+".jpg").get();
                if (image != null && image.length > 0){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    mSingleImageView.setImageBitmap(bitmap);
                }

                if (dagangan.getStatus_recommendation() == Boolean.FALSE)
                    mSingleStar.setVisibility(View.INVISIBLE);
                else mSingleStar.setVisibility(View.VISIBLE);

                if ( dagangan.getTipe_dagangan() == Boolean.FALSE){
                    mSingleIcon.setImageResource(R.drawable.icon_diam);
                    if (dagangan.getStatus_berjualan() == Boolean.TRUE)
                        mSingleFrame.setBackgroundResource(R.color.colorOnline);
                     else mSingleFrame.setBackgroundResource(R.color.colorOffline);
                }else{
                    mSingleIcon.setImageResource(R.drawable.icon_berjalan);
                    mSingleFrame.setBackgroundResource(R.color.colorOnline);
                }


            }catch (Exception e){
                e.printStackTrace();
            }

            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(dagangan.getNama_dagangan());
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Dagangan> cluster, MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;
            int counterRecomendation = 0;
            for (Dagangan p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;

                GetImageMethod getImageMethod = new GetImageMethod();

                byte[] image = new byte[0];
                try{
                    image = getImageMethod.execute("http://pkl-tracker.hol.es/assets/image/"+p.getFoto_dagangan()+".jpg").get();
                    if (image != null && image.length > 0){
                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        drawable.setBounds(0, 0, width, height);
                        profilePhotos.add(drawable);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (p.getStatus_recommendation() == Boolean.TRUE)
                    counterRecomendation++;

            }
            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
            multiDrawable.setBounds(0, 0, width, height);

            if (counterRecomendation > 0)
                mClusterStar.setVisibility(View.VISIBLE);
            mClusterImageView.setImageDrawable(multiDrawable);
            mClusterTextView.setText(String.valueOf(profilePhotos.size()));

            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<Dagangan> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String firstName = cluster.getItems().iterator().next().getNama_dagangan();
        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<Dagangan> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(Dagangan item) {
        // Does nothing, but you could go into the user's profile page, for example.
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Dagangan item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }

    private void addItems() {
        ArrayList<Dagangan> listDagangan = new ArrayList<>();
        listDagangan = getDaganganLocation();

        for (Dagangan dagangan: listDagangan) {
            if(dagangan.getTipe_dagangan() == Boolean.FALSE){
                if (dagangan.getStatus_berjualan() == Boolean.TRUE)
                    mClusterManager.addItem(dagangan);
                else{
                    mClusterManager.addItem(dagangan);
                }
            } else{
                if (dagangan.getStatus_berjualan() == Boolean.TRUE)
                    mClusterManager.addItem(dagangan);
            }

        }
    }

    public ArrayList<Dagangan> getDaganganLocation(){
        ArrayList<Dagangan> listDagangan = new ArrayList<>();

        GetMethod getMethod = (GetMethod) new GetMethod().execute(
                "http://pkl-tracker.hol.es/index.php/c_all/display_dagangan_location");
        String jsonData = null;
        JSONObject Jobject = null;

        try {
            jsonData = getMethod.get();
            Jobject = new JSONObject(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Getting JSON Array node
        assert Jobject != null;
        JSONArray arr = null;
        try {
            arr = Jobject.getJSONArray("result_dagangan_location");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < arr.length(); i++) {
            JSONObject c = null;
            Dagangan dagangan = new Dagangan();
            try {
                c = arr.getJSONObject(i);
                dagangan.setId_dagangan(c.getString("id_dagangan"));
                dagangan.setNama_dagangan(c.getString("nama_dagangan"));
                dagangan.setFoto_dagangan(c.getString("foto_dagangan"));
                dagangan.setLat_dagangan(c.getDouble("lat_dagangan"));
                dagangan.setLng_dagangan(c.getDouble("lng_dagangan"));
                dagangan.setMean_penilaian_dagangan(c.getDouble("mean_penilaian_dagangan"));
                dagangan.setCount_penilaian_dagangan(c.getInt("count_penilaian_dagangan"));
                dagangan.setStatus_recommendation(c.getBoolean("status_recommendation"));
                dagangan.setStatus_berjualan(c.getBoolean("status_berjualan"));
                dagangan.setTipe_dagangan(c.getBoolean("tipe_dagangan"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listDagangan.add(dagangan);
        }

        return listDagangan;
    }
}
