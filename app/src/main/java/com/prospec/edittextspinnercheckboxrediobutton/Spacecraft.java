package com.prospec.edittextspinnercheckboxrediobutton;

//class get and set
public class Spacecraft {

    //    ประกาศตัวแปร
    private int id;
    private String name;
    private String propellant;
    private int technologyExists;

    //    ตัวรับและตัวตั้ง
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropellant() {
        return propellant;
    }

    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public int getTechnologyExists() {
        return technologyExists;
    }

    public void setTechnologyExists(int technologyExists) {
        this.technologyExists = technologyExists;
    }
    //    toString
    public String toString() {
        return name;
    }
}