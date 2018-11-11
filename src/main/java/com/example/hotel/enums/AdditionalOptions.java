package com.example.hotel.enums;



public enum AdditionalOptions {
    CleaningRoom(2), Safe(2), Telephone(4), Minibar(5), DryCleaning(3);

    double price;

        AdditionalOptions(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


}
