package com.example.expensestracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo (name = "kind")
    private String kind;
    @ColumnInfo (name = "descrip")
    private String discrip;
    @ColumnInfo (name = "amount")
    private double amount;
    @ColumnInfo (name = "date")
    private java.util.Date date;

    public Task() {

    }

    public Task(int id, String kind, String discrip, double amount, Date date) {
        this.id = id;
        this.kind = kind;
        this.discrip = discrip;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDiscrip() {
        return discrip;
    }

    public void setDiscrip(String discrip) {
        this.discrip = discrip;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }
}
