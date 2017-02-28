package crawler;

import com.lz.octopus.common.httpclient.HttpClient;
import com.lz.octopus.common.httpclient.HttpClientBuilder;
import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.Task;
import com.lz.octopus.common.task.TaskType;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.crawler.IBusinessCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/11/27.
 */
@TaskTypeAnnotation(taskType="39TUMOURSPIDER", version="201612112207")
public class TumourCrawler implements IBusinessCrawler {
    private String[] keyWord = {"癌","瘤","免疫力"};

    private HttpClient httpClient;

    public TumourCrawler(){
        this.httpClient = HttpClientBuilder.createDefault()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .buildHttpClient();
    }
    @Override
    public List<Result> crawl(Task task) {
        List<Result> resultList = new ArrayList<>();
        TumourTaskDetail tumourTaskDetail = (TumourTaskDetail)task.getTaskDetail();
        String url = tumourTaskDetail.getUrl();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("User-Agent", "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
        Map<String, String> retMap = httpClient.sendGet(url,headers);
        String response_content =  retMap.get("response_content");
        Document doc = Jsoup.parse(response_content);
        Elements elements = doc.select(".con_left").select(".list1");
        for(Element elem : elements){
            //parse the html
            try{
                Element divElement = elem;
                Element titleElement = divElement.select(".span2").select(".title").select("a").first();
                String title = titleElement.text();
                String srcUrl = titleElement.attr("href");
                Element ownerElement = divElement.select(".span3").select("a").first();
                String owner = ownerElement.text();
                Element timeElement = divElement.select(".span5").first();
                String time = timeElement.text();
                TumourResultDetail tumourResultDetail = new TumourResultDetail();

                //fill the field of the result detail
                tumourResultDetail.setTitle(title);
                tumourResultDetail.setOwner(owner);
                tumourResultDetail.setTime(time);
                tumourResultDetail.setSrcUrl(srcUrl);
                //construct Result
                Result result = new Result(task.getTaskType(), null);
                result.setBuildTime(task.getBuildTime());
                result.setTaskId(task.getTaskId());
                result.setResultDetail(tumourResultDetail);

                if(title.contains(keyWord[0]) || title.contains(keyWord[1]) || title.contains(keyWord[2])){
                    resultList.add(result);
                    System.out.println("title:" + title + " owner :" + owner + " time : " + time + " srcUrl: " + srcUrl);

                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        return resultList;
    }

    @Override
    public Class<?> getTaskDetailClass() {
        return TumourTaskDetail.class;
    }

    public static void main(String[] args) {
        TumourCrawler c = new TumourCrawler();
        TumourTaskDetail taskDetail = new TumourTaskDetail();
        taskDetail.setUrl("http://bbs.39.net/by/forum/375-1-1.html");
        TaskType taskType = new TaskType("TUMOURSPIDER", 5, 6);
        Task task = new Task(taskType, taskDetail);
        List<Result> resultList = c.crawl(task);

    }
}
