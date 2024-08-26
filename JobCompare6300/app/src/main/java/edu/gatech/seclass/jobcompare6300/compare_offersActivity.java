package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.util.ToastUtil;

public class compare_offersActivity extends Activity{
    //创建显示列表的listView
    private ListView listView;
    private TextView textview;
    //列表标题list
    private List<String> listText;
    //创建适配器对象
    private MyAdapter adapter;
    private int chekedCount =0;
    //private checkedTextView =
    private Button mBtnCompareJobsBackMain;
    private Button mBtnCompareJobsCompare;
    // 在另一个页面中获取数据库实例

    private JobDatabase jobDatabase= JobDatabase.getObj(this);;
    private List<Job> allJobs = jobDatabase.getAllJobs();;
    private Job current_job= jobDatabase.getCurrentJob();



// 现在allJobs中包含了数据库中所有的offer数据，你可以对其进行进一步处理或展示在界面上

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_offers);
        //初始化页面对象
        init();
        //将数据显示在页面上
        initDate();
        //initChecked();
        mBtnCompareJobsBackMain = findViewById(R.id.compare_job_back_main);

        mBtnCompareJobsBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job_compare_back  = "Back to the main menu";
                ToastUtil.showMsg(compare_offersActivity.this,job_compare_back);
                Intent intent = null;
                intent = new Intent(compare_offersActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mBtnCompareJobsCompare = findViewById(R.id.compare_job_compare);
        mBtnCompareJobsCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chekedCount_num = adapter.getCheckedItemCount();
                if(chekedCount_num==2) {
                    String job_compare_compare = "enter compare page";
                    List<String> selectedItems = adapter.getSelectedJobDetails();
                    ToastUtil.showMsg(compare_offersActivity.this, job_compare_compare);
                    Intent intent = null;
                    intent = new Intent(compare_offersActivity.this, compare_offers_tableActivity.class);
                    intent.putStringArrayListExtra("selectedItems",new ArrayList<>(selectedItems));
                    startActivity(intent);
                }
                else{
                    String job_select = "please select two jobs";
                    ToastUtil.showMsg(compare_offersActivity.this, job_select);
                }
            }
        });


    }
    public  void init(){
        listView=(ListView) findViewById(R.id.lv_text_view);
        listText=new ArrayList<String>();
        Log.d("MyTag", "listText: " + listText);


    }
    Comparator<String> jobScoreComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            // 以第3个逗号后的数字（即工作得分）进行比较排序
            double score1 = Double.parseDouble(o1.split(",")[3]);
            double score2 = Double.parseDouble(o2.split(",")[3]);
            int intScore1 = (int) score1;
            int intScore2 = (int) score2;
            return intScore2 - intScore1;
        }
    };


    public  void initDate(){
        //模拟创建数据
        //private List<String> listText
        int num_job = allJobs.size();
        for (int i=0;i<num_job;i++){
            //0序号，1:title，2:company,3:score,4:location,5:col_index,6:annual_salary,7:annual_bonus
            //8:stock_options,9:hbp_fund,10:num_holidays,11:monthly_interval,12:current_job
            listText.add(""+(i+1)+","+allJobs.get(i).getJob_title()+","+allJobs.get(i).getCompany()+","+(int)allJobs.get(i).getJob_score()
                    +","+allJobs.get(i).getLocation()+","+allJobs.get(i).getCol_index()+","+allJobs.get(i).getAnnual_salary()
                    +","+allJobs.get(i).getAnnual_bonus()+","+allJobs.get(i).getStock_options()+","+allJobs.get(i).getHbp_fund()
                    +","+allJobs.get(i).getNum_holidays()+","+allJobs.get(i).getMonthly_internet()+","+allJobs.get(i).isCurrent_job()
            );
            //listText.add(","+current_job.getJob_title());
            //listText.add(","+current_job.getCompany());
            //listText.add(","+current_job.getJob_score());
            //listText.add(","+current_job.getJob_title());
            //listText.add("jinjinjin"+i);//rank这一行的数据
        }
        //listText = allJobs;
        Collections.sort(listText, jobScoreComparator);
        for (String text : listText) {
            Log.d("paixupaiux",text);
        }
        // 创建一个新的List来存储排序后的数据
        //List<String> sortedListText = new ArrayList<>(listText);
        adapter=new MyAdapter(listText,this);
        listView.setAdapter(adapter);
        Log.d("MyTag2", "listText: " + listText);
        //Map<Integer,Boolean> map2 = (Map<Integer, Boolean>) adapter.getItem(1);
        //Log.d("MyTag_map", "map: " + map2);

    }
    /**
    public  void initChecked(){
        textview = findViewById(R.id.checked_text_view);
        textview.setText("被循环中的次数"+chekedCount);


    }
     **/
    //找到控制件
    ;

}
