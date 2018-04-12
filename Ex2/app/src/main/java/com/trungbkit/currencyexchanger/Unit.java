package com.trungbkit.currencyexchanger;

public class Unit {
  public String name;
  private double rate;
  public Unit(String _name, double _rate) {
    this.name = _name;
    this.rate = _rate;
  }
  public double exchangeTo(Unit destUnit) {
    return destUnit.rate / this.rate;
  }
}
