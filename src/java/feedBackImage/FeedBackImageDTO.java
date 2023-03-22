/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedBackImage;

/**
 *
 * @author long
 */
public class FeedBackImageDTO {

    private int feedBackImageID;
    private String feedBackImage;
    private int feedBackID;

    public FeedBackImageDTO() {
    }

    public FeedBackImageDTO(int feedBackImageID, String feedBackImage, int feedBackID) {
        this.feedBackImageID = feedBackImageID;
        this.feedBackImage = feedBackImage;
        this.feedBackID = feedBackID;
    }

    public int getFeedBackImageID() {
        return feedBackImageID;
    }

    public void setFeedBackImageID(int feedBackImageID) {
        this.feedBackImageID = feedBackImageID;
    }

    public String getFeedBackImage() {
        return feedBackImage;
    }

    public void setFeedBackImage(String feedBackImage) {
        this.feedBackImage = feedBackImage;
    }

    public int getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(int feedBackID) {
        this.feedBackID = feedBackID;
    }

}
