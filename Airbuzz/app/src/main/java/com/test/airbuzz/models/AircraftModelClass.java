package com.test.airbuzz.models;

public class AircraftModelClass {

    String msnNo;
    String maxAltReached;
    String fuelQuantityLeft;
    String fuelQuantityRight;
    String roomTemp;
    String atmPressure;
    String grossWeight;
    String arrivalAirport;
    String departureAirport;
    String arrivalDate;
    String departureDate;
    String economySeatsAvailable;
    String businessSeatsAvailable;

    public AircraftModelClass(){

    }

    public AircraftModelClass(String msnNo, String maxAltReached, String fuelQuantityLeft, String fuelQuantityRight, String roomTemp, String atmPressure, String grossWeight, String arrivalAirport, String departureAirport, String arrivalDate, String departureDate, String economySeatsAvailable, String businessSeatsAvailable) {
        this.msnNo = msnNo;
        this.maxAltReached = maxAltReached;
        this.fuelQuantityLeft = fuelQuantityLeft;
        this.fuelQuantityRight = fuelQuantityRight;
        this.roomTemp = roomTemp;
        this.atmPressure = atmPressure;
        this.grossWeight = grossWeight;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.economySeatsAvailable = economySeatsAvailable;
        this.businessSeatsAvailable = businessSeatsAvailable;
    }

    public String getMsnNo() {
        return msnNo;
    }

    public void setMsnNo(String msnNo) {
        this.msnNo = msnNo;
    }

    public String getMaxAltReached() {
        return maxAltReached;
    }

    public void setMaxAltReached(String maxAltReached) {
        this.maxAltReached = maxAltReached;
    }

    public String getFuelQuantityLeft() {
        return fuelQuantityLeft;
    }

    public void setFuelQuantityLeft(String fuelQuantityLeft) {
        this.fuelQuantityLeft = fuelQuantityLeft;
    }

    public String getFuelQuantityRight() {
        return fuelQuantityRight;
    }

    public void setFuelQuantityRight(String fuelQuantityRight) {
        this.fuelQuantityRight = fuelQuantityRight;
    }

    public String getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(String roomTemp) {
        this.roomTemp = roomTemp;
    }

    public String getAtmPressure() {
        return atmPressure;
    }

    public void setAtmPressure(String atmPressure) {
        this.atmPressure = atmPressure;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getEconomySeatsAvailable() {
        return economySeatsAvailable;
    }

    public void setEconomySeatsAvailable(String economySeatsAvailable) {
        this.economySeatsAvailable = economySeatsAvailable;
    }

    public String getBusinessSeatsAvailable() {
        return businessSeatsAvailable;
    }

    public void setBusinessSeatsAvailable(String businessSeatsAvailable) {
        this.businessSeatsAvailable = businessSeatsAvailable;
    }
}
