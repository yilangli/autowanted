package com.dao;

import com.bean.Vehicle;

public interface VehicleDao {
	public void create(Vehicle vehicle);
	public void update(Vehicle vehicle);
	public Vehicle read(String vin);
}
