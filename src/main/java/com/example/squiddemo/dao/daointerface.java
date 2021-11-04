package com.example.squiddemo.dao;

import javafx.collections.ObservableList;

public interface daointerface <E>{
    public int addData (E data);
    public int delData (E data);
    public int updateData (E data);
    public ObservableList<E> showData();

}
