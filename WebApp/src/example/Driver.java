
package example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for driver complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="driver">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="profPic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driver", propOrder = {
    "profPic",
    "rating",
    "userId",
    "userName",
    "userUsername",
    "vote"
})
public class Driver {

    protected String profPic;
    protected float rating;
    protected int userId;
    protected String userName;
    protected String userUsername;
    protected int vote;

    /**
     * Gets the value of the profPic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfPic() {
        return profPic;
    }

    /**
     * Sets the value of the profPic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfPic(String value) {
        this.profPic = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(float value) {
        this.rating = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUsername() {
        return userUsername;
    }

    /**
     * Sets the value of the userUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUsername(String value) {
        this.userUsername = value;
    }

    /**
     * Gets the value of the vote property.
     * 
     */
    public int getVote() {
        return vote;
    }

    /**
     * Sets the value of the vote property.
     * 
     */
    public void setVote(int value) {
        this.vote = value;
    }

}
