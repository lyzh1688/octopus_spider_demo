package crawler;

import com.lz.octopus.builder.ITaskBuilder;
import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaowangzi
 */
@TaskTypeAnnotation(taskType = "BAIDUNEWS", version = "201702182000")
public class BaiduNewsTaskBuilder implements ITaskBuilder {

	private final int pageCnt = 20;

	@Override
	public List<ITaskDetail> buildTasks() {
		List<ITaskDetail> taskList = new ArrayList<>();
		String baseURL = "http://news.baidu.com/";
		String keywords[] = { "梅西百货", "优衣库", "Gap", "韩都衣舍", "乐天百货", "老佛爷百货" };
		for (int k = 0; k < keywords.length; k++) {
			String crawlURL = "ns?word=" + keywords[k] + "&pn={PN}&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";
			for (int i = 0; i < 20; ++i) {
				BaiduNewsTaskDetail baiduNewsTaskDetail = new BaiduNewsTaskDetail();
				baiduNewsTaskDetail.setUrl(baseURL + crawlURL.replace("{PN}", String.valueOf(i * pageCnt)));
				baiduNewsTaskDetail.setKeyWord(keywords[k]);
				taskList.add(baiduNewsTaskDetail);
			}
		}

		return taskList;
	}
}
