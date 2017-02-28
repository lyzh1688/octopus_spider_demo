package crawler;

import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.writter.IResultWritter;
import com.lz.octopus.writter.db.JDBCUtils;

import java.util.List;

/**
 * @author xiaowangzi
 */
@TaskTypeAnnotation(taskType="BAIDUNEWS", version="201702182000")
public class BaiduNewsResultWritter implements IResultWritter {

    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public void writeResult(List<Result> list) {
        this.jdbcUtils.batchInsertCrawlResult(list);
    }
}
