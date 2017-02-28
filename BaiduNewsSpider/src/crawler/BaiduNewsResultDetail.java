package crawler;

import com.lz.octopus.common.result.IResultDetail;

/**
 * @author xiaowangzi
 */
public class BaiduNewsResultDetail implements IResultDetail {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String source;
    private String time;
    private String title;
    private String href;
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "BaiduNewsResultDetail [title=" + this.title + ", source=" + this.source + ", time=" + this.time + ", keyword=" + this.keyword
                + ", href=" + this.href + "]";
    }
}
