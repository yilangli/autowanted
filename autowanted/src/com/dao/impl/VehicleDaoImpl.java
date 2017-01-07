package com.dao.impl;


import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Vehicle;
import com.dao.VehicleDao;
import com.util.JdbcUtils;

public class VehicleDaoImpl implements VehicleDao {
	private JdbcUtils jdUtils = new JdbcUtils();
	
	@Override
	public void create(Vehicle vehicle) {
		// TODO Auto-generated method stub
		String sql="insert into vehicle (vin, make, model, mileage, fuel, exterior_color, engine, transmission, drive_type, interior_color, year, image) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		List params = new ArrayList();
		params.add(vehicle.getVin());
		params.add(vehicle.getMake());
		params.add(vehicle.getModel());
		params.add(vehicle.getMileage());
		params.add(vehicle.getFuel());
		params.add(vehicle.getExteriorColor());
		params.add(vehicle.getEngine());
		params.add(vehicle.getTransmission());
		params.add(vehicle.getDriveType());
		params.add(vehicle.getInteriorColor());
		params.add(vehicle.getYear());
		params.add(vehicle.getImage());
		jdUtils.getConnection();
		try {
			jdUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdUtils.releaseConn();
		
	}

	@Override
	public void update(Vehicle vehicle) {
		// TODO Auto-generated method stub
		String sql="update vehicle make=?, model=?, mileage=?, fuel=?, exterior_color=?, engine=?, transmission=?, drive_type=?, interior_color=?, year=? where vin=?";
		List params = new ArrayList();
		params.add(vehicle.getMake());
		params.add(vehicle.getModel());
		params.add(vehicle.getMileage());
		params.add(vehicle.getFuel());
		params.add(vehicle.getExteriorColor());
		params.add(vehicle.getEngine());
		params.add(vehicle.getTransmission());
		params.add(vehicle.getDriveType());
		params.add(vehicle.getInteriorColor());
		params.add(vehicle.getYear());
		params.add(vehicle.getVin());
		jdUtils.getConnection();
		try {
			jdUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jdUtils.releaseConn();
	}

	@Override
	public Vehicle read(String vin) {
		// TODO Auto-generated method stub
		String sql="select * from vehicle where vin=\""+vin+"\"";
		Map map = new HashMap();
		jdUtils.getConnection();
		try {
			map = jdUtils.findSimpleResult(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map.isEmpty()){
			return null;
		}
		Vehicle resultVehicle = new Vehicle();
		resultVehicle.setDriveType(map.get("drive_type").toString());
		resultVehicle.setEngine(map.get("engine").toString());
		resultVehicle.setExteriorColor(map.get("exterior_color").toString());
		resultVehicle.setFuel(map.get("fuel").toString());
		resultVehicle.setInteriorColor(map.get("interior_color").toString());
		resultVehicle.setMake(map.get("make").toString());
		resultVehicle.setMileage((Integer) map.get("mileage"));
		resultVehicle.setModel(map.get("model").toString());
		resultVehicle.setTransmission(map.get("transmission").toString());
		resultVehicle.setVin(map.get("vin").toString());
		resultVehicle.setYear((Integer) map.get("year"));
		resultVehicle.setImage(map.get("image").toString());
		jdUtils.releaseConn();
		return resultVehicle;
	}

}
