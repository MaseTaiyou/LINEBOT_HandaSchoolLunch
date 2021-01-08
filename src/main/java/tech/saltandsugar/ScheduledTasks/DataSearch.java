package tech.saltandsugar.ScheduledTasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.saltandsugar.GetData.DataProcessor;
import tech.saltandsugar.Utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Component
@EnableScheduling
public class DataSearch {
    @Value("${configuration.filepath}")
    private String DATA_FOLDER_PATH;
    //今日の献立
    public static String[] todayKyushokuKondate = new String[13];//csv横列は14列だから
    @Autowired//なんか黄色いなみなみあるけどとりあえず使えるしいいんじゃね?
    DataProcessor dataProcessor;
    //毎日AM0:00に実行
    @Scheduled(cron = "0 0 0 * * MON-FRI", zone = "Asia/Tokyo")
    public void doSearch(){
        log.info("検索を実施します");
        Date date = new Date();
        String[] Array_nowDate = Utility.toArray(date);
        Path CSV_FileLocation;
        //TODO ゴリ押しな気がするので直したい(美しくない)
        if (Array_nowDate[1].length() == 1) {
            CSV_FileLocation = Paths.get(DATA_FOLDER_PATH + "kyushoku_" + Array_nowDate[0] + "0" + Array_nowDate[1] + ".csv");
        } else {
            CSV_FileLocation = Paths.get(DATA_FOLDER_PATH + "kyushoku_" + Array_nowDate[0] + Array_nowDate[1] + ".csv");
        }
        ArrayList<String[]> data = dataProcessor.CSV_toArrayList(CSV_FileLocation);
        if(data==null){
            log.error("正常にCSVファイルを読み込めなかったようなので、アプリケーションを終了します。");
            System.exit(1);
        }
        int result = dataProcessor.doSearchLine(data,3,Utility.toArray(date)[2]);
        if(result==-1){
            log.error("原因不明エラー");
        }else {
            String[] finaldata = data.get(result);
            if(!(finaldata.length<5)){
                //平日
                todayKyushokuKondate = new String[]
                        {finaldata[4], finaldata[5], finaldata[6], finaldata[7], finaldata[8], finaldata[9], finaldata[10], finaldata[11], finaldata[12], finaldata[13]};
                //わーきれいなスパゲッティコード！！！！！
            }else{
                //休日
                todayKyushokuKondate=null;
            }
        }
    }
}