package com.example.administrator.greendao_demo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.greendao_demo.greendao.gen.DaoMaster;
import com.example.administrator.greendao_demo.greendao.gen.DaoSession;
import com.example.administrator.greendao_demo.greendao.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDbHelp();
        mTvResult = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增
                String name = "小明";
                int age = 25;
                QueryBuilder qb = userDao.queryBuilder();
                User user = new User();
                user.setName(name);
                user.setAge(age);
                userDao.insert(user);
                Toast.makeText(MainActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删
                String name = "小明";
                if (!TextUtils.isEmpty(name)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Name.eq(name)).list();
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size();i++){
                            userDao.deleteByKey(list.get(i).getId());
                        }
                        Toast.makeText(MainActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改
                String name = "小明";
                int age = 23;
                QueryBuilder qb = userDao.queryBuilder();
                ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Name.eq(name)).list();
                if (list.size() > 0){
                    User user = new User();
                    user.setId(list.get(0).getId());
                    user.setName("小白");
                    user.setAge(age);
                    userDao.update(user);
                    Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "不存在该数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查
                QueryBuilder qb = userDao.queryBuilder();
                ArrayList<User> list = (ArrayList<User>) qb.list();
                if (list.size() > 0) {
                    String text = "";
                    for (User user : list) {
                        text = text + "\r\n" + "id:" + user.getId() + ",name:" + user.getName() + ",age:" + user.getAge();
                    }
                    mTvResult.setText(text);
                } else {
                    mTvResult.setText("");
                    Toast.makeText(MainActivity.this, "不存在该数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*初始化数据库相关*/
    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}
