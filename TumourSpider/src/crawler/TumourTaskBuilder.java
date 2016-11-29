package crawler;

import com.lz.octopus.builder.ITaskBuilder;
import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/11/27.
 */
@TaskTypeAnnotation(taskType="39TUMOURSPIDER", version="201611292207")
public class TumourTaskBuilder implements ITaskBuilder {
    @Override
    public List<ITaskDetail> buildTasks() {
        List<ITaskDetail> taskList = new ArrayList<>();
        String baseURL = "http://bbs.39.net/by/forum/375-1-#PAGE.html";
        for(int i = 1; i <= 200; i++) {
            TumourTaskDetail taskDetail = new TumourTaskDetail();
            taskDetail.setUrl(baseURL.replace("#PAGE", String.valueOf(i)));
            taskList.add(taskDetail);
        }
        return taskList;
    }
}
