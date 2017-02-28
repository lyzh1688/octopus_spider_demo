package crawler;

import com.lz.octopus.common.result.IResultDetail;

/**
 * Created by 胡志洁 on 2016/11/27.
 */
public class TumourResultDetail implements IResultDetail {


    private String title;

    private String owner;

    private String time;

    private String srcUrl;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }
    @Override
    public String toString() {
        return "TumourResultDetail [title=" + title + ", owner=" + owner + ", time=" + time  + "]";
    }
}
