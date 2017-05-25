package org.usfirst.frc.team1014.robot.log;

import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class CSVLogColumn {
	
	private final String name;
	private final String unit;
	private final String description;
	
	private enum ColumnDataType {
		STRING, INT, DOUBLE;
	}
	
	private ColumnDataType type;
	private Optional<Supplier<String>> stringSupplier = Optional.empty();
	private Optional<IntSupplier> intSupplier = Optional.empty();
	private Optional<DoubleSupplier> doubleSupplier = Optional.empty();

	
	public CSVLogColumn(String name, String unit, String description, Supplier<String> supplier) {
		this.name = name;
		this.unit = unit;
		this.description = description;
		this.stringSupplier = Optional.of(supplier);
		this.type = ColumnDataType.STRING;
	}
	
	public CSVLogColumn(String name, String unit, String description, IntSupplier supplier) {
		this.name = name;
		this.unit = unit;
		this.description = description;
		this.intSupplier = Optional.of(supplier);
		this.type = ColumnDataType.INT;
	}
	
	public CSVLogColumn(String name, String unit, String description, DoubleSupplier supplier) {
		this.name = name;
		this.unit = unit;
		this.description = description;
		this.doubleSupplier = Optional.of(supplier);
		this.type = ColumnDataType.DOUBLE;
	}
	
	public String get() {
		switch(type) {
		case STRING:
			// quotes prevent false delimiters
			return "\"" + stringSupplier.get().get() + "\"";
		case INT:
			return Integer.toString(intSupplier.get().getAsInt());
		case DOUBLE:
			return Double.toString(doubleSupplier.get().getAsDouble());
		}
		return "INVALID TYPE"; // should never run
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public String getType() {
		return type.toString();
	}
}
