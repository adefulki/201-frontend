package com.example.pkl.frontend_201.model;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pkl.frontend_201.MainMapsActivity;
import com.example.pkl.frontend_201.restful.GetImageMethod;
import com.example.pkl.frontend_201.restful.GetMethod;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by AdeFulki on 5/29/2017.
 */

public class Dagangan implements ClusterItem {
    private String id_dagangan;
    private String nama_dagangan;
    private String foto_dagangan;
    private Double lat_dagangan;
    private Double lng_dagangan;
    private Double mean_penilaian_dagangan;
    private Integer count_penilaian_dagangan;
    private Boolean status_recommendation;
    private Boolean status_berjualan;
    private Boolean tipe_dagangan;

    public String getId_dagangan() {
        return id_dagangan;
    }

    public void setId_dagangan(String id_dagangan) {
        this.id_dagangan = id_dagangan;
    }

    public String getNama_dagangan() {
        return nama_dagangan;
    }

    public void setNama_dagangan(String nama_dagangan) {
        this.nama_dagangan = nama_dagangan;
    }

    public String getFoto_dagangan() {
        return foto_dagangan;
    }

    public void setFoto_dagangan(String foto_dagangan) {
        this.foto_dagangan = foto_dagangan;
    }

    public Double getLat_dagangan() {
        return lat_dagangan;
    }

    public void setLat_dagangan(Double lat_dagangan) {
        this.lat_dagangan = lat_dagangan;
    }

    public Double getLng_dagangan() {
        return lng_dagangan;
    }

    public void setLng_dagangan(Double lng_dagangan) {
        this.lng_dagangan = lng_dagangan;
    }

    public Double getMean_penilaian_dagangan() {
        return mean_penilaian_dagangan;
    }

    public void setMean_penilaian_dagangan(Double mean_penilaian_dagangan) {
        this.mean_penilaian_dagangan = mean_penilaian_dagangan;
    }

    public Integer getCount_penilaian_dagangan() {
        return count_penilaian_dagangan;
    }

    public void setCount_penilaian_dagangan(Integer count_penilaian_dagangan) {
        this.count_penilaian_dagangan = count_penilaian_dagangan;
    }

    public Boolean getStatus_recommendation() {
        return status_recommendation;
    }

    public void setStatus_recommendation(Boolean status_recommendation) {
        this.status_recommendation = status_recommendation;
    }

    public Boolean getStatus_berjualan() {
        return status_berjualan;
    }

    public void setStatus_berjualan(Boolean status_berjualan) {
        this.status_berjualan = status_berjualan;
    }

    public Boolean getTipe_dagangan() {
        return tipe_dagangan;
    }

    public void setTipe_dagangan(Boolean tipe_dagangan) {
        this.tipe_dagangan = tipe_dagangan;
    }

    public LatLng getPosition() {
        return new LatLng(lat_dagangan,lng_dagangan);
    }



}
