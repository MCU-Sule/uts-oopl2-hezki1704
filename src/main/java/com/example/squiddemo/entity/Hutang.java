/**
 * @auhtor 1972004 - Yehezkiel Christian
 */

package com.example.squiddemo.entity;

public class Hutang {
    private int id;
    private String pemberiHutang;
    private double jumlah;
    private Player player;

    public Hutang(int id, String pemberiHutang, double jumlah, Player player) {
        this.id = id;
        this.pemberiHutang = pemberiHutang;
        this.jumlah = jumlah;
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPemberiHutang() {
        return pemberiHutang;
    }

    public void setPemberiHutang(String pemberiHutang) {
        this.pemberiHutang = pemberiHutang;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
