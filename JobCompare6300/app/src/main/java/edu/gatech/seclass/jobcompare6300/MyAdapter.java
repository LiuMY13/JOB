package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private List<String> listText;
    private Context context;
    private Map<Integer,Boolean> map=new HashMap<>();
    private int count =0;
    private int maxCheckedCount = 2; // 最多允许选择的次数
    private int checkedCount = 0; // 记录已选择的次数
    private List<String> selectedJobDetails;//记录被选中数据的详细信息


    public MyAdapter(List<String> listText,Context context){
        this.listText=listText;
        this.context=context;
        this.selectedJobDetails = new ArrayList<>();
    }
    @Override
    public int getCount() {
        //return返回的是int类型，也就是页面要显示的数量。
        return listText.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            //通过一个打气筒 inflate 可以把一个布局转换成一个view对象
            view=View.inflate(context,R.layout.list_view_item,null);
        }else {
            view=convertView;//复用历史缓存对象
        }
        //单选按钮的文字
        //在这里改数据，离胜利不远了！
        String[] listText2 = listText.get(position).split(",");
        Log.d("Debug", "Value at index 12: " + listText2[12]+"  datatype:"+ listText2[12].getClass().getName());
        //judge if it is current job?
        if(listText2[12].equals("true")) {
            TextView radioText=(TextView)view.findViewById(R.id.tv_check_text);
            //radioText.setText(listText.get(position));
            radioText.setText(listText2[0]+"*");

            TextView titleText=(TextView)view.findViewById(R.id.list_rank);
            //titleText.setText(listText.get(position));
            titleText.setText(listText2[1]+"*");

            TextView companyText=(TextView)view.findViewById(R.id.list_title);
            //companyText.setText(listText.get(position));
            companyText.setText(listText2[2]+"*");

            TextView scoreText=(TextView)view.findViewById(R.id.list_company);
            //scoreText.setText(listText.get(position));
            scoreText.setText(listText2[3]+"*");

        }
        else {
            TextView radioText = (TextView) view.findViewById(R.id.tv_check_text);
            //radioText.setText(listText.get(position));
            radioText.setText(listText2[0]);

            TextView titleText = (TextView) view.findViewById(R.id.list_rank);
            //titleText.setText(listText.get(position));
            titleText.setText(listText2[1]);

            TextView companyText = (TextView) view.findViewById(R.id.list_title);
            //companyText.setText(listText.get(position));
            companyText.setText(listText2[2]);

            TextView scoreText = (TextView) view.findViewById(R.id.list_company);
            //scoreText.setText(listText.get(position));
            scoreText.setText(listText2[3]);
        }



        //单选按钮
        final CheckBox checkBox=(CheckBox)view.findViewById(R.id.rb_check_button);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    if (checkedCount < maxCheckedCount) {
                        map.put(position, true);
                        checkedCount++;
                        // 将选中的数据加入 selectedJobDetails 列表中
                        String selectedJob = listText.get(position);
                        selectedJobDetails.add(selectedJob);

                    }
                    else{
                        checkBox.setChecked(false); // 不允许再选中
                    }


                }else {
                    map.put(position,false);
                    checkedCount--;
                    //map.remove(position);
                    // 将取消选中的数据从 selectedJobDetails 列表中移除
                    String deselectedJob = listText.get(position);
                    selectedJobDetails.remove(deselectedJob);

                }
            }
        });
        if(map!=null&&map.containsKey(position)){
            checkBox.setChecked(true);

        }else {
            checkBox.setChecked(false);

        }
        return view;
    }
    //用来检查我选择了多少个
    public int getCheckedItemCount() {

        return checkedCount;
    }

    //用来传输被选中的工作的数据，直接传入到compare_table页面
    public List<String> getSelectedJobDetails(){
        return selectedJobDetails;
    }




}