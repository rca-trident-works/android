package com.example.practicegooglemaps1;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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

        // Add a marker in Sydney and move the camera
        loc = new LatLng(35.09059656089489, 136.87840551078992);
        /// マーカーオプションを設定（情報ウィンドウ）
        mMap.addMarker(new MarkerOptions().position(loc).title("名古屋港水族館"));
        /// 表示位置を地図に指定
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        /// 地図の倍率を指定
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
        // タップした時のリスナーをセット
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(@NonNull Marker marker) {
                /// タップしたマーカーを削除
                marker.remove();
                /// このメソッドでは戻り値がfalseの場合、
                /// 動作が似ているため同時に発生しがちなイベントである
                /// onClickなどの他のメソッドは実行しない。
                return false;
            }
        });

        // 長押しのリスナーをセット
        mMap.setOnMapLongClickListener(longpushLocation -> {
            LatLng newlocation = new LatLng(longpushLocation.latitude, longpushLocation.longitude);
            mMap.addMarker(new MarkerOptions().position(newlocation).title(""+longpushLocation.latitude+" :"+ longpushLocation.longitude));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlocation, 10));
        });
    }
}