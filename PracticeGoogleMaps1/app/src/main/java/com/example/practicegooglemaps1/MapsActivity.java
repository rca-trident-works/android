package com.example.practicegooglemaps1;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.practicegooglemaps1.databinding.ActivityMapsBinding;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(v -> {
            mMap.clear();
        });

        Button button = findViewById(R.id.easterEggButton);
        button.setOnClickListener(v -> {
            for (int j = 0; j <= 5; j++) {
                // generate random location
                double lat = Math.random() * 180 - 90;
                double lng = Math.random() * 360 - 180;
                LatLng newlocation = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(newlocation).title("Start"));
                for (int i = 0; i < 20; i++) {
                    lng++;
                    LatLng newlocation2 = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(newlocation2).title("Point1-" + i));
                }

                for (int i = 0; i < 5; i++) {
                    lng--;
                    lat++;
                    LatLng newlocation3 = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(newlocation3).title("Point2-" + i));
                }

                lng = lng + 5;
                lat = lat - 5;

                for (int i = 0; i < 5; i++) {
                    lng--;
                    lat--;
                    LatLng newlocation4 = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(newlocation4).title("Point3-" + i));
                }
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // マップの種類を設定
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        loc = new LatLng(35.09059656089489, 136.87840551078992);
        /// マーカーオプションを設定（情報ウィンドウ）
        mMap.addMarker(new MarkerOptions().position(loc).title("名古屋港水族館"));
        /// 表示位置を地図に指定
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        /// 地図の倍率を指定
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
        // タップした時のリスナーをセット
        mMap.setOnMarkerClickListener(marker -> {
            /// タップしたマーカーを削除
            marker.remove();
            /// このメソッドでは戻り値がfalseの場合、
            /// 動作が似ているため同時に発生しがちなイベントである
            /// onClickなどの他のメソッドは実行しない。
            return false;
        });

        mMap.setOnMapClickListener(tapLocation -> {
            mMap.addMarker(new MarkerOptions()
                    .position(tapLocation)
                    .title(String.format(Locale.JAPAN, "%.4f, %.4f", tapLocation.latitude, tapLocation.longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );
        });

        // 長押しのリスナーをセット
        mMap.setOnMapLongClickListener(longpushLocation -> {
            LatLng newlocation = new LatLng(longpushLocation.latitude, longpushLocation.longitude);
            mMap.addCircle(
                    new CircleOptions()
                            .center(newlocation)
                            .radius(1000)
                            .strokeColor(Color.RED)
                            .fillColor(Color.argb(64, 255, 0, 0))
                            .zIndex(1.0f)
            );
        });
    }
}