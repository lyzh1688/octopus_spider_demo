package crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lz.octopus.common.httpclient.HttpClient;
import com.lz.octopus.common.httpclient.HttpClientBuilder;
import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.Task;
import com.lz.octopus.common.task.TaskType;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.crawler.IBusinessCrawler;

/**
 * @author xiaowangzi
 */
@TaskTypeAnnotation(taskType="BAIDUNEWS", version="201702182000")
public class BaiduNewsCrawler implements IBusinessCrawler {

    private HttpClient httpClient;

    public BaiduNewsCrawler(){
        this.httpClient = HttpClientBuilder.createDefault()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .buildHttpClient();
    }

    @Override
    public List<Result> crawl(Task task) {
        List<Result> resultList = new ArrayList<>();
        BaiduNewsTaskDetail baiduNewsTaskDetail = (BaiduNewsTaskDetail)task.getTaskDetail();
        String url = baiduNewsTaskDetail.getUrl();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("User-Agent", "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
        Map<String, String> retMap = httpClient.sendGet(url,headers);
        String response_content =  retMap.get("response_content");
        Document doc = Jsoup.parse(response_content);
        
        Elements elements = doc.select("#content_left .result");
        for(Element elem : elements){
        	
            Element a = elem.select("a").first();
            String href = a.attr("href");
            String title = a.text();
            Element span = elem.select(".c-author").first();
            String source = span.text().split(" ")[0];
            String time = span.text().split(" ")[2];
            
            BaiduNewsResultDetail baiduNewsResultDetail = new BaiduNewsResultDetail();

            //fill the field of the result detail
            baiduNewsResultDetail.setHref(href);
            baiduNewsResultDetail.setTime(time);
            baiduNewsResultDetail.setSource(source);
            baiduNewsResultDetail.setTitle(title);
            baiduNewsResultDetail.setKeyword(baiduNewsTaskDetail.getKeyWord());

            //construct Result
            Result result = new Result(task.getTaskType(), null);
            result.setBuildTime(task.getBuildTime());
            result.setTaskId(task.getTaskId());
            result.setResultDetail(baiduNewsResultDetail);

            resultList.add(result);
        }

        return resultList;
    }

    @Override
    public Class<?> getTaskDetailClass() {
        return BaiduNewsTaskDetail.class;
    }

    public static void main(String[] args) {
        BaiduNewsCrawler c = new BaiduNewsCrawler();
        BaiduNewsTaskDetail taskDetail = new BaiduNewsTaskDetail();
        taskDetail.setUrl("http://news.baidu.com/ns?word=Bloomindales&pn=0&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0");
        TaskType taskType = new TaskType("BAIDU_NEWS", 5, 6);
        Task task = new Task(taskType, taskDetail);
        List<Result> resultList = c.crawl(task);
        resultList.forEach(System.out::println);
        System.out.println(resultList);
        
//        List<ITaskDetail> taskList = new BaiduNewsTaskBuilder().buildTasks();
//        taskList.forEach((t)->{
//        	 TaskType taskType = new TaskType("BAIDU_NEWS", 5, 6);
//        	 Task task = new Task(taskType, t);
//        	 List<Result> resultList = c.crawl(task);
//        	 System.out.print("  -------------->    "+resultList.size());
//        	 resultList.forEach(System.out::println);
//        });
        
        System.out.println(" *** finished ***");
    }
}
