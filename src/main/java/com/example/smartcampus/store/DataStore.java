package com.example.smartcampus.store;

import com.example.smartcampus.model.Room;
import com.example.smartcampus.model.Sensor;
import com.example.smartcampus.model.SensorReading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DataStore {
    public static final Map<String, Room> rooms = new HashMap<>();
    public static final Map<String, Sensor> sensors = new HashMap<>();

    // key = sensorId, value = list of readings for that sensor
    public static final Map<String, List<SensorReading>> sensorReadings = new HashMap<>();
}