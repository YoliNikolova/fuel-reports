package commands;

import com.beust.jcommander.Parameter;

public class ReportCommands {
    @Parameter(names = "--period", description = "Get period", required = true)
    String period;

    @Parameter(names = "--fuel-type", description = "Get fuelType", required = false)
    String fuelType;

    @Parameter(names = "--petrol-station", description = "Get petrolStation", required = false)
    String petrolStation;

    @Parameter(names = "--city", description = "Get city", required = false)
    String cityName;

    public ReportCommands() {

    }

    public ReportCommands(String period, String fuelType, String petrolStation, String cityName) {
        this.period = period;
        this.fuelType = fuelType;
        this.petrolStation = petrolStation;
        this.cityName = cityName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getPetrolStation() {
        return petrolStation;
    }

    public void setPetrolStation(String petrolStation) {
        this.petrolStation = petrolStation;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
