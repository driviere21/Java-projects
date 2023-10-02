package Model;

/** This class supplies the customer report object. */
public class CustomerReport {
    String customerName;
    String division;


/** Constructor for an instance of the appointment schedule object.
 *
 * @param customerName customer name
 * @param division division ID
 *
 */
    public CustomerReport(String customerName, String division) {
        this.customerName = customerName;
        this.division = division;
    }

    public CustomerReport() {
    }

    /** The customer name getter.
     *
     * @return customer name
     *
     */
    public String getCustomerName() {
        return customerName;
    }

    /** The customer name setter. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** The divison ID getter.
     *
     * @return  division
     *
     */
    public String getDivision() {
        return division;
    }

    /** THe division setter. */
    public void setDivision(String division) {
        this.division = division;
    }
}
