package com.example.phonelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.Files.delete

class MainActivity : AppCompatActivity() {

    private var shouldAutoSplit=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPhoneEdit.addTextChangedListener(object :LoginTextWatcher(){
            override fun afterTextChanged(s: Editable?) {
                //设置按钮是否可以点击
                mLogin.isEnabled = s.toString().length==13
                //判断是在删除还是输入
                if(!shouldAutoSplit){
                    return
                }
                s.toString().length.also {
                    if(it==3||it==8){
                        //当134  1234
                        //需要自动添加空格
                        s?.append(' ')
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //count表示在输入，before表示在删除，只有在输入的时候才自动添加
                shouldAutoSplit = count==1
            }
        })

        //按钮的点击事件
        mLogin.setOnClickListener{
            Intent().apply {
                //跳转方向
                setClass(this@MainActivity,VertifyActivity::class.java)
                //配置数据
                putExtra("phone",getPhoneNumber(mPhoneEdit.text))
                //启动
                startActivity(this)
            }
        }
    }




    //将格式化的内容转化为正常数据
    private fun getPhoneNumber(editable: Editable):String{
        //创建一个新的对象  用于操作editable里面的内容
        // 为啥这样做？    因为调用的时候时直接传mPhoneEdit.text这个对象，
        //                所以要创建SpannableStringBuilder这个对象来接收mPhoneEdit.text
        //                  中的数据，不直接对mPhoneEdit.tex这个对象进行操作
        /**
         * editable是一个接口，不能直接实例化，所以找它的实现类SpannableStringBuilder来
         * 接收mPhoneEdit.text这个对象的数据
         */
        SpannableStringBuilder(editable.toString()).also {
            //1234567 8910
            it.delete(3,4)

            //12345678910
            it.delete(7,8)

            return it.toString()
        }


    }

}

open class LoginTextWatcher:TextWatcher{
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

}