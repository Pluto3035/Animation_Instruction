package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Animation
         AlphaAnimation
         TranslateAnimation
         RotateAnimation
         ScaleAnimation
         AnimationSet

         duration 动画时长      repeatCount 重复次数 无限INFINITE
         repeatMode  重复的方式  restart 从头开始  reverse  从结束回到起始
         reset()  重新设置动画
         start()  启动动画
         cancel()  取消动画
         fillAfter  是否停留在结束状态
         fillBefore 回到其实状态
         setAnimationListener  监听事件

         创建动画的两种方式
                   xml配置 ：res /anim/xxx.xml
                   代码创建
         使用AnimationUtils 类来加载动画
         */
      mAlphaOut.setOnClickListener{
         // testAlphaXml(R.anim.fade_out_anim)
          testAlphaCode(1.0f,0.0f)
      }
        mAlphaIn.setOnClickListener{
         // testAlphaXml(R.anim.fade_in_anim)
            testAlphaCode(0.0f,1.0f)
        }

        mMove.setOnClickListener{
           // testMoveXml()
            testMoveCode()
        }

        mRotate.setOnClickListener{
           //  testRotateXml()
            testRotateCode()
        }

        mScale.setOnClickListener{
           // testScaleXml()
            testScaleCode()
        }

        mSet.setOnClickListener{
           //    testAnimationSetXml()
            testAnimationSetCode()
        }
    }

    //AnimationSet
    private fun testAnimationSetXml(){
        val animationSet =   AnimationUtils.loadAnimation(this,R.anim.set_anim) as AnimationSet
        view.startAnimation(animationSet)
    }

    private fun testAnimationSetCode(){
      val scaleAnimation=  ScaleAnimation(0.1f,1.5f,0.1f,1.5f,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f)

      val rotateAnimation=  RotateAnimation(0f,360f,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f)

        AnimationSet(true).apply {
          addAnimation(scaleAnimation)
            addAnimation(rotateAnimation)
            duration = 1500
            view.startAnimation(this)
        }
    }

    //ScaleAnimation
    private fun testScaleXml(){
      val scaleAnimation=  AnimationUtils.loadAnimation(this,R.anim.scale_anim) as ScaleAnimation
        view.startAnimation(scaleAnimation)
    }

    private fun testScaleCode(){
        //默认以原点作为缩放点
        /**
        ScaleAnimation(0.8f,1.5f,0.8f,1.5f).apply {
            duration = 1000
        }.also {
            view.startAnimation(it)
        }
        */

        /**
        ScaleAnimation(0.8f,1.5f,0.8f,1.5f,0.5f*view.width,0.5f*view.height).apply {
            duration =1000
        }.also {
            view.startAnimation(it)
        }
        */

        ScaleAnimation(0.8f,1.5f,0.8f,1.5f,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f).apply {
            duration =1000
            repeatCount = Animation.INFINITE
        }.also {
            view.startAnimation(it)
        }
    }

    //RotateAnimation
    private fun testRotateXml(){
    val rotateAnimation=  AnimationUtils.loadAnimation(this,R.anim.rotate_anim) as RotateAnimation
        view.startAnimation(rotateAnimation)
    }

    private fun testRotateCode(){
        /**
         RotateAnimation(0f,360f,0.5f*view.width,0.5f*view.height).apply {
             duration = 1000

         }.also {
             view.startAnimation(it)
         }
         */
        //使用相对的方式设置旋转中心点
      RotateAnimation(0f,360f,
          Animation.RELATIVE_TO_SELF,0.5f,
          Animation.RELATIVE_TO_SELF,0.5f).apply {
          duration = 1000
      }.also {
          view.startAnimation(it)
      }
    }

    //TranslateAnimation
    private fun testMoveCode(){
        /**
            TranslateAnimation(0f,200f,0f,0f).apply {
                duration = 1000
                fillAfter = true
            }.also {
                view.startAnimation(it)
            }
            */
        TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,
            Animation.RELATIVE_TO_SELF,1f,
            Animation.RELATIVE_TO_SELF,0f,
            Animation.RELATIVE_TO_SELF,1f
            ).apply {
            duration = 1000
            fillAfter = true
        }.also {
            view.startAnimation(it)
        }
    }

    /**
     xml配置移动距离时  可以使用3种方式
        100 ABSOLUTE 固定值 相对于自己的起点x或y 移动对应的距离
        100%  RELATIVE_TO_SELF相对于自身的比例  移动的距离和自身宽高的比例
        100%p RELATIVE_TO_PARENT  相对于父容器的比例  移动的距离是父容器宽高的比例
     */

    private fun testMoveXml(){
        val translateAnimation = AnimationUtils.loadAnimation(this,R.anim.move_anim) as TranslateAnimation
        view.startAnimation(translateAnimation)
    }

   // AlphaAnimation
    private fun testAlphaCode(from:Float,to:Float){
       AlphaAnimation(from,to).apply {
           duration = 1000
           fillAfter= true
           repeatCount = Animation.INFINITE
           repeatMode = Animation.REVERSE
           setAnimationListener(object :Animation.AnimationListener{
               override fun onAnimationRepeat(animation: Animation?) {

               }

               override fun onAnimationEnd(animation: Animation?) {

               }

               override fun onAnimationStart(animation: Animation?) {

               }

           })
       }.also {
           view.startAnimation(it)
       }
   }

    private fun testAlphaXml(id:Int){
      //将xml配置的动画解析出来
    val alphaAnimation = AnimationUtils.loadAnimation(this,id) as AlphaAnimation

       //将这个动画添加到视图上
      view.startAnimation(alphaAnimation)
    }
}
