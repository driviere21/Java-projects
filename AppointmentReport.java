package Model;

/** This class supplies the appointment report object. */
public class AppointmentReport {
    String month;

    String Type;
    String amount;

    /** Constructor for and instance of the appointment report object.
     *
     * @param type type of appointment
     * @param amount count for the report
     * @param month  appointment by month
     *
     *
     */
    public AppointmentReport(String month,  String type, String amount) {
        this.month = month;

        Type = type;
        this.amount = amount;
    }

    public AppointmentReport() {
    }


    /** The getter for month.
     *
     * @return the month
     * */
    public String getMonth() {
        return month;
    }

    /** The month setter. */
    public void setMonth(String month) {
        this.month = month;
    }


    /** The type getter.
     *
     * @return the type
     *
     */
    public String getType() {
        return Type;
    }

    /** The type setter. */
    public void setType(String type) {
        Type = type;
    }

    /** The amount getter.
     *
     * @return the amount
     *
     */
    public String getAmount() {
        return amount;
    }

    /** The amount setter. */
    public void setAmount(String amount) {
        this.amount = amount;
    }
}


