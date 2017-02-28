package crawler;

import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

/**
 * @author xiaowangzi
 */
@TaskTypeAnnotation(taskType="BAIDUNEWS", version="201702182000")
public class BaiduNewsTaskDetail extends ITaskDetail {

    private String url;

    private String taskType = "BAIDUNEWS";

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
