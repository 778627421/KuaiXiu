package judou.ceicheng.com.computerprotect.fragment


import android.app.Fragment
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.zxing.common.BitArray
import judou.ceicheng.com.computerprotect.FabuActivity
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.adapter.HomeAdapter
import judou.ceicheng.com.computerprotect.bean.EventHomeBean
import judou.ceicheng.com.computerprotect.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home2.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class Home2Fragment : Fragment() {

    var list: MutableList<HomeBean> = ArrayList<HomeBean>()
    var adapt:HomeAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        EventBus.getDefault().register(this)
        initRecyData()
        rv_home2.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapt=HomeAdapter(list, this!!.activity!!, fragmentManager)
        rv_home2.adapter= adapt
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun busEvent(eventBus: HomeBean) {
        var name=eventBus.name
        var question=eventBus.word
        var pic=eventBus.userpic
       // var bitmap=eventBus.bitmap

       adapt!!.addItem(0,eventBus)

        //Toast.makeText(activity,name+type+question, Toast.LENGTH_SHORT).show()
    }






    fun initRecyData() {
        val names = arrayOf("下单超人", "雷神索尔", "小红美美", "奥特曼王",
                "米斯特吴", "锋行天下")
        val words = arrayOf("我的电脑刚买不久，用了三个月，就感觉开机很慢,加我微信110，帮帮忙，拜托啦…",
                "我的电脑是在联想电脑城买的，用了不久，不小心丢失了原装的鼠标，有发现的好心人可以联系我电话1008611…",
                "本人因为外出上班， 不慎将电脑在天王星地铁站丢失，里面有很重要的公司文件，如果有发现，联系我120，重金酬谢…",
                "如果你的电脑感觉到运行缓慢，可以来找我啊，我是宇宙无敌修理工奥特曼，解决一切电脑难题，包括重装系统，更换硬盘",
                "出售，出售，本人Mac Pro 顶配，原价10086,现在只需要9999,出售出售，素来抢购…爱你哦，么么哒，吴老板电脑城",
                "我的电脑因为键盘进水，现在需要更换键盘，如果有懂这方面技术的可以联系我，电话是1008611，谢谢…")
        val userpics = intArrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4
                , R.drawable.pic5, R.drawable.pic6)

        val pics = intArrayOf(R.drawable.pics1, R.drawable.pics2, R.drawable.pics3, R.drawable.pics4
                , R.drawable.pics5, R.drawable.pics6)


        var n: Int = 0
        while (n < words.size) {
            val stu = HomeBean()
            stu.word = words[n]
            stu.name = names[n]
            stu.userpic = userpics[n]
            stu.pic = pics[n]
            list.add(stu)
            n++
        }
    }



}
