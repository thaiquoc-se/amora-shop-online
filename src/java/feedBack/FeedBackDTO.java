/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedBack;

import java.sql.Date;

/**
 *
 * @author long
 */
public class FeedBackDTO {
    private int feedBackID;
    private String fullName;
    private float rated_star;
    private String feedBack;
    private String image;
    private String status;
    private int productID;
    private int userID;
    private Date date;
    private String feedBackImage;

    public FeedBackDTO() {
    }

    public FeedBackDTO(int feedBackID, String fullName, float rated_star, String feedBack, String image, String status, int productID, int userID, Date date, String feedBackImage) {
        this.feedBackID = feedBackID;
        this.fullName = fullName;
        this.rated_star = rated_star;
        this.feedBack = feedBack;
        this.image = image;
        this.status = status;
        this.productID = productID;
        this.userID = userID;
        this.date = date;
        this.feedBackImage = feedBackImage;
    }

    public int getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(int feedBackID) {
        this.feedBackID = feedBackID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getRated_star() {
        return rated_star;
    }

    public void setRated_star(float rated_star) {
        this.rated_star = rated_star;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFeedBackImage() {
        return feedBackImage;
    }

    public void setFeedBackImage(String feedBackImage) {
        this.feedBackImage = feedBackImage;
    }


    

}
