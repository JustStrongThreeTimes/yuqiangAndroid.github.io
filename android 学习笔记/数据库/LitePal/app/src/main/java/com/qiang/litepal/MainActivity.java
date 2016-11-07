package com.qiang.litepal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qiang.litepal.bean.Comment;
import com.qiang.litepal.bean.News;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button one,two,three,four,five,six,seven,eight,nine,ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = Connector.getDatabase();
        initView();


    }

    private void initView() {
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight =(Button) findViewById(R.id.eight);
        ten =(Button) findViewById(R.id.ten);
        nine =(Button) findViewById(R.id.nine);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        ten.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //存储信息
            case R.id.one :
                News news = new News();
                news.setTitle("这是一条新闻标题");
                news.setContent("这是一条新闻内容");
                news.setPublishDate(new Date());
                if (news.save()) {
                    Toast.makeText(getApplicationContext(), "存储成功"+ news.getId(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "存储失败", Toast.LENGTH_SHORT).show();
                }
                break;
//存储关联表
            case R.id.two :
                Comment comment1 =new Comment();
                comment1.setContent("haha1");
                comment1.setPublishDate(new Date());
                comment1.save();
                Comment comment2 =new Comment();
                comment2.setContent("haha2");
                comment2.setPublishDate(new Date());
                comment2.save();
                News news1 =new News();
                news1.getCommentList().add(comment1);
                news1.getCommentList().add(comment2);
                news1.setTitle("第二条新闻标题");
                news1.setContent("第二条新闻内容");
                news1.setPublishDate(new Date());
                news1.setCommentCount(news1.getCommentList().size());
                news1.save();
//根据id修改信息
            case R.id.three:
                ContentValues values =new ContentValues();
                values.put("title","qianggechaoshuai");
                DataSupport.update(News.class,values,2);
//或者
               /* News updateNews = new News();
                updateNews.setTitle("今日iPhone6发布");
                updateNews.update(2);*/
                break;
//根据条件修改信息
            case  R.id.four:
                ContentValues values1 =new ContentValues();
                values1.put("title","qianqiang");
                DataSupport.updateAll(News.class,values1,"title = ?","qianggechaoshuai");
//或者
                /* News updateNews = new News();
                updateNews.setTitle("今日iPhone6发布");
                updateNews.updateAll("title = ? and commentcount > ?", "今日iPhone6发布", "0");*/
                break;

            case R.id.five :
                DataSupport.delete(News.class, 2);

                //根据条件删除
                /*DataSupport.deleteAll(News.class, "title = ? and commentcount = ?", "今日iPhone6发布", "0");*/
                break;
            case R.id.six:
                //简单查询
                News news2 = DataSupport.find(News.class, 1);
                //查询第一条
                News firstNews = DataSupport.findFirst(News.class);
                //查询最后一条
                News lastNews = DataSupport.findLast(News.class);

                System.out.println(news2.getTitle()+"------"+
                        firstNews.getTitle()+"---------"
                +lastNews.getTitle());
                break;
            case  R.id.seven:
                //根据 id list 查询
                List<News> newsList = DataSupport.findAll(News.class, 1, 3, 5, 7);

            /*    long[] ids = new long[] { 1, 3, 5, 7 };
                List<News> newsList = DataSupport.findAll(News.class, ids);*/
                for(News news3 :newsList){
                    System.out.println(news3.getTitle());
                }
                //查询所有
               /* List<News> allNews = DataSupport.findAll(News.class);*/
                break;

            case R.id.eight:
                //根据条件查询
                List<News> newsList2 = DataSupport.where("commentcount > ?", "0").find(News.class);
                //Select Where
              /*  List<News> newsList = DataSupport.select("title", "content")
                        .where("commentcount > ?", "0")
                        .find(News.class);*/

                //查询条件 where order limit 分页：offset 每页显示条数
               /* List<News> newsList = DataSupport.select("title", "content")
                        .where("commentcount > ?", "0")
                        .order("publishdate desc").limit(10).offset(10)
                        .find(News.class);*/
                // 利用sql 查询 Cursor cursor = DataSupport.findBySQL("select * from news where commentcount>?", "0");
                break;

            case R.id.nine :
                //获得个数
                int result = DataSupport.count(News.class);
               /* int result = DataSupport.where("commentcount = ?", "0").count(News.class);*/
                break;

            case R.id.ten:
                //sum求和 commentcount 属性名
                int result1 = DataSupport.sum(News.class, "commentcount", int.class);
                //平均
                double result2 = DataSupport.average(News.class, "commentcount");
                //最小
                int result3 = DataSupport.min(News.class, "commentcount", int.class);
                //最大
                int result4 = DataSupport.max(News.class, "commentcount", int.class);
                break;


        }
    }


   /* News updateNews = new News();
    updateNews.setToDefault("commentCount");
    updateNews.updateAll();数据清0*/
}
