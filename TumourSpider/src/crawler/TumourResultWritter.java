package crawler;

import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.writter.IResultWritter;
import com.lz.octopus.writter.db.JDBCUtils;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/11/27.
 */
@TaskTypeAnnotation(taskType="39TUMOURSPIDER", version="201611292207")
public class TumourResultWritter implements IResultWritter{

    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public void writeResult(List<Result> resultList) {
        jdbcUtils.batchInsertCrawlResult(resultList);
    }
}
