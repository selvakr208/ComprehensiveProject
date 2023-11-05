package assignment;
public enum Months {
    January("Jan"),
    Febraury("Feb"),
    March("Mar"),
    April("Apr"),
    May("May"),
    June("Jun"),
    July("Jul"),
    August("Aug"),
    September("Sep"),
    October("Oct"),
    November("Nov"),
    December("Dec");

    private String description;
    Months(String description) {
        this.description= description;
    }
    public String getDescription(){
        return description;
    }
}
