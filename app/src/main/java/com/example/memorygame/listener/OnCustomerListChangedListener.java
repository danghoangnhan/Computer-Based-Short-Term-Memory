package com.example.memorygame.listener;

import com.example.memorygame.Object.Customer;

import java.util.List;

public interface OnCustomerListChangedListener {
    void onNoteListChanged(List<Customer> customers);
}
