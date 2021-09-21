package com.developers.quickcollect;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Developer on 4/12/2015.
 */
public class productos implements Parcelable {

    private String producto;
    private int costo;
    private int cantidad;


    public productos(int costo, String producto,int cantidad) {
        this.costo = costo;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(producto);
        dest.writeInt(costo);
        dest.writeInt(cantidad);

    }

    private void readFromParcel(Parcel in) {
        producto = in.readString();
        costo = in.readInt();
        cantidad = in.readInt();
    }

    public productos() {
    }

    public productos(Parcel in) {
        readFromParcel(in);
    }

    public static final Parcelable.Creator<productos> CREATOR = new Parcelable.Creator<productos>() {
        public productos createFromParcel(Parcel in) {
            return new productos(in);
        }

        @Override
        public productos[] newArray(int size) {
            return new productos[size];
        }
    };

    public String toString() {
        return "Producto: "+this.getProducto()+"";
    }

}
