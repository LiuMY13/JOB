package edu.gatech.seclass.jobcompare6300;

public class ComparisonSettings {
    private int salaryWeight;
    private int bonusWeight;
    private int stockWeight;
    private int homeBuyingProgramFundWeight;
    private int personalChoiceHolidaysWeight;
    private int internetStipendWeight;

    public ComparisonSettings(int salaryWeight, int bonusWeight, int stockWeight,
                              int homeBuyingProgramFundWeight, int personalChoiceHolidaysWeight,
                              int internetStipendWeight) {
        this.salaryWeight = salaryWeight;
        this.bonusWeight = bonusWeight;
        this.stockWeight = stockWeight;
        this.homeBuyingProgramFundWeight = homeBuyingProgramFundWeight;
        this.personalChoiceHolidaysWeight = personalChoiceHolidaysWeight;
        this.internetStipendWeight = internetStipendWeight;
    }

    public int getSalaryWeight() {
        return salaryWeight;
    }

    public void setSalaryWeight(int salaryWeight) {
        this.salaryWeight = salaryWeight;
    }

    public int getBonusWeight() {
        return bonusWeight;
    }

    public void setBonusWeight(int bonusWeight) {
        this.bonusWeight = bonusWeight;
    }

    public int getStockWeight() {
        return stockWeight;
    }

    public void setStockWeight(int stockWeight) {
        this.stockWeight = stockWeight;
    }

    public int getHomeBuyingProgramFundWeight() {
        return homeBuyingProgramFundWeight;
    }

    public void setHomeBuyingProgramFundWeight(int homeBuyingProgramFundWeight) {
        this.homeBuyingProgramFundWeight = homeBuyingProgramFundWeight;
    }

    public int getPersonalChoiceHolidaysWeight() {
        return personalChoiceHolidaysWeight;
    }

    public void setPersonalChoiceHolidaysWeight(int personalChoiceHolidaysWeight) {
        this.personalChoiceHolidaysWeight = personalChoiceHolidaysWeight;
    }

    public int getInternetStipendWeight() {
        return internetStipendWeight;
    }

    public void setInternetStipendWeight(int internetStipendWeight) {
        this.internetStipendWeight = internetStipendWeight;
    }

    public int getSum() {
        return salaryWeight + bonusWeight + stockWeight + homeBuyingProgramFundWeight + personalChoiceHolidaysWeight + internetStipendWeight;
    }

}
