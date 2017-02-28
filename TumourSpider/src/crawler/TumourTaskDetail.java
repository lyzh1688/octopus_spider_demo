package crawler;


import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

/**
 * Created by 胡志洁 on 2016/11/27.
 */
@TaskTypeAnnotation(taskType="39TUMOURSPIDER", version="201612112207")
public class TumourTaskDetail extends ITaskDetail {

    private String url;

    private String taskType ="39TUMOURSPIDER";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }


}
