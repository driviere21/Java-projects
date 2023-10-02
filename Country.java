package Model;

/** This class supplies the country object. */
public class Country {
    private int countryId;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /** Constructor for an instance of the appointment schedule object.
     * @param lastUpdatedBy the user to last update
     * @param lastUpdate the date and time of last update
     * @param createdBy the user that created the transaction
     * @param createDate the date and time the transaction was created
     * @param country the country assigned
     * @param countryId  the country ID
     *
     *
     */
    public Country(int countryId,  String country, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Country() {
    }

    public Country(String country) {
        this.country = country;

    }

    /** The country ID getter.
     *
     * @return the country ID
     *
     */
    public int getCountryId() {
        return countryId;
    }

    /** The country ID setter. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** The country getter.
     *
     * @return the country
     *
     */
    public String getCountry() {
        return country;
    }

    /** The country setter. */
    public void setCountry(String country) {
        this.country = country;
    }

    /** The create date getter.
     *
     * @return date created
     *
     */
    public String getCreateDate() {
        return createDate;
    }

    /** The create date setter. */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /** The created By getter.
     *
     * @return created By
     *
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** The created by setter. */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** The last update getter.
     *
     * @return last update
     *
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /** The last update setter. */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** The last updated by getter.
     *
     * @return last updated by
     *
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** The last updated setter. */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
